package com.example.myeverydaynews;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.feicui.newsclient.fargment.BlankFragment;
import edu.feicui.newsclient.fargment.DuanZiFragment;
import edu.feicui.newsclient.fargment.NBAFragment;
import edu.feicui.newsclient.fargment.QiCheFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    private Button[] mBtns = new Button[4];
    private int[] mIds = {R.id.btn_main_toutiao, R.id.btn_main_duanzi, R.id.btn_main_qiche, R.id.btn_main_nba};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = (NavigationView) findViewById(R.id.navi_main_show);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_main_show);
        for (int i = 0; i < mBtns.length; i++) {
            mBtns[i] = (Button) findViewById(mIds[i]);
            mBtns[i].setOnClickListener(this);
        }
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_toutiao:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_linear_layout, new BlankFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            case R.id.btn_main_duanzi:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_linear_layout, new DuanZiFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            case R.id.btn_main_qiche:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_linear_layout, new QiCheFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            case R.id.btn_main_nba:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_linear_layout, new NBAFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
        }
    }
}
