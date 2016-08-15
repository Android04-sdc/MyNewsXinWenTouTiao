package com.example.myeverydaynews;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.feicui.newsclient.adapter.MyRecyclerAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BlankFragment extends Fragment {
    private static final String TAG = "BlankFragment";
    String mPath = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
    OkHttpClient mClient;
    private RecyclerView mRecyclerView;
    ArrayList<GsonBeanTouTiao> mList;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tou_tiao, container, false);
        mClient = new OkHttpClient();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_toutiao);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        new MyTHread().start();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    class MyTHread extends Thread {
        @Override
        public void run() {
            super.run();
            Request request = new Request.Builder().url(mPath).build();
            try {
                Response response = mClient.newCall(request).execute();
                Log.d(TAG, "run: " + response.body().string());
                // 如果响应
                if (response.isSuccessful()) {
                    doGet(response.body().string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void doGet(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("T1348647909107");
            for (int i = 1; i < mList.size(); i++) {
                JSONObject arrays = array.getJSONObject(i);
                String url = arrays.getString("url");
                String title = arrays.getString("title");
                String image = arrays.getString("imgsrc");
                GsonBeanTouTiao touTiao = new GsonBeanTouTiao(url, image, title);
                mList.add(touTiao);
            }
            mRecyclerView.setAdapter(new MyRecyclerAdapter(getActivity(), mList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
