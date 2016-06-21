package com.chenqq.mask;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenqq on 2016/6/13 0013.
 */
public class AppConfig {
    private Context mContext;
    private static AppConfig appConfig;
    private static final String APP_CONFIG = "config";

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public Properties get(){
        FileInputStream fis = null;
        Properties pros = new Properties();
        File file = mContext.getDir(APP_CONFIG,Context.MODE_PRIVATE);
        try {
            fis = new FileInputStream(file.getPath()+File.separator+APP_CONFIG);
            pros.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pros;
    }

    public String get (String key) {
        Properties properties = get();
        return (properties != null) ? properties.getProperty(key) : null;
    }

    public void setProps (Properties p) {
        FileOutputStream fos = null;
        File dirConf = mContext.getDir(APP_CONFIG,Context.MODE_PRIVATE);
        File conf = new File(dirConf,APP_CONFIG);
        try {
            fos = new FileOutputStream(conf);
            p.store(fos,null);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
