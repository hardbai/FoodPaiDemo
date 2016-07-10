package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.SearchResultAdapter;
import app.bai.com.foodpai.bean.SearchResult;
import app.bai.com.foodpai.uri.Uri;

public class SearchDitalActivity extends AppCompatActivity {

    private Toolbar dital_SearchToolBar;
    private EditText dital_SearchInput;
    private ImageView dital_SearchGo;
    private TextView order;
    private ListView search_result_listView;
    private PullToRefreshListView searchResult222;
    private int page = 1;
    private List<SearchResult.ItemsBean> itemsBean = new ArrayList<>();
    private SearchResultAdapter adapter;
    private ListView mLV;
    private String foodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dital);
        Intent intent = getIntent();
         foodName = intent.getStringExtra("name");
        Log.d("name","-----------"+foodName);
        initView();
        adapter = new SearchResultAdapter(itemsBean, this, R.layout.searchresultlistitem);

        loadData();
        aboutPullToRefreshListView();


    }

    private void aboutPullToRefreshListView() {
        searchResult222.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mLV = searchResult222.getRefreshableView();
        mLV.setAdapter(adapter);
        searchResult222.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page ++;
                loadData();
            }
        });

    }

    private void loadData() {
        if(page > 20){
            Toast.makeText(this,"最后一页啦！！！！", Toast.LENGTH_LONG).show();
        }
        else{
        String url = String.format(Uri.URL_SEARCH_DITALS, page, foodName);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SearchResult searchResult = gson.fromJson(response,SearchResult.class);

                itemsBean = searchResult.getItems();

                adapter.addRes(itemsBean);
                searchResult222.onRefreshComplete();
            }

        },null) ;
            request.setTag("DataIsOk");
            MyApp.getApp().getRequestQueue().add(request);
    }


    }

    private void initView() {
        dital_SearchToolBar = ((Toolbar) findViewById(R.id.dital_search_toolbar));
        dital_SearchToolBar.setTitle("");
        setSupportActionBar(dital_SearchToolBar);
        dital_SearchToolBar.setNavigationIcon(R.drawable.ic_back_dark);
        dital_SearchToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dital_SearchInput = ((EditText) findViewById(R.id.dital_search_input));
        dital_SearchInput.setText(foodName);
        dital_SearchGo = ((ImageView) findViewById(R.id.dital_search_go));
        dital_SearchGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodName = dital_SearchInput.getText().toString();
                adapter.clear();
                loadData();
            }
        });
        order = ((TextView) findViewById(R.id.tv_list_order));
        searchResult222 = ((PullToRefreshListView) findViewById(R.id.search_result_ptrlv));
    }
}
