package com.example.administrator.mynewsxinwentoutiao;

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
 * Created by Administrator on 2016/8/10.
 */
public class XiaohuaAdapter extends RecyclerView.Adapter<XiaohuaAdapter.XiaoHuaViewHolder> {
    ArrayList<GsonBeanxiaohua.T1350383429665Bean> list;
    Context mcontext;
    public XiaohuaAdapter(ArrayList<GsonBeanxiaohua.T1350383429665Bean> list, Context context) {
        this.list = list;
        mcontext = context;
    }
    @Override
    public XiaoHuaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.xiaohua_item_show,parent,false);
        XiaoHuaViewHolder viewHolder=new XiaoHuaViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(XiaoHuaViewHolder holder, int position) {
        final GsonBeanxiaohua.T1350383429665Bean xiaohuabean = list.get(position);
        holder.textView.setText(xiaohuabean.getTitle());
        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(300,200));
        Picasso.with(mcontext).load(xiaohuabean.getImgsrc()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,ShowXiaohua.class);
                intent.putExtra("url_3w",xiaohuabean.getUrl_3w());
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
    public class XiaoHuaViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;
        private final LinearLayout linearLayout;
        public XiaoHuaViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.xiaohua_img);
            textView = (TextView) itemView.findViewById(R.id.xiaohua_textview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.xiaohua_item);
        }
    }
}
