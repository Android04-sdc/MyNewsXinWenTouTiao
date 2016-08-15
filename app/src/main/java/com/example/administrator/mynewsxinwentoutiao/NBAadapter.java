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
public class NBAadapter extends RecyclerView.Adapter<NBAadapter.NBAviewHolder> {
    ArrayList<GsonBeanNBA.T1348649145984Bean> mlist;
    Context mcontext;

    public NBAadapter(ArrayList<GsonBeanNBA.T1348649145984Bean> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }

    @Override
    public NBAviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.nba_show_item,parent,false);
        NBAviewHolder viewHolder=new NBAviewHolder(view);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(NBAviewHolder holder, int position) {
        final GsonBeanNBA.T1348649145984Bean NBAbean = mlist.get(position);
        holder.textView.setText(NBAbean.getTitle());
        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(300,200));
        Picasso.with(mcontext).load(NBAbean.getImgsrc()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,ShowNBA.class);
                intent.putExtra("url_3w",NBAbean.getUrl_3w());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mlist!=null){
            return mlist.size();
        }
        return 0;
    }
    public class NBAviewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;
        private final LinearLayout linearLayout;
        public NBAviewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.nba_img);
            textView = (TextView) itemView.findViewById(R.id.nba_textview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.nba_item_show);
        }
    }
}
