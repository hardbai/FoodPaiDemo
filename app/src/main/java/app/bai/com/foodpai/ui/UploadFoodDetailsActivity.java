package app.bai.com.foodpai.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.exception.DbException;

import java.util.List;

import app.bai.com.foodpai.Model.Food;
import app.bai.com.foodpai.MyAdapter.MyFoodDetailsAdapter;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.UploadFood;

public class UploadFoodDetailsActivity extends AppCompatActivity {
    private ListView lv_upload_food_details_id;
    private List<UploadFood> food;
    private MyFoodDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food_details);

        Toolbar tb_my_upload_food = (Toolbar) findViewById(R.id.tb_my_upload_food_id);
        tb_my_upload_food.setTitle("");
        setSupportActionBar(tb_my_upload_food);
        tb_my_upload_food.setNavigationIcon(R.drawable.ic_back_dark);
        tb_my_upload_food.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lv_upload_food_details_id = (ListView) findViewById(R.id.lv_upload_food_details_id);
        aboutListView();
        lv_upload_food_details_id.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(UploadFoodDetailsActivity.this)
                        .setTitle("删除")
                        .setMessage("是否删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                UploadFood food = (UploadFood) adapter.getItem(i);
                                try {
                                    MyApp.getApp().getDbUtils().delete(food);
                                    aboutListView();
                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消", null).show();
                return false;
            }
        });
    }

    private void aboutListView() {
        //1、数据源
        try {
            food = MyApp.getApp().getDbUtils().findAll(UploadFood.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        //2、适配器
        adapter = new MyFoodDetailsAdapter(food, this);
        //设置适配器
        lv_upload_food_details_id.setAdapter(adapter);
    }
}
