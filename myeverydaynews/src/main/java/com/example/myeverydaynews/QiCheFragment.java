package com.example.myeverydaynews;

import android.content.Context;
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

import java.util.ArrayList;

import edu.feicui.newsclient.adapter.MyYuLeAdapter;

public class QiCheFragment extends Fragment {
    private static final String TAG = "TouTiaoFragment";
    String mPath = "http://c.m.163.com/nc/article/list/T1348654060988/0-20.html";
    Utils mDemo = new Utils();
    private RecyclerView mRecyclerView;
    private MyThread mTHread;
    private MyHandler mHandler;
    ArrayList<GsonBeanYuLe.T1348654060988Bean> mList;

    public QiCheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tou_tiao, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_toutiao);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mHandler = new MyHandler();
        mTHread = new MyThread();
        mTHread.start();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            String info = mDemo.getInfo(mPath);
            GsonBeanYuLe json = mDemo.getJsonThird(info);
            Log.d(TAG, "run: " + json);
            // 存在复用机制，性能好
            Message message = Message.obtain();
            // 调用 Message里的 Object,将 JsonBean里的消息发送出去
            message.obj = json;
            mHandler.sendMessage(message);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 接收 Thread发过来的消息，并强转成 JsonBean类型
            GsonBeanYuLe obj = (GsonBeanYuLe) msg.obj;
            // 调用 JsonBean里的 getResults()方法添加到集合里
            mList = (ArrayList<GsonBeanYuLe.T1348654060988Bean>) obj.getT1348654060988();
            // 通过适配器加载出来
            MyYuLeAdapter mAdapter = new MyYuLeAdapter(getActivity(), mList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
