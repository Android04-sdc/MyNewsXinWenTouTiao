package com.example.myeverydaynews;
import com.google.gson.Gson;

import edu.feicui.newsclient.gsonbean.GsonBean;
import edu.feicui.newsclient.gsonbean.GsonBeanDuanZi;
import edu.feicui.newsclient.gsonbean.GsonBeanNBA;
import edu.feicui.newsclient.gsonbean.GsonBeanYuLe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by Administrator on 2016/8/8.
 */
public class Utils {
    public GsonBean getJson(String str) {
        // 实例化 Gson对象
        Gson gson = new Gson();
        GsonBean gsonBean = gson.fromJson(str, GsonBean.class);
        return gsonBean;
    }
    public GsonBeanDuanZi getJsonSecond(String str) {
        // 实例化 Gson对象
        Gson gson = new Gson();
        GsonBeanDuanZi gsonBean = gson.fromJson(str, GsonBeanDuanZi.class);
        return gsonBean;
    }
    public GsonBeanYuLe getJsonThird(String str) {
        // 实例化 Gson对象
        Gson gson = new Gson();
        GsonBeanYuLe gsonBean = gson.fromJson(str, GsonBeanYuLe.class);
        return gsonBean;
    }
    public GsonBeanNBA getJsonForth(String str) {
        // 实例化 Gson对象
        Gson gson = new Gson();
        GsonBeanNBA gsonBean = gson.fromJson(str, GsonBeanNBA.class);
        return gsonBean;
    }
    public String getInfo(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                System.out.print("请求参数有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
