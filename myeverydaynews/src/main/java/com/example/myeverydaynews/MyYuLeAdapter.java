package com.example.myeverydaynews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8.
 */
public class MyYuLeAdapter extends RecyclerView.Adapter<MyYuLeAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<GsonBeanYuLe.T1348654060988Bean> mList;
    public MyYuLeAdapter(Context context, ArrayList<GsonBeanYuLe.T1348654060988Bean> list) {
        mContext = context;
        mList = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_adapter, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GsonBeanYuLe.T1348654060988Bean info = mList.get(position);
        holder.mTvTitle.setText(info.getTitle());
        Picasso.with(mContext).load(info.getImgsrc()).into(holder.mImageView);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowNewsActivity.class);
                intent.putExtra("internet", info.getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTvTitle;
        private LinearLayout mLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout) itemView.findViewById(R.id.tou_tiao_linear_layout);
            mImageView = (ImageView) itemView.findViewById(R.id.recycler_adapter_image);
            mTvTitle = (TextView) itemView.findViewById(R.id.recycler_adapter_tv_title);
        }
    }
}
