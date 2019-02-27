package com.example.pc.leakcanarydemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * as
     */
    private Button mBt;
    private ImageView mImg;
    private WeakReference mWeakReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeakReferences = new WeakReference<Activity>(MainActivity.this);
        DaoUtil.instance(this);
        initView();

        //Thread thread = new Thread();
/*        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                DaoUtil.instance(MainActivity.this);
            }
        };
        timer.schedule(timerTask,3000);*/
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        mImg.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }


    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mImg = (ImageView) findViewById(R.id.img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                 startActivity(new Intent(this, Main2Activity.class));
                String s = "你好我泄露了";
                EventBus.getDefault().postSticky(s.toString());
                // DaoUtil.instance(MainActivity.this);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeakReferences.clear();
        mWeakReferences = null;
       LeakCanary.refWatcher(this);
        //LeakCanary.install(getApplication())
    }
}


