package app.bai.com.foodpai.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyAdapter.MyFoodCollcetAdapter;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.FoodCollect;
import app.bai.com.foodpai.ui.FoodDetialActivity;

/**
 * Created by Administrator on 16-7-11.
 */
public class Collec_food_Fragment extends BaseFragment {

    private ListView lv_collect_food;
    private List<FoodCollect> foodCollect;
    private MyFoodCollcetAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.collect_food_fragment, null);

        lv_collect_food = (ListView) view.findViewById(R.id.lv_collect_food_id);

        aboutListView();

        lv_collect_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(), FoodDetialActivity.class);
                intent.putExtra("code",((FoodCollect) adapter.getItem(i)).getCode());
                intent.putExtra("name",((FoodCollect) adapter.getItem(i)).getName());
                startActivity(intent);

            }
        });

        lv_collect_food.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                new AlertDialog.Builder(getContext())
                        .setTitle("删除")
                        .setMessage("是否确认删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {

                                FoodCollect foodCollect = ((FoodCollect) adapter.getItem(i));

                                try {
                                    MyApp.getApp().getDbUtils().delete(foodCollect);

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

        return view;
    }

    private void aboutListView() {


        adapter = new MyFoodCollcetAdapter(new ArrayList<FoodCollect>(), getContext());

        //3.设置适配器
        lv_collect_food.setAdapter(adapter);

        //1.数据源

        try {
            foodCollect = MyApp.getApp().getDbUtils().findAll(FoodCollect.class);

            adapter.addAll(foodCollect);

        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
