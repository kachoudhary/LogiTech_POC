package com.topcoder.autoinsurance.domain.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor {

    private static volatile ThreadExecutor sThreadExecutor;

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadExecutor() {
        long keepAlive = (long) KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE
        );
    }

    public void execute(final BaseRunnable interactor) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        interactor.onFinish();
                    }
                });
            }
        });
    }

    public static ThreadExecutor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }

        return sThreadExecutor;
    }
}
