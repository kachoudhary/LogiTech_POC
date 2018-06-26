package com.topcoder.autoinsurance.domain.repository.dummyimpl;

import android.content.Context;

import com.topcoder.autoinsurance.domain.executor.ThreadExecutor;
import com.topcoder.autoinsurance.domain.repository.UserRepository;
import com.topcoder.autoinsurance.domain.repository.callback.GetPolicyCallback;
import com.topcoder.autoinsurance.domain.repository.dummyimpl.runnable.GetPolicyRunnable;

/**
 * Created by TCCoder on 25/01/2018.
 * This is an dummy implementation of the UserRepository.
 * This class fetch some data from JSON file.
 * Further development should be implemented to fetch from API
 */

public class UserRepositoryImpl implements UserRepository {

    private Context mContext;
    public UserRepositoryImpl(Context context) {
        mContext = context;
    }

    @Override
    public void getPolicy(GetPolicyCallback callback) {
        GetPolicyRunnable runnable = new GetPolicyRunnable(mContext, callback);
        ThreadExecutor.getInstance().execute(runnable);
    }

}
