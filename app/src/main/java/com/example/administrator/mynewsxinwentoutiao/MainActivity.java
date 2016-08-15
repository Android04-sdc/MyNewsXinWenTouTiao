package com.example.administrator.mynewsxinwentoutiao;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener, ViewPager.OnPageChangeListener {
    private Button mbtncar;
    private Button mbtnxinwen;
    private Button mbtnxiaohua;
    private Button mbtnmeinv;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;//同步用的工具
    private NavigationView navigationView;//抽屉
    private ViewPager mviewPager;
    ArrayList<Fragment> framentlist;
    private NavigationView navigationview1;
    private ImageButton imageButton;
    private Button mbtndenglu;
    private Button mbtnzhuce;
    private EditText medusername;
    private EditText medpassword;
    private View headerView;
    private MyBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtncar = (Button) findViewById(R.id.qiche);
        mbtnxinwen = (Button) findViewById(R.id.xinwen);
        mbtnxiaohua = (Button) findViewById(R.id.xiaohua);
        mbtnmeinv = (Button) findViewById(R.id.nba);
        mviewPager = (ViewPager) findViewById(R.id.viewpage);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//没这句话toolbar上的菜单不显示啊
//        ActionBar supportActionBar = getSupportActionBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        mtoggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open,R.string.close);//
        drawerLayout.addDrawerListener(mtoggle);
        framentlist=new ArrayList<>();

        toolbar.setOnMenuItemClickListener(this);

//        supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
        mtoggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.design_navigation_view);
        navigationview1 = (NavigationView) findViewById(R.id.nagationview_right);
        navigationView.setNavigationItemSelectedListener(this);
        mbtncar.setOnClickListener(this);
        mbtnxinwen.setOnClickListener(this);
        mbtnxiaohua.setOnClickListener(this);
        mbtnmeinv.setOnClickListener(this);
        initheaderayout();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qiche:
                mviewPager.setCurrentItem(2);
                break;
            case R.id.xiaohua:
                mviewPager.setCurrentItem(1);
                break;
            case R.id.nba:
                mviewPager.setCurrentItem(0);//设置当前的出现的 界面
                break;
            case R.id.denglu:
                find();
                Toast.makeText(MainActivity.this, "你点击了登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton:
                Toast.makeText(MainActivity.this, "你点击了头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zhuce:
//            getSupportFragmentManager().beginTransaction().replace(R.id.recyclerview_show,new DengLufragment()).commit();
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "第一次注册", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    //navigationview 中的menu item 条目选中的时候的方法
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shoucang:
                Toast.makeText(MainActivity.this, "你点击了收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.redian:
                Toast.makeText(MainActivity.this, "你点击了热点", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rediguanzhu:
                Toast.makeText(MainActivity.this, "你点击了关注", Toast.LENGTH_SHORT).show();
                break;
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }
    @Override
    //档案返回键的时候
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();//有这个的话按返回键会返回，否则不会退出
        }
    }
    @Override
    //创建一个toolbar上的菜单
    public boolean onCreateOptionsMenu(Menu menu) {//只执行一次，创建以后再也不执行
        getMenuInflater().inflate(R.menu.mtoolbar_menu,menu);
        return true;
    }
    @Override
    //toolbar上的菜单的点击
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Toast.makeText(MainActivity.this, "你点击了menu1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(MainActivity.this, "你点击了menu2", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().beginTransaction().replace(R.id.recyclerview_show,new CarFragment()).commit();
        framentlist.add(new NBAFragment());
        framentlist.add(new XiaohuaFragment());
        framentlist.add(new CarFragment());
        mviewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        mviewPager.setCurrentItem(0);
        mviewPager.addOnPageChangeListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    public class MyViewPagerAdapter extends FragmentPagerAdapter{
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return framentlist.get(position);
        }
        @Override
        public int getCount() {
            return framentlist.size();
        }
    }
    public void initheaderayout(){//eaderlayout 中的控件必须是用navigatiionview.getheaderview()方法返回的view去找到
        headerView = navigationview1.getHeaderView(0);
        imageButton = (ImageButton) headerView.findViewById(R.id.imageButton);
        mbtndenglu = (Button) headerView.findViewById(R.id.denglu);
        mbtnzhuce = (Button) headerView.findViewById(R.id.zhuce);
        medusername = (EditText) headerView.findViewById(R.id.username);
        medpassword = (EditText) headerView.findViewById(R.id.password);
        imageButton.setOnClickListener(this);
        mbtndenglu.setOnClickListener(this);
        mbtnzhuce.setOnClickListener(this);
    }
    public void find(){//遍历数据库
        //下边这两句是代开数据库或者是创建数据库

        myDBHelper=new MyBHelper(getApplicationContext(),"userDB",null,1);
        SQLiteDatabase database = myDBHelper.getReadableDatabase();


        Cursor cursor=database.rawQuery("select*from usertable",null);
        if (cursor!=null&&cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                if (name.equals(medusername.getText())&&password.equals(medpassword.getText())){
                    Toast.makeText(MainActivity.this, "登录吧", Toast.LENGTH_SHORT).show();
                    break;
                }
            }while (cursor.moveToNext());
        }else {
            Toast.makeText(MainActivity.this, "没出来", Toast.LENGTH_SHORT).show();
        }
       database.close();
    }
}
