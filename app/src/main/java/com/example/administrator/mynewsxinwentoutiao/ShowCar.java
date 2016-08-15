package com.example.administrator.mynewsxinwentoutiao;
import android.content.Intent;
import android.net.wifi.WifiEnterpriseConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class ShowCar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);
        Intent intent = getIntent();
        String url_3w = intent.getStringExtra("url_3w");
        WebView webview=new WebView(this);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url_3w);
        setContentView(webview);
    }
}
