package app.bai.com.foodpai.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initView();
        initFragment();
        TabFragmentsUtils tabFragmentsUtils = new TabFragmentsUtils(radioGroup,fragments,getSupportFragmentManager(),R.id.re_frameLayout);
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
}
