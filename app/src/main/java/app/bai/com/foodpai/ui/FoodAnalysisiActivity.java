package app.bai.com.foodpai.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyAdapter.MyViewPagerAdapter;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.fragment.Collec_food_Fragment;
import app.bai.com.foodpai.fragment.Search_Fragment;
import app.bai.com.foodpai.fragment.Upload_Fragment;

public class FoodAnalysisiActivity extends AppCompatActivity {
    private Toolbar tb_food_analysis;
    private TextView tv_search_food;
    private EditText et_search_food;
    private TabLayout tl_search_food;
    private ViewPager vp_search_food_id;
    private String[] searchNames;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analysisi);
        aboutToolbar();
        initwidgets();
        initFragments();

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(),searchNames,fragments);
        vp_search_food_id.setAdapter(adapter);
        tl_search_food.setupWithViewPager(vp_search_food_id);


    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new Search_Fragment());
        fragments.add(new Collec_food_Fragment());
        fragments.add(new Upload_Fragment());

    }


    private void initwidgets() {
        tv_search_food = (TextView) findViewById(R.id.tv_search_food_id);
        et_search_food = (EditText) findViewById(R.id.et_search_food_id);
        tl_search_food = (TabLayout) findViewById(R.id.tl_search_food_id);
        vp_search_food_id = (ViewPager) findViewById(R.id.vp_search_food_id);
        searchNames = getResources().getStringArray(R.array.foodAnalysisItems);

    }

    private void aboutToolbar() {
        tb_food_analysis = (Toolbar) findViewById(R.id.tb_food_analysis_id);
        tb_food_analysis.setTitle("");
        setSupportActionBar(tb_food_analysis);
        tb_food_analysis.setNavigationIcon(R.drawable.ic_back_dark);
        tb_food_analysis.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
