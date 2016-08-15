package com.example.administrator.mynewsxinwentoutiao;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class XiaohuaFragment extends Fragment {
    ArrayList<GsonBeanxiaohua.T1350383429665Bean> mlist;
    private RecyclerView recyclerView;
    XiaohuaHandler handler;
    XiaohuaThread thread;
    Tools tools=new Tools();
    String path="http://c.m.163.com/nc/article/list/T1350383429665/0-20.html";
    public XiaohuaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xiaohua, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.xiaohua_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        handler=new XiaohuaHandler();
        thread=new XiaohuaThread();
        thread.start();
        return view;
    }
    class XiaohuaThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                String getjson = tools.getjson(path);
                GsonBeanxiaohua gsonxiaohua = tools.getGsonxiaohua(getjson);
                Message message=Message.obtain();
                message.obj=gsonxiaohua;
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class XiaohuaHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GsonBeanxiaohua gsonxioahua = (GsonBeanxiaohua) msg.obj;
            mlist= (ArrayList<GsonBeanxiaohua.T1350383429665Bean>) gsonxioahua.getT1350383429665();
            XiaohuaAdapter adapter=new XiaohuaAdapter(mlist,getActivity());
            recyclerView.setAdapter(adapter);
        }
    }
}
