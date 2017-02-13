package com.guoqiang.wgqviewtest.util;

/**
 * Created by wangguoqiang on 2017/2/7.
 */

import android.util.Log;

import com.guoqiang.wgqviewtest.BaseApplication;

public class LogUtil {
    private static final String APP_TAG = "MiOA";
    private static final String TAG_FORMAT = "%s-%s";

    public static void d(String tag, String log) {
        if (BaseApplication.isUserDebug()) {
            Log.d(String.format(TAG_FORMAT, APP_TAG, tag), log);
        }
    }

    public static void e(String tag, String log) {
        Log.e(String.format(TAG_FORMAT, APP_TAG, tag), log);
    }

    public static void w(String tag, String log) {
        Log.w(String.format(TAG_FORMAT, APP_TAG, tag), log);
    }
}

