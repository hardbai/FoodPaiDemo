package app.bai.com.foodpai;

import android.app.Application;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import app.bai.com.foodpai.bean.Collect;
import app.bai.com.foodpai.bean.FoodCollect;
import app.bai.com.foodpai.bean.FoodDetialForWiki;
import app.bai.com.foodpai.bean.Search;
import app.bai.com.foodpai.bean.UploadFood;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class MyApp extends Application {
    public SharedPreferences config;
    private static MyApp app;
    private RequestQueue requestQueue;
    private DbUtils dbUtils;
    private FoodDetialForWiki.IngredientBean ingredient;

    public FoodDetialForWiki.IngredientBean getIngredient() {
        return ingredient;
    }

    public void setIngredient(FoodDetialForWiki.IngredientBean ingredient) {
        this.ingredient = ingredient;
    }

    public SharedPreferences getConfig() {
        return config;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        config = getSharedPreferences("config", MODE_PRIVATE);
        initVolley();
        initDbUtils();


    }

    private void initDbUtils() {
        dbUtils = DbUtils.create(this,"collect.db",1,new DbUtils.DbUpgradeListener() {
            /**
             *
             * @param db
             * @param oldVersion :旧版本号
             * @param newVersion :新版本号
             */
            @Override
            public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                if(oldVersion<newVersion)
                {
                    //dbUtils.execQuery("alter table ")
                    try {
                        //删除表
                        dbUtils.dropTable(Collect.class);

                        dbUtils.dropTable(Search.class);

                        dbUtils.dropTable(FoodCollect.class);

                        //创建表
                        dbUtils.createTableIfNotExist(Collect.class);//收藏webView的表

                        dbUtils.createTableIfNotExist(Search.class);//搜索框中搜藏食物名的表

                        dbUtils.createTableIfNotExist(FoodCollect.class);

                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //2.创建收藏表
        try {
            dbUtils.createTableIfNotExist(Collect.class);

            dbUtils.createTableIfNotExist(Search.class);//搜索框中搜藏食物名的表

            dbUtils.createTableIfNotExist(FoodCollect.class);

            dbUtils.createTableIfNotExist(UploadFood.class);

            //打印日志信息
            dbUtils.configDebug(true);

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public DbUtils getDbUtils() {
        return dbUtils;
    }

    private void initVolley() {
        requestQueue = Volley.newRequestQueue(this);
    }

    public static MyApp getApp() {
        return app;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void showShare(String text,String url,String comment) {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(text);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(comment);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("食物派");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

// 启动分享GUI
        oks.show(this);
    }
}
