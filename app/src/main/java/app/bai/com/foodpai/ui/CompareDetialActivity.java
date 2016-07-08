package app.bai.com.foodpai.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.bai.com.foodpai.R;

public class CompareDetialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_detial);
        //设置Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.compare_detial_tb_id);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //思路
        //初始化界面控件
        initWidget();
    }
    //初始化控件
    private void initWidget() {
        findViewById(R.id.compare_left_iv_id);
        findViewById(R.id.compare_left_tv_id);
        findViewById(R.id.compare_right_iv_id);
        findViewById(R.id.compare_right_tv_id);
    }
}
