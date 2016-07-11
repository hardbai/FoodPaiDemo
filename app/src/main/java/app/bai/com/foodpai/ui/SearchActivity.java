package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.showSearchHistoryAdapter;
import app.bai.com.foodpai.bean.Search;

public class SearchActivity extends AppCompatActivity  {

    private Toolbar search_toolBar;
    private EditText input;
    private ImageView doSearch;
    private ListView searchHistory_LV;
    private LinearLayout historyClear;
    private List<Search> search = new ArrayList<>();
    private showSearchHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initData();
        initView();
    }

    private void initData() {
        try {

             search = MyApp.getApp().getDbUtils().findAll(Search.class);
            Log.d("size","-----------"+search.size());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        search_toolBar = ((Toolbar) findViewById(R.id.search_toolbar));
        search_toolBar.setTitle("");
        setSupportActionBar(search_toolBar);
        input = ((EditText) findViewById(R.id.search_input));//输入框
        doSearch = ((ImageView) findViewById(R.id.search_go));//搜索图标
        doSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(input.getText()!=null&&input.getText().length()>0){


                    Search search = new Search();
                    search.setName(input.getText().toString());

                    try {
                        MyApp.getApp().getDbUtils().saveOrUpdate(search);//将食物名存入数据库

                        //跳转到详情页
                        Intent intent = new Intent(SearchActivity.this,SearchDitalActivity.class);
                        intent.putExtra("name",input.getText().toString());
                        startActivity(intent);

                        finish();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(SearchActivity.this,"内容不能为空",Toast.LENGTH_LONG).show();
                }


            }
        });
        search_toolBar.setNavigationIcon(R.drawable.ic_back_dark);
        search_toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchHistory_LV = ((ListView) findViewById(R.id.search_history_lv));
        historyClear = ((LinearLayout) findViewById(R.id.ll_clearHistory));
        adapter = new showSearchHistoryAdapter(search,this,R.layout.searchhistory_lv_item);
        searchHistory_LV.setAdapter(adapter);
        searchHistory_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this,SearchDitalActivity.class);
                intent.putExtra("name",adapter.getItem(i).getName());
                Log.d("name","--------"+adapter.getItem(i).getName());
                startActivity(intent);
            }
        });
        historyClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MyApp.getApp().getDbUtils().deleteAll(Search.class);//清空搜素历史表中的数据
                    adapter.clear();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
