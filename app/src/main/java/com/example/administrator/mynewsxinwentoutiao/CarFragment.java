package com.example.administrator.mynewsxinwentoutiao;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends Fragment {
    private static final String TAG = "CarFragment";
    String Path = "http://c.m.163.com/nc/article/list/T1348654060988/0-20.html";
    Tools tools=new Tools();
    private RecyclerView recyclerView;
    private Myhandler myhandler;
    private Mythread mythread;
    ArrayList<GsonBeanCar.T1348654060988Bean> mlist;

    public CarFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        mlist=new ArrayList<>();
        myhandler= new Myhandler();
        mythread=new Mythread();
        mythread.start();
        return view;
    }
    class Mythread extends Thread{
        @Override
        public void run() {
            super.run();
            Log.d(TAG, "Path: "+Path);
            String getjson = null;
            try {
                getjson = tools.getjson(Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "getjson: "+getjson);
            GsonBeanCar gson = tools.getGson(getjson);
            Log.d(TAG, "gson: "+gson);
            Message message = Message.obtain();
            // 调用 Message里的 Object,将 JsonBean里的消息发送出去
            message.obj = gson;
            myhandler.sendMessage(message);
        }
    }
    class Myhandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GsonBeanCar beancar = (GsonBeanCar) msg.obj;
            Log.d(TAG, "handleMessage: "+beancar.toString());
            mlist = (ArrayList<GsonBeanCar.T1348654060988Bean>) beancar.getT1348654060988();
            Myadapter myadapter=new Myadapter(mlist,getActivity());
            recyclerView.setAdapter(myadapter);//在主线程中更新ui
        }
    }


}
