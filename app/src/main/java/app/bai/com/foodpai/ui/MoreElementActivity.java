package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.MoreElementAdapter;
import app.bai.com.foodpai.bean.FoodDetialForWiki;

public class MoreElementActivity extends AppCompatActivity {

    private ListView more_lv_id;
    private Intent receiveIntent;
    private FoodDetialForWiki.IngredientBean ingredient;
    private Toolbar more_tb_id;
    private String[] elementNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_element);
        //思路
        //获得数据源
        ingredient = MyApp.getApp().getIngredient();
        elementNames = getResources().getStringArray(R.array.elementNames);
        //初始化控件
        initWidget();
        //关于toolbar的操作
        aboutToolbar();
        //关于ListView的操作
        aboutListView();
    }
    //关于toolbar的操作
    private void aboutToolbar() {
        more_tb_id.setTitle("");
        more_tb_id.setNavigationIcon(R.drawable.ic_back_dark);
        more_tb_id.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //关于ListView的操作
    private void aboutListView() {
        //适配器
        MoreElementAdapter adapter = new MoreElementAdapter(this,ingredient,elementNames);
        more_lv_id.setAdapter(adapter);
    }

    //初始化控件
    private void initWidget() {
        more_tb_id = ((Toolbar) findViewById(R.id.more_tb_id));
        more_lv_id = ((ListView) findViewById(R.id.more_lv_id));
    }
}
