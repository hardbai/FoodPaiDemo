package app.bai.com.foodpai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.CustomView.MyGridView;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.MyWikiAdapterForPrimary;
import app.bai.com.foodpai.bean.FoodForWiki;
import app.bai.com.foodpai.ui.CompareDetialActivity;
import app.bai.com.foodpai.ui.WikiListActivity;
import app.bai.com.foodpai.uri.Uri;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class WikiFragment extends BaseFragment {
    private List<FoodForWiki.GroupBean> data;
    private MyWikiAdapterForPrimary adapterForGroup;
    private MyWikiAdapterForPrimary adapterForBrand;
    private MyWikiAdapterForPrimary adapterForRes;
    private LinearLayout wiki_compare_ll_id;
    private MyGridView wiki_group_gv_id;
    private MyGridView wiki_brand_gv_id;
    private MyGridView wiki_res_gv_id;
    private View wiki_group_ll_id;
    private View wiki_brand_ll_id;
    private View wiki_res_ll_id;
    private PullToRefreshScrollView pull_refresh_scrollview;
    private ScrollView mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wiki_fragment_layout,null);
        pull_refresh_scrollview = ((PullToRefreshScrollView) view.findViewById(R.id.pull_refresh_scrollview));
        wiki_compare_ll_id = (LinearLayout) view.findViewById(R.id.wiki_compare_ll_id);
        wiki_group_gv_id = (MyGridView) view .findViewById(R.id.wiki_group_gv_id);
        wiki_brand_gv_id= (MyGridView) view .findViewById(R.id.wiki_brand_gv_id);
        wiki_res_gv_id = (MyGridView) view .findViewById(R.id.wiki_res_gv_id);
        //给食物对比添加监听器
        wiki_compare_ll_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.wiki_compare_ll_id){
                    Intent intent = new Intent(getContext(), CompareDetialActivity.class);
                    startActivity(intent);
                }
            }
        });
        wiki_group_ll_id = ((LinearLayout) view.findViewById(R.id.wiki_group_ll_id));
        wiki_brand_ll_id = ((LinearLayout) view.findViewById(R.id.wiki_brand_ll_id));
        wiki_res_ll_id = ((LinearLayout) view.findViewById(R.id.wiki_res_ll_id));
        wiki_group_ll_id.setVisibility(View.GONE);
        wiki_brand_ll_id.setVisibility(View.GONE);
        wiki_res_ll_id.setVisibility(View.GONE);


        //思路:
        //1)准备数据
        final String url = Uri.URL_WIKIS_I;
        data = new ArrayList<>();
        initDataSource(url);
        //2)准备适配器
        pull_refresh_scrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pull_refresh_scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                initDataSource(url);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            getActivity().runOnUiThread(new Thread(){
                                @Override
                                public void run() {
                                    pull_refresh_scrollview.onRefreshComplete();
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

        });
        mScrollView = pull_refresh_scrollview.getRefreshableView();
        adapterForGroup = new MyWikiAdapterForPrimary(0,getContext(),data);
        adapterForBrand = new MyWikiAdapterForPrimary(1,getContext(),data);
        adapterForRes = new MyWikiAdapterForPrimary(2,getContext(),data);
        wiki_group_gv_id.setAdapter(adapterForGroup);
        wiki_brand_gv_id.setAdapter(adapterForBrand);
        wiki_res_gv_id.setAdapter(adapterForRes);
        //3)添加监听器
        wiki_group_gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent groupIntent = new Intent(getActivity(), WikiListActivity.class);

                groupIntent.putExtra("name",data.get(0).getCategories().get(i).getName());
                groupIntent.putExtra("kind","group");
                groupIntent.putExtra("value",i+1);
                ArrayList<String> subNames = new ArrayList<String>();
                List<FoodForWiki.GroupBean.CategoriesBean.SubCategoriesBean> subs = data.get(0).getCategories().get(i).getSub_categories();
                for(int count = 0;count<subs.size();count++){
                    subNames.add(subs.get(count).getName());
                }
                groupIntent.putStringArrayListExtra("sub",subNames);
                startActivity(groupIntent);
            }
        });
        wiki_brand_gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent groupIntent = new Intent(getActivity(), WikiListActivity.class);

                groupIntent.putExtra("name",data.get(1).getCategories().get(i).getName());
                groupIntent.putExtra("kind","brand");
                groupIntent.putExtra("value",i+1);
                ArrayList<String> subNames = new ArrayList<String>();
                List<FoodForWiki.GroupBean.CategoriesBean.SubCategoriesBean> subs = data.get(1).getCategories().get(i).getSub_categories();
                for(int count = 0;count<subs.size();count++){
                    subNames.add(subs.get(count).getName());
                }
                groupIntent.putStringArrayListExtra("sub",subNames);
                startActivity(groupIntent);
            }
        });
        wiki_res_gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent groupIntent = new Intent(getActivity(), WikiListActivity.class);

                groupIntent.putExtra("name",data.get(2).getCategories().get(i).getName());
                groupIntent.putExtra("kind","restaurant");
                groupIntent.putExtra("value",i+1);
                ArrayList<String> subNames = new ArrayList<String>();
                List<FoodForWiki.GroupBean.CategoriesBean.SubCategoriesBean> subs = data.get(2).getCategories().get(i).getSub_categories();
                for(int count = 0;count<subs.size();count++){
                    subNames.add(subs.get(count).getName());
                }
                groupIntent.putStringArrayListExtra("sub",subNames);
                startActivity(groupIntent);
            }
        });

        return view;
    }

    /**
     * 准备数据
     */
    private void initDataSource(String url) {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                FoodForWiki food = gson.fromJson(response,FoodForWiki.class);
                data = food.getGroup();
                wiki_group_ll_id.setVisibility(View.VISIBLE);
                wiki_brand_ll_id.setVisibility(View.VISIBLE);
                wiki_res_ll_id.setVisibility(View.VISIBLE);

//                food.getGroup();
                adapterForGroup.addAll(food.getGroup());
                adapterForBrand.addAll(food.getGroup());
                adapterForRes.addAll(food.getGroup());

            }
        }, null);
        stringRequest.setTag("qx");
        MyApp.getApp().getRequestQueue().add(stringRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApp.getApp().getRequestQueue().cancelAll("qx");
    }
}
