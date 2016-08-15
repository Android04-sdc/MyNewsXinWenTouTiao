package com.example.myeverydaynews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.feicui.newsclient.adapter.MyAdapter;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "ViewPagerActivity";
    private static final String SP_CONFIG = "sp_config";
    private static final String IS_FIRST_RUN = "is_first_run";
    private Button mBtnSkip;
    private ViewPager mViewPager;
    private SharedPreferences mPreferences;
    ArrayList<View> mList;
    int[] pictures = {R.drawable.welcome, R.drawable.wy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = getSharedPreferences(SP_CONFIG, MODE_PRIVATE);
        // 判断是不是第一次运行，写入一个期望值
        boolean isFirstRun = mPreferences.getBoolean(IS_FIRST_RUN, true);
        // 如果不是第一次进入应用，直接跳转到 MainActivity界面
        if (!isFirstRun) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_view_pager);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mList = new ArrayList<>();
        mBtnSkip = (Button) findViewById(R.id.btn_view_pager_skip);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_choice);
        for (int i = 0; i < pictures.length; i++) {
            // 实例化一个 ImageView
            ImageView image = new ImageView(this);
            // 为 ImageView设置背景资源
            image.setBackgroundResource(pictures[i]);
            // 将 ImageView添加到集合中
            mList.add(image);
            Log.d(TAG, "onStart: " + mList);
        }
        // 实例化 MyAdapter
        MyAdapter mAdapter = new MyAdapter(mList);
        // 为 ViewPager设置 adapter
        mViewPager.setAdapter(mAdapter);
        // 为 "跳过" 按键设置监听
        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // 当图片改变时设置监听
        mViewPager.addOnPageChangeListener(this);
        // 生成 shared_prefs文件夹
        mPreferences = getSharedPreferences(SP_CONFIG, MODE_PRIVATE);
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putBoolean(IS_FIRST_RUN, false);
        edit.commit();
        Log.d(TAG, "onStart: " + 1);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    /**
     * 实现 ViewPager的监听需要重写是三个方法
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /*
     * 当图片选择到最后一张时，显示跳转按键，其他时候都不显示
     */
    @Override
    public void onPageSelected(int position) {
        if (position == mList.size()) {
            mBtnSkip.setVisibility(View.VISIBLE);
        } else {
            mBtnSkip.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
