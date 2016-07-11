package app.bai.com.foodpai.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyAdapter.ViewPageAdapter;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.fragment.Collec_Fragment;
import app.bai.com.foodpai.fragment.Collec_food_Fragment;

public class CollectActivity extends AppCompatActivity {
    private Toolbar tb_collect;
    private TabLayout tl_id;
    private ViewPager vp_id;
    private String[] tabNames;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        tb_collect = (Toolbar) findViewById(R.id.tb_collect_id);
        tb_collect.setTitle("");
        setSupportActionBar(tb_collect);
        tb_collect.setNavigationIcon(R.drawable.ic_back_dark);
        tb_collect.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //获取界面上的控件
        initWidgets();
        initData();
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), tabNames, fragments);
        vp_id.setAdapter(adapter);
        tl_id.setupWithViewPager(vp_id);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new Collec_Fragment());
        fragments.add(new Collec_food_Fragment());
    }

    private void initWidgets() {
        tl_id = (TabLayout) findViewById(R.id.tl_id);
        vp_id = (ViewPager) findViewById(R.id.vp_id);
        tabNames = getResources().getStringArray(R.array.collections);
    }

}
