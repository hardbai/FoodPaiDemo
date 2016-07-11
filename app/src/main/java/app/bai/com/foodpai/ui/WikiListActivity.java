package app.bai.com.foodpai.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.MyWikiListAdapter;
import app.bai.com.foodpai.bean.ListFoodForWiki;
import app.bai.com.foodpai.uri.Uri;

public class WikiListActivity extends AppCompatActivity {
    private String kind;
    private int value;
    private int order=1;
    private String title;
    private Toolbar wikiListToolbar;//Toolbar
    private PullToRefreshListView wiki_list_lv_id;//pullToRefreshListView
    private int pageNum = 1;//分页加载
    private List<ListFoodForWiki.FoodsBean> foods;//整个wiki的数据源
    private MyWikiListAdapter adapter;//wiki的适配器
    private ListView myListView;//pullToRefreshListView里面真正的ListView
    private CheckBox wiki_list_order_cb_id;//排序跟向下箭头,用来添加监听显示PopUpWindow
    private GridView popupwindowOrderGvId;//PopUpWindow里面的GridView
    private RelativeLayout mRelativeLayoutId;//排序整个LinearLayout,用来给PopUpWindow定位
    private PopupWindow mPopupWindow;//声明opUpWindow
    private PopupWindow mSubPopupWindow;//声明子数据opUpWindow
    private boolean isShow = false;//判断PopUpWindow是否显示
    private boolean subIsShow = false;//判断子数据popupwindow是否显示
    private CheckBox wiki_list_changeorder_cb_id;//调整排序
    private String[] orderNames;//排序选项
    private TextView wiki_list_title_id;//toolbar 的标题
    private TextView wiki_list_sub_id;//toolbar里面的TextView,子数据的popupwindow就显示在它下面
    private ListView sub_popup_lv_id;//subPopUpWindow里面的ListView
    private ArrayList<String> subs;//传过来的子数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_list);

        //初始化
        //获取传过来的值
        Intent listIntent = getIntent();
        title = listIntent.getStringExtra("name");
        kind = listIntent.getStringExtra("kind");
        value = listIntent.getIntExtra("value", 1);
        subs = listIntent.getStringArrayListExtra("sub");
//        subs = new String[]{"sfsdf","sfsdf","sdfdf"};
        //初始化控件
        initWidget();
        //准备Toolbar
        initToolbar();
        //关于子类食物数据popupwindow的操作
        aboutSubCategories();
        //关于排序popupwindow的的操作
        aboutWikiListOrderLlId();
        //关于wiki_list_lv_id下拉数据
        aboutWikiListLvId();


    }

    //关于子数据的操作
    private void aboutSubCategories() {
        if(!subs.isEmpty()){
            wiki_list_sub_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(subIsShow){
                        mSubPopupWindow.dismiss();
                        subIsShow=false;
                    }else{
                        showSubPopupWindow();
                        subIsShow=true;
                    }
                }
            });
        }else{
            wiki_list_sub_id.setVisibility(View.INVISIBLE);
        }

    }

    //子数据通过popupwindow弹出来
    private void showSubPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.subpopupwindow,null);
        sub_popup_lv_id = ((ListView) view.findViewById(R.id.wiki_list_tb_sub_popup_lv_id));
        aboutSubPopupLvId();
        mSubPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);
        mSubPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSubPopupWindow.setOutsideTouchable(true);
        mSubPopupWindow.showAsDropDown(wiki_list_sub_id);
    }

    //关于子数据popupwindow里面的listView的操作
    private void aboutSubPopupLvId() {
        //思路
        //数据源
        //适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.popup_text_item,subs);
        sub_popup_lv_id.setAdapter(adapter);
        //监听
        sub_popup_lv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                wiki_list_sub_id.setText(subs.get(i));
                mSubPopupWindow.dismiss();
            }
        });

    }

    //于下拉数据的操作
    private void aboutWikiListLvId() {
        wiki_list_lv_id.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        //数据源获取
        //准备url
//        String url = String.format(Uri.URL_WIKIS_II, kind, value, order, pageNum);
        String url = String.format(Uri.URL_WIKIS_II, kind, value, order, pageNum);
        foods = new ArrayList<>();
        fillDataSource(url);
        //准备适配器
        adapter = new MyWikiListAdapter(this, foods);
        //添加监听
        wiki_list_lv_id.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                loadData(pageNum);

            }

        });
        myListView = ((ListView) wiki_list_lv_id.getRefreshableView());
        myListView.setAdapter(adapter);
        //给内部listview添加监听器
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String code = ((ListFoodForWiki.FoodsBean) adapter.getItem(i-1)).getCode();
                String name = ((ListFoodForWiki.FoodsBean) adapter.getItem(i-1)).getName();

                Intent detialIntent = new Intent(WikiListActivity.this,FoodDetialActivity.class);
                detialIntent.putExtra("code",code);
                detialIntent.putExtra("name",name);

                startActivity(detialIntent);

            }
        });
    }

    //准备Toolbar
    private void initToolbar() {
        wikiListToolbar = (Toolbar) findViewById(R.id.wiki_list_tb_id);
        wikiListToolbar.setTitle("");
        setSupportActionBar(wikiListToolbar);

        wiki_list_title_id.setText(title);
        wikiListToolbar.setNavigationIcon(R.drawable.ic_back_dark);
        wikiListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //关于orderlinearlayout的操作(排序)
    private void aboutWikiListOrderLlId() {
        wiki_list_order_cb_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isShow){
                    mPopupWindow.dismiss();
                    isShow=false;
                }else{
                    showPopupWindow();
                    isShow=true;

                }
            }
        });
//        wiki_list_changeorder_cb_id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
        wiki_list_changeorder_cb_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    wiki_list_changeorder_cb_id.setText("从低到高");
                }else{
                    wiki_list_changeorder_cb_id.setText("从高到低");
                }
                adapter.reverseData();
            }
        });
    }

    //显示popupWindow
    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow,null);
        popupwindowOrderGvId = ((GridView) view.findViewById(R.id.popupwindow_order_gv_id));
        aboutPopupWindowOrderGvId();
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(mRelativeLayoutId);
    }

    //关于orderGridView的操作
    private void aboutPopupWindowOrderGvId() {
        //思路
        //准备数据
        final String[] orderNames = getResources().getStringArray(R.array.orderNames);

        //准备适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.popup_text_item,orderNames);
        popupwindowOrderGvId.setAdapter(adapter);
        //添加监听
        popupwindowOrderGvId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                wiki_list_order_cb_id.setText(orderNames[i]);
                wiki_list_order_cb_id.setChecked(false);
                isShow=false;
                wiki_list_changeorder_cb_id.setVisibility(View.VISIBLE);
                order = i;
                String orderUrl = String.format(Uri.URL_WIKIS_II,kind,value,order,1);
                fillDataSource(orderUrl);
                mPopupWindow.dismiss();
            }
        });
    }

    //加载数据
    private void loadData(int newPageNum) {
        String newUrl = String.format(Uri.URL_WIKIS_II, kind, value, order, newPageNum);
        StringRequest request = new StringRequest(newUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ListFoodForWiki listFood = gson.fromJson(response, ListFoodForWiki.class);
                foods = listFood.getFoods();
                adapter.add(foods,false);
                wiki_list_lv_id.onRefreshComplete();

            }
        }, null);
        request.setTag("loadNew");
        MyApp.getApp().getRequestQueue().add(request);

    }

    //填充数据
    private void fillDataSource(String url) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ListFoodForWiki listFood = gson.fromJson(response, ListFoodForWiki.class);
                foods = listFood.getFoods();
                adapter.add(foods,true);


            }
        }, null);
        request.setTag("list");
        MyApp.getApp().getRequestQueue().add(request);

    }

    //初始化控件
    private void initWidget() {
        wiki_list_title_id = (TextView) findViewById(R.id.wiki_list_title_id);
        wiki_list_lv_id = (PullToRefreshListView) findViewById(R.id.wiki_list_lv_id);
        wiki_list_order_cb_id = ((CheckBox) findViewById(R.id.wiki_list_order_cb_id));
        mRelativeLayoutId = ((RelativeLayout) findViewById(R.id.wiki_list_rl_id));
        wiki_list_changeorder_cb_id = ((CheckBox) findViewById(R.id.wiki_list_changeorder_cb_id));
        wiki_list_sub_id = ((TextView) findViewById(R.id.wiki_list_sub_id));
    }

    @Override
    protected void onDestroy() {

        MyApp.getApp().getRequestQueue().cancelAll("loadNew");
        MyApp.getApp().getRequestQueue().cancelAll("list");
        super.onDestroy();
    }


}
