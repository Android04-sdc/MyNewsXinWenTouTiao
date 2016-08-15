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
public class NBAFragment extends Fragment {
    Tools tools=new Tools();
    String path="http://c.m.163.com/nc/article/list/T1348649145984/0-20.html";
    ArrayList<GsonBeanNBA.T1348649145984Bean> mlist;
    private RecyclerView recyclerView;
    NBAHandler handler;
    NBAThread thread;
    public NBAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nba, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.nba_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        handler=new NBAHandler();
        thread=new NBAThread();
        thread.start();
        return view;
    }
    class NBAThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                String getjson = tools.getjson(path);
                GsonBeanNBA gsonnba = tools.getGsonnba(getjson);
                Message message=Message.obtain();
                message.obj=gsonnba;
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class NBAHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GsonBeanNBA gsonnba = (GsonBeanNBA) msg.obj;
            mlist= (ArrayList<GsonBeanNBA.T1348649145984Bean>) gsonnba.getT1348649145984();
            NBAadapter adapter=new NBAadapter(mlist,getActivity());
            recyclerView.setAdapter(adapter);
        }
    }
}
