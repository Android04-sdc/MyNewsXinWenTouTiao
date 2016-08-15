package com.example.administrator.mynewsxinwentoutiao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.administrator.mynewsxinwentoutiao.R.id.item_show;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Myadapter extends RecyclerView.Adapter<Myadapter.MyviewHolder> {
    ArrayList<GsonBeanCar.T1348654060988Bean> list;
    Context mcontext;
    public Myadapter(ArrayList<GsonBeanCar.T1348654060988Bean> list, Context context) {
        this.list = list;
        mcontext = context;
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_show, parent, false);
        MyviewHolder holder=new MyviewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        final GsonBeanCar.T1348654060988Bean CarBean = list.get(position);
        holder.mtv.setText(CarBean.getTitle());
        holder.imageView.setLayoutParams(new RelativeLayout.LayoutParams(300,200));
        Picasso.with(mcontext).load(CarBean.getImgsrc()).into(holder.imageView);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,ShowCar.class);
                intent.putExtra("url_3w",CarBean.getUrl_3w());
                mcontext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
    public class MyviewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView mtv;
        private RelativeLayout relativeLayout;
        public MyviewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            mtv = (TextView) itemView.findViewById(R.id.tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_show);
        }
    }
}
