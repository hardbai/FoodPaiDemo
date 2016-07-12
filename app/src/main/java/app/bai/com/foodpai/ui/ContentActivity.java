package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.fragment.BaseFragment;
import app.bai.com.foodpai.fragment.GuangFragment;
import app.bai.com.foodpai.fragment.MeFragment;
import app.bai.com.foodpai.fragment.WikiFragment;
import app.bai.com.foodpai.utils.TabFragmentsUtils;

public class ContentActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private FrameLayout frameLayout;
    private ArrayList<BaseFragment> fragments;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initView();
        initFragment();
        TabFragmentsUtils tabFragmentsUtils = new TabFragmentsUtils(radioGroup,fragments,getSupportFragmentManager(),R.id.re_frameLayout);
        imageView = ((ImageView) findViewById(R.id.login));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new GuangFragment());
        fragments.add(new WikiFragment());
        fragments.add(new MeFragment());
    }

    private void initView() {
        radioGroup = ((RadioGroup) findViewById(R.id.radioGroup));
        frameLayout = ((FrameLayout) findViewById(R.id.re_frameLayout));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginActivity.removeHandler.sendEmptyMessage(1);
        MyApp.getApp().config.edit().putBoolean("isLogin",false);
        MeFragment.btn_exit.setVisibility(View.GONE);
    }
}
