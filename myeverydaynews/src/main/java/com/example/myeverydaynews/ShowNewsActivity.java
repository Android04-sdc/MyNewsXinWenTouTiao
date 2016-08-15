package com.example.myeverydaynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class ShowNewsActivity extends AppCompatActivity {
    private static final String TAG = "ShowNewsActivity";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        Log.d(TAG, "onCreate: start");
        Intent intent = getIntent();
        String internet = intent.getStringExtra("internet");
        Log.d(TAG, "onCreate: " + internet);
        mWebView = (WebView) findViewById(R.id.web_view);
        // 设置JavaScript可用
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 处理JavaScript对话框
        mWebView.setWebChromeClient(new WebChromeClient());
        // 处理各种通知和请求事件，如果不使用该句代码，将使用内置浏览器访问网页
//        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(internet);
    }
}
