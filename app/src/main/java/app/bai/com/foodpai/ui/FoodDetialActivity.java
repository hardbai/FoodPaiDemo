package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.FoodDetialAdapter;
import app.bai.com.foodpai.bean.FoodCollect;
import app.bai.com.foodpai.bean.FoodDetialForWiki;
import app.bai.com.foodpai.uri.Uri;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class FoodDetialActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private String code;//URL的code
    private String name;//食物的name
    private Toolbar food_detial_tb_id;
    private TextView food_detial_tb_title_id;
    private ImageView detial_food_name_iv_id;
    private TextView detial_food_name_tv_id;
    private TextView detial_food_cal_jo_tv_id;
    private Button detial_add_data_btn_id;
    private Button detial_add_compare_btn_id;
    private ImageView detial_food_reference_iv_id;
    private TextView detial_food_reference_multiple_tv_id;
    private TextView detial_food_reference_suggest_tv_id;
    private ListView detial_heat_lv_id;
    private CheckBox detial_open_cb_id;
    private TextView detial_element_heat_id;
    private TextView detial_element_heat_light_id;
    private TextView detial_element_protein_id;
    private TextView detial_element_protein_light_id;
    private TextView detial_element_fat_id;
    private TextView detial_element_fat_light_id;
    private TextView detial_element_hydrate_id;
    private TextView detial_element_hydrate_light_id;
    private TextView detial_element_df_id;
    private TextView detial_element_df_light_id;
    private TextView detial_element_more_tv_id;
    private TextView detial_suggest_title_id;
    private TextView detial_suggest_tv_id;
    private FoodDetialAdapter adapter;
    private FoodDetialForWiki food;
    private LinearLayout food_reference_id;
    private RadioGroup detial_radiogroup_id;
    private CheckBox detial_tb_collect_cb_id;
    private FoodCollect find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detial);
        //思路
        //接受上个页面传来的数据
        receiveData();
        //初始化控件
        initWidget();
        //Toolbar的初始化
        initToolbar();
        //下载食物详情数据
        downloadFoodData();



    }
    //初始化Toolbar
    private void initToolbar() {
        food_detial_tb_id.setNavigationIcon(R.drawable.ic_back_dark);
        food_detial_tb_id.setTitle("");
        food_detial_tb_title_id.setText(name);
        food_detial_tb_id.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        try {
            find = MyApp.getApp().getDbUtils().findById(FoodCollect.class,code);
            if(find!=null){
                detial_tb_collect_cb_id.setChecked(true);
            }else{
                detial_tb_collect_cb_id.setChecked(false);
            }
            detial_tb_collect_cb_id.setClickable(false);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    //下载食物详情数据
    private void downloadFoodData() {
        String urlStr = String.format(Uri.URL_WIKIS_III,code);
        StringRequest stringRequest = new StringRequest(urlStr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                food = gson.fromJson(response, FoodDetialForWiki.class);
                //关于控件的操作
                aboutWidget();

            }
        }, null);
        stringRequest.setTag("detial");
        MyApp.getApp().getRequestQueue().add(stringRequest);
    }


    //关于控件的操作
    private void aboutWidget() {
        detial_tb_collect_cb_id.setClickable(true);
        detial_tb_collect_cb_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String code = food.getCode();
                    String name = food.getName();
                    String calory = food.getCalory();
                    String imgUrl = food.getThumb_image_url();
                    FoodCollect fCollect = new FoodCollect(code,name,calory,imgUrl);
                    if(b){
                        try {
                            MyApp.getApp().getDbUtils().saveOrUpdate(fCollect);
                            Log.i("TAG", "onCheckedChanged: 收藏"+code+name+calory+imgUrl);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {

                            MyApp.getApp().getDbUtils().delete(fCollect);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                     }


            }
        });
        Glide.with(this).load(food.getThumb_image_url()).bitmapTransform(new CropCircleTransformation(this)).into(detial_food_name_iv_id);
        detial_food_name_tv_id.setText(food.getName());
        detial_food_cal_jo_tv_id.setText(food.getCalory()+"千卡/每100克");
        aboutDetialReference();
        aboutDetial_heat_lv_id();
        aboutElement();
        aboutSuggest();

        aboutDetial_radiogroup_id();
    }
    //关于radiogroup的操作
    private void aboutDetial_radiogroup_id() {
        detial_radiogroup_id.setOnCheckedChangeListener(this);
    }


    public void toMore(View view){
        MyApp.getApp().setIngredient(food.getIngredient());
        Intent intent = new Intent(this,MoreElementActivity.class);
        startActivity(intent);
    }

    //关于推荐的操作
    private void aboutSuggest() {
        int light = food.getHealth_appraise().get(0).getLight();
        switch (light) {
            case 1:
                detial_suggest_title_id.setTextColor(Color.GREEN);
                break;
            case 2:
                detial_suggest_title_id.setTextColor(Color.RED);
                break;
        }
        detial_suggest_tv_id.setText(food.getHealth_appraise().get(0).getAppraise());
    }

    //关于营养元素的操作
    private void aboutElement() {
         detial_element_heat_id.setText(food.getCalory());
        detial_element_heat_light_id.setText(food.getLights().getCalory());
        detial_element_protein_id.setText(food.getProtein());
        detial_element_protein_light_id.setText(food.getLights().getFat());
        detial_element_fat_id.setText(food.getFat());
        detial_element_fat_light_id.setText(food.getLights().getProtein());
        detial_element_hydrate_id.setText(food.getCarbohydrate());
        detial_element_hydrate_light_id.setText(food.getLights().getCarbohydrate());
        detial_element_df_id.setText(food.getFiber_dietary());
        detial_element_df_light_id.setText(food.getLights().getFiber_dietary());
    }

    //关于参考的操作
    private void aboutDetialReference() {
        if(food.getCompare().getTarget_image_url()==null){
            food_reference_id.setVisibility(View.GONE);
        }else{
            Glide.with(this).load(food.getCompare().getTarget_image_url()).into(detial_food_reference_iv_id);
            detial_food_reference_multiple_tv_id.setText("X"+food.getCompare().getAmount1());
            detial_food_reference_suggest_tv_id.setText(food.getCompare().getAmount0()+food.getCompare().getUnit0()+food.getName()+"~"+food.getCompare().getAmount1()+food.getCompare().getUnit1()+food.getCompare().getTarget_name());
        }
    }

    //关于热量listview的操作
    private void aboutDetial_heat_lv_id() {
        //数据源
         List<FoodDetialForWiki.UnitsBean> units = food.getUnits();
        if(units.size()<=2){
            detial_open_cb_id.setVisibility(View.GONE);
        }
        //适配器
        adapter = new FoodDetialAdapter(units,this);
        detial_heat_lv_id.setAdapter(adapter);

        detial_open_cb_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //设置listview的高度
                setListHeight(b);
            }
        });

    }
    //设置listview的高度
    private void setListHeight(Boolean b) {
        ListAdapter listAdapter = detial_heat_lv_id.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, detial_heat_lv_id);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = detial_heat_lv_id.getLayoutParams();
        if(b){
            detial_open_cb_id.setText("收起");
            params.height = 10+totalHeight + (detial_heat_lv_id.getDividerHeight() * (listAdapter.getCount() - 1));
        }else{
            detial_open_cb_id.setText("展开");
            int twoItems = 0;
            for(int i = 0;i<2;i++){
                View listItem = listAdapter.getView(i, null, detial_heat_lv_id);
                listItem.measure(0,0);
                twoItems = listItem.getMeasuredHeight()*2;
            }
            params.height = 10+twoItems + (detial_heat_lv_id.getDividerHeight() * 2);
        }
        detial_heat_lv_id.setLayoutParams(params);
    }

    //初始化控件
    private void initWidget() {
        food_detial_tb_id = ((Toolbar) findViewById(R.id.food_detial_tb_id));
        food_detial_tb_title_id = ((TextView) findViewById(R.id.food_detial_tb_title_id));
        detial_food_name_iv_id = ((ImageView) findViewById(R.id.detial_food_name_iv_id));
        detial_food_name_tv_id = ((TextView) findViewById(R.id.detial_food_name_tv_id));
        detial_food_cal_jo_tv_id = ((TextView) findViewById(R.id.detial_food_cal_jo_tv_id));
        detial_add_data_btn_id = ((Button) findViewById(R.id.detial_add_data_btn_id));
        detial_add_compare_btn_id = ((Button) findViewById(R.id.detial_add_compare_btn_id));
        detial_food_reference_iv_id = ((ImageView) findViewById(R.id.detial_food_reference_iv_id));
        detial_food_reference_multiple_tv_id = ((TextView) findViewById(R.id.detial_food_reference_multiple_tv_id));
        detial_food_reference_suggest_tv_id = ((TextView) findViewById(R.id.detial_food_reference_suggest_tv_id));
        detial_heat_lv_id = ((ListView) findViewById(R.id.detial_heat_lv_id));
        detial_open_cb_id = ((CheckBox) findViewById(R.id.detial_open_cb_id));
        detial_element_heat_id = ((TextView) findViewById(R.id.detial_element_heat_id));
        detial_element_heat_light_id = ((TextView) findViewById(R.id.detial_element_heat_light_id));
        detial_element_protein_id = ((TextView) findViewById(R.id.detial_element_protein_id));
        detial_element_protein_light_id = ((TextView) findViewById(R.id.detial_element_protein_light_id));
        detial_element_fat_id = ((TextView) findViewById(R.id.detial_element_fat_id));
        detial_element_fat_light_id = ((TextView) findViewById(R.id.detial_element_fat_light_id));
        detial_element_hydrate_id = ((TextView) findViewById(R.id.detial_element_hydrate_id));
        detial_element_hydrate_light_id = ((TextView) findViewById(R.id.detial_element_hydrate_light_id));
        detial_element_df_id = ((TextView) findViewById(R.id.detial_element_df_id));
        detial_element_df_light_id = ((TextView) findViewById(R.id.detial_element_df_light_id));
        detial_element_more_tv_id = ((TextView) findViewById(R.id.detial_element_more_tv_id));
        detial_suggest_title_id = ((TextView) findViewById(R.id.detial_suggest_title_id));
        detial_suggest_tv_id = ((TextView) findViewById(R.id.detial_suggest_tv_id));
        food_reference_id = ((LinearLayout) findViewById(R.id.detial_reference_id));
        detial_radiogroup_id = ((RadioGroup) findViewById(R.id.detial_radiogroup_id));
        detial_tb_collect_cb_id = ((CheckBox) findViewById(R.id.detial_tb_collect_cb_id));

    }

    //接受上个页面传来的数据
    private void receiveData() {
        Intent receiveIntent = getIntent();
        name = receiveIntent.getStringExtra("name");
        code = receiveIntent.getStringExtra("code");

    }

    @Override
    protected void onDestroy() {
        MyApp.getApp().getRequestQueue().cancelAll("detial");
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.detial_ca_rb_id:
                detial_food_cal_jo_tv_id.setText(food.getCalory()+"千卡/每100克");
                detial_element_heat_id.setText(food.getCalory());
                break;
            case R.id.detial_jo_rb_id:
                detial_food_cal_jo_tv_id.setText(food.getProtein()+"千焦/每100克");
                detial_element_heat_id.setText(food.getProtein());
                break;

        }
    }
}

