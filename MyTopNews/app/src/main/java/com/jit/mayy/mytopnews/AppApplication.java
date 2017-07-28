package com.jit.mayy.mytopnews;

import android.app.Application;

import com.jit.mayy.mytopnews.db.SQLHelper;
import com.jit.mayy.mytopnews.fragment.Page1Fragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.MemoryCookieStore;

import java.util.logging.Level;

public class AppApplication extends Application {

    private static AppApplication mAppApplication;
    private SQLHelper sqlHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = this;
        OkGo.init(this);
        try{
            OkGo.getInstance().debug("OkGo", Level.INFO, true)
                    .setCookieStore(new MemoryCookieStore())
                    .setRetryCount(3);


        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 获取Application
     */
    public static AppApplication getApp() {
        return mAppApplication;
    }

    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
        //整体摧毁的时候调用这个方法
    }

}
