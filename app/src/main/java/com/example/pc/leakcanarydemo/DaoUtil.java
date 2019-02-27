package com.example.pc.leakcanarydemo;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.Date;

public class DaoUtil {
    private static TextView mTextViews;



    //我是小仙女
    //我是李涛呀
    //woshiliangchen
    //aaa
    private DaoUtil() {
    }



    private static DaoUtil daoUtil = new DaoUtil();

    public static void instance(Context activity) {
        if (mTextViews == null) {
            mTextViews = new TextView(activity);







        }
    }


}
