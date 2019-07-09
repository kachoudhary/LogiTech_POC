package com.topcoder.autoinsurance.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.topcoder.autoinsurance.R;
import com.topcoder.autoinsurance.model.POJO.MyListData;

public class MainrecylerAdapter extends RecyclerView.Adapter<MainrecylerAdapter.ViewHolder> {

    private MyListData[] listData;

    public MainrecylerAdapter(MyListData[] listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     final MyListData myListData=listData[position];
     holder.textView.setText(listData[position].getMovieTitle());
     holder.imageView.setImageResource(R.drawable.bg_shadow);
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder (View itemvieew) {
            super(itemvieew);
            this.imageView=(ImageView)itemvieew.findViewById(R.id.movie_image);
            this.textView=(TextView)itemvieew.findViewById(R.id.movie_title);
        }
    }
}
