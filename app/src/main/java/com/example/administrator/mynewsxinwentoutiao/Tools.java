package com.example.administrator.mynewsxinwentoutiao;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
/**
 * Created by Administrator on 2016/8/9.
 */
public class Tools {
    private static final String TAG = "CarFragment";
    public String getjson(String path) throws IOException {//获取乱码资源的方法
        OkHttpClient mclient = new OkHttpClient();
        Log.d(TAG, "path: "+path);
       Request request=new Request.Builder().url(path).build();
        Log.d(TAG, "request: "+request.toString());
        Response response = mclient.newCall(request).execute();
        if (response.isSuccessful()){
            String string = response.body().string();
            Log.d(TAG, "getjson: "+string);
            return string;
        }else{
            System.out.println("失败了");
        }
        return null;
    }
    public GsonBeanCar getGson(String string){
        Gson gson=new Gson();
        GsonBeanCar gsonBeanCar = gson.fromJson(string, GsonBeanCar.class);
        return gsonBeanCar;
    }
    public GsonBeanxiaohua getGsonxiaohua(String string){
        Gson gson=new Gson();
        GsonBeanxiaohua gsonBeanxiaohua = gson.fromJson(string, GsonBeanxiaohua.class);
        return gsonBeanxiaohua;
    }
    public GsonBeanNBA getGsonnba(String string){
        Gson gson=new Gson();
        GsonBeanNBA gsonBeannba = gson.fromJson(string, GsonBeanNBA.class);
        return gsonBeannba;
    }

}
