package app.bai.com.foodpai;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class MyApp extends Application {
    private static MyApp app;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        initVolley();

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
}
