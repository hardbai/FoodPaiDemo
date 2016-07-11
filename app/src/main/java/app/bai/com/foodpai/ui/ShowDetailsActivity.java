package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.exception.DbException;

import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.Collect;

public class ShowDetailsActivity extends AppCompatActivity {

    private ImageView iv_back;
    private WebView webView;
    private ImageView iv_share;
    private TextView checkBox_collect;
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

        checkBox_collect.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    switch (view.getId()){
                        case R.id.checkBox_collect:
                            String title = intent.getStringExtra("title");

                            if(isCollected(title)){
                                //将网页存入数据库
                                Collect collect = new Collect();
                                collect.setTitle(title);
                                collect.setUrl(link);
                                try {
                                    MyApp.getApp().getDbUtils().saveOrUpdate(collect);
                                    Toast.makeText(ShowDetailsActivity.this,"收藏成功！",Toast.LENGTH_LONG).show();
                                    Log.d("tag","-----------------"+collect.getTitle());
                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                Toast.makeText(ShowDetailsActivity.this,"已收藏过了！",Toast.LENGTH_LONG).show();
                            }

                            break;
                    }
            }
        });

    }

    private void initView() {
        iv_back = ((ImageView) findViewById(R.id.back));
        webView = ((WebView) findViewById(R.id.show_webView));
        iv_share = ((ImageView) findViewById(R.id.iv_share));
        checkBox_collect = ((TextView) findViewById(R.id.checkBox_collect));
    }


    public boolean isCollected(String title){
        List<Collect> collects = null;
        try {
            collects = MyApp.getApp().getDbUtils().findAll(Collect.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        for (Collect c:collects) {
            if(c.getTitle().equals(title)){
                return false;
            }
        }
        return  true;
    }
}
