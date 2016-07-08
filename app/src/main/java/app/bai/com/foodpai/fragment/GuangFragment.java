package app.bai.com.foodpai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.Model.Banners;
import app.bai.com.foodpai.Model.Food;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.ShowListViewAdapter;
import app.bai.com.foodpai.adapter.ShowViewPagerAdapter;
import app.bai.com.foodpai.ui.ShowDetailsActivity;
import app.bai.com.foodpai.uri.Uri;
import com.liucanwen.app.headerfooterrecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.liucanwen.app.headerfooterrecyclerview.RecyclerViewUtils;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class GuangFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private Food food;
    private ShowListViewAdapter showListViewAdapter;
    private ArrayList<Food.FeedsBean> listViewData;
    private LinearLayoutManager layoutManager;
    private ShowViewPagerAdapter viewPagerAdapter;
    private ViewPager showViewpager;
    private ImageView image;

    //接收到handler发送消息后切换下一个ViewPager页面
    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showViewpager.setCurrentItem((showViewpager.getCurrentItem()+1)%4);
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    public static GuangFragment newInstance(){
        GuangFragment guangFrament = new GuangFragment();
        return  guangFrament;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.guang_fragment_layout,null);
        listViewData = new ArrayList<Food.FeedsBean>();

        initView();
        showListViewAdapter = new ShowListViewAdapter(listViewData , getContext());
        //设置头适配器
        HeaderAndFooterRecyclerViewAdapter adapter = new HeaderAndFooterRecyclerViewAdapter(showListViewAdapter);

        //RecyclerView布局管理器
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //ViewPager适配器实例化
        viewPagerAdapter = new ShowViewPagerAdapter(new ArrayList<ImageView>());


        //View headerView = inflater.inflate(R.layout.show_list_header_layout,null);

        //得到头布局
        View headerView = inflater.inflate(R.layout.show_list_header_layout,null);

        //得到头布局中的控件（ViewPager）
        showViewpager = ((ViewPager) headerView.findViewById(R.id.showViewPager));

        //给ViewPager设置适配器
        showViewpager.setAdapter(viewPagerAdapter);

        //设置ViewPager转场动画
        showViewpager.setPageTransformer(true,new FlipHorizontalTransformer());


        recyclerView.setAdapter(adapter);

        //把头布局设置给RecyclerView
        RecyclerViewUtils.setHeaderView(recyclerView,headerView);

        //设置RecyclerView点击事件
        showListViewAdapter.setOnItemClickListener(new ShowListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                Intent intent = new Intent(getContext(), ShowDetailsActivity.class);
                String str = listViewData.get(position-1).getLink();
                String titleStr = listViewData.get(position-1).getTitle();
                int id = listViewData.get(position-1).getId();
                intent.putExtra("title",titleStr);
                intent.putExtra("id",id);
                intent.putExtra("link",str);
                startActivity(intent);
            }
        });


        //handler延迟3秒发送消息
        handler.sendEmptyMessageDelayed(0,3000);

        loadListViewData();//请求ListView数据

        upLoadData(); //上拉加载

        downRefresh();//下拉刷新

        loadViewPageData();//加载ViewPager数据

        return view;
    }


    private void initView() {
        recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView));
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout));

    }

    /*
    *
    * 请求ViewPager数据
    * */
    private void loadViewPageData() {
        StringRequest stringRequest = new StringRequest(Uri.URL_SHOW_IMAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Banners banners = gson.fromJson(response,Banners.class);
                List<ImageView> list = new ArrayList<ImageView>();
                for(int i=0;i<banners.getBanners().size();i++){
                    ImageView imageView = new ImageView(getContext());

                    Glide.with(getContext()).load(banners.getBanners().get(i).getImage_key()).into(imageView);
                    list.add(imageView);
                }
                viewPagerAdapter.addAll(list);

            }
        }, null);
        stringRequest.setTag("qx");
        MyApp.getApp().getRequestQueue().add(stringRequest);
    }

    /*
    *
    * 请求ListView数据
    *
    * */
    private void loadListViewData() {
        if(page > 20){
            Toast.makeText(getContext(),"最后一页啦！！！！", Toast.LENGTH_LONG).show();
        }
        else {
            StringRequest stringRequest = new StringRequest(Uri.URL_SHOW_LIST + page, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    food = gson.fromJson(response, Food.class);
                    showListViewAdapter.addAll(food.getFeeds());
                    listViewData.addAll(food.getFeeds());
                    Log.d("t", "------" + food.getFeeds());
                }
            }, null);
            MyApp.getApp().getRequestQueue().add(stringRequest);
        }
    }

    /*
    *
    * 加载下一页
    *
    * */
    private void upLoadData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(layoutManager.findLastVisibleItemPosition() == showListViewAdapter.getItemCount()) {
                    Log.d("count","---------------------"+showListViewAdapter.getItemCount());
                    page ++;
                    loadListViewData();
                }
            }
        });
    }

    /*
    *
    * 下拉刷新
    * */
    private void downRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                showListViewAdapter.clear();
                loadListViewData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApp.getApp().getRequestQueue().cancelAll("qx");
    }
}
