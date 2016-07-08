package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.lidroid.xutils.exception.DbException;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.Collect;

public class ShowDetailsActivity extends AppCompatActivity {

    private ImageView iv_back;
    private WebView webView;
    private ImageView iv_share;
    private ImageView iv_keep;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        initView();
        intent = getIntent();
       final String link = intent.getStringExtra("link");
        Log.d("link","---------------"+link);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.getApp().showShare("不错",link,"真好吃");
            }
        });

        iv_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              int id = intent.getIntExtra("id",0);
                String title = intent.getStringExtra("title");
                Collect collect = new Collect();
                collect.setId(id);
                collect.setTitle(title);
                collect.setUrl(link);
                try {
                    MyApp.getApp().getDbUtils().saveOrUpdate(collect);
                    Log.d("check","ok");
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        iv_back = ((ImageView) findViewById(R.id.back));
        webView = ((WebView) findViewById(R.id.show_webView));
        iv_share = ((ImageView) findViewById(R.id.iv_share));
        iv_keep = ((ImageView) findViewById(R.id.iv_keep));
    }
}
