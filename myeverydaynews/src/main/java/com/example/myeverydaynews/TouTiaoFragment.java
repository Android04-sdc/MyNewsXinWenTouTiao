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

public class TouTiaoFragment extends Fragment {
    private static final String TAG = "TouTiaoFragment";
    String mPath = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
    Utils mDemo = new Utils();
    String urlInter;
    private RecyclerView mRecyclerView;
    private MyTHread mTHread;
    private MyHandler mHandler;
    ArrayList<GsonBean.T1348647909107Bean> mList;

    public TouTiaoFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tou_tiao, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_toutiao);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mHandler = new MyHandler();
        mTHread = new MyTHread();
        mTHread.start();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 创建一个 Thread用来发送消息
     */
    class MyTHread extends Thread {
        @Override
        public void run() {
            super.run();
            String info = mDemo.getInfo(mPath);
            GsonBean json = mDemo.getJson(info);
            Log.d(TAG, "run: " + json);
            // 存在复用机制，性能好
            Message message = Message.obtain();
            // 调用 Message里的 Object,将 JsonBean里的消息发送出去
            message.obj = json;
            mHandler.sendMessage(message);
        }
    }
    /**
     * 创建一乐个 Handler用来接收 Thread发过来的消息
     */
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 接收 Thread发过来的消息，并强转成 JsonBean类型
            GsonBean obj = (GsonBean) msg.obj;
            // 调用 JsonBean里的 getResults()方法添加到集合里
            mList = (ArrayList<GsonBean.T1348647909107Bean>) obj.getT1348647909107();
            // 通过适配器加载出来
//            MyRecyclerAdapter mAdapter = new MyRecyclerAdapter(getActivity(), mList);
//            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
