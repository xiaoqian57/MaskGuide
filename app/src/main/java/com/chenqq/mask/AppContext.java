package com.chenqq.mask;

import android.app.Application;

import java.util.Properties;

/**
 * Created by chenqq on 2016/6/13 0013.
 */
//切记别忘了在AndroidManifest中application下添加android:name=".AppContext"，保证程序入口
public class AppContext extends Application {
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppContext getInstance () {
        return instance;
    }

    public void setProperties (Properties ps) {
        AppConfig.getAppConfig(this).setProps(ps);
    }

    public Properties getProperties () {
        return AppConfig.getAppConfig(this).get();
    }

    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

}
