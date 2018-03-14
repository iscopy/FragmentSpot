package com.win.fragmentspot;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.win.fragmentspot.fragment.FiveFragment;
import com.win.fragmentspot.fragment.FourFragment;
import com.win.fragmentspot.fragment.OneFragment;
import com.win.fragmentspot.fragment.ThreeFragment;
import com.win.fragmentspot.fragment.TwoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout fragment_container; //碎片容器
    private RadioButton mHomeRb, mSleepRb, mStatisticsRb,mFindRb, mMySelfRb; //碎片的五个按钮
    private RadioButton[] rbs;
    //碎片布局
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FiveFragment fiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fragment();
        //首先设置第一个界面布局
        oneFragment = new OneFragment();
        replaces(oneFragment);

    }

    public void init(){
        mHomeRb = (RadioButton) findViewById(R.id.home_rb);
        mSleepRb = (RadioButton) findViewById(R.id.sleep_rb);
        mStatisticsRb = (RadioButton) findViewById(R.id.Statistics_rb);
        mFindRb = (RadioButton) findViewById(R.id.find_rb);
        mMySelfRb = (RadioButton) findViewById(R.id.myself_rb);
        mHomeRb.setOnClickListener(this);
        mSleepRb.setOnClickListener(this);
        mStatisticsRb.setOnClickListener(this);
        mFindRb.setOnClickListener(this);
        mMySelfRb.setOnClickListener(this);
        mHomeRb.setChecked(true);
        rbs = new RadioButton[5];
        rbs[0] = mHomeRb;
        rbs[1] = mSleepRb;
        rbs[2] = mStatisticsRb;
        rbs[3] = mFindRb;
        rbs[4] = mMySelfRb;
        for (RadioButton rb : rbs) {//设置导航图像的大小
            Drawable[] drs = rb.getCompoundDrawables();
            Rect r = new Rect(0, 0, drs[1].getMinimumWidth() * 2 / 3 - 5, drs[1].getMinimumHeight() * 2 / 3 - 5);
            drs[1].setBounds(r);
            rb.setCompoundDrawables(null, drs[1], null, null);
        }
    }

    public void fragment(){
        oneFragment = new OneFragment();
        addFragment(oneFragment,"one");
        twoFragment = new TwoFragment();
        addFragment(twoFragment,"two");
        threeFragment = new ThreeFragment();
        addFragment(threeFragment,"three");
        fourFragment = new FourFragment();
        addFragment(fourFragment,"four");
        fiveFragment = new FiveFragment();
        addFragment(fiveFragment,"five");
    }

    //动态添加 Fragment
    private void addFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, tag);
        transaction.commit();
    }

    //将 container(碎片容器) 中的视图移除，然后添加上 fragment 视图
    public void replaces(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        //点击后，调用 replaces() 方法将布局替换掉，前面的布局就移除掉
        switch(view.getId()){
            case R.id.home_rb:
                oneFragment = new OneFragment();
                replaces(oneFragment);
                break;
            case R.id.sleep_rb:
                twoFragment = new TwoFragment();
                replaces(twoFragment);
                break;
            case R.id.Statistics_rb:
                threeFragment = new ThreeFragment();
                replaces(threeFragment);
                break;
            case R.id.find_rb:
                fourFragment = new FourFragment();
                replaces(fourFragment);
                break;
            case R.id.myself_rb:
                fiveFragment = new FiveFragment();
                replaces(fiveFragment);
                break;
        }
    }
}
