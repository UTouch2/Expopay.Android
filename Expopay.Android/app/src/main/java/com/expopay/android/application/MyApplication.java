package com.expopay.android.application;

import android.app.Application;

import com.android.kechong.lib.BaseApplication;
import com.android.kechong.lib.cache.MyCache;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MyApplication extends BaseApplication {
    public final static String HOST = "http://10.66.2.31/";
    public static final String PHONE = "400-022-7299";
    public static final String EMAIL = "service@expopay.cn";
    public static final String NET = "http://www.expopay.cn";
    public final static String DB_NAME = "expopay.db";
    public static MyCache cache = MyCache.getInstance();
}