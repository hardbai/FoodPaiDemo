package app.bai.com.foodpai.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import app.bai.com.foodpai.MyAdapter.MyPaperAdapter;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.Collect;
import app.bai.com.foodpai.ui.ShowDetailsActivity;

/**
 * Created by Administrator on 16-7-8.
 */
public class Collec_Fragment extends BaseFragment {

    private ListView lv_collect;
    private List<Collect> datas;
    private MyPaperAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect_fragment, null);
        lv_collect = (ListView) view.findViewById(R.id.lv_collect_id);

        //关于ListView的操作
        aboutListView();

        lv_collect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(), ShowDetailsActivity.class);
                intent.putExtra("link", ((Collect) adapter.getItem(i)).getUrl());
                intent.putExtra("title",((Collect) adapter.getItem(i)).getTitle());
                startActivity(intent);
            }
        });

        lv_collect.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                new AlertDialog.Builder(getContext())
                        .setTitle("删除")
                        .setMessage("是否确认删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {

                                Collect collect = ((Collect) adapter.getItem(i));

                                try {

                                    MyApp.getApp().getDbUtils().delete(collect);

                                    aboutListView();

                                } catch (DbException e) {

                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消", null).show();

                return true;
            }
        });

        return view;
    }

    public void aboutListView() {
        //1.数据源
        datas = new ArrayList<>();

        try {
            datas = MyApp.getApp().getDbUtils().findAll(Collect.class);

            Log.i("-----", "onActivityCreated: " + datas.size());

        } catch (DbException e) {

            e.printStackTrace();

        }
        //2.适配器
        adapter = new MyPaperAdapter(datas, getContext());

        //设置适配器
        lv_collect.setAdapter(adapter);

    }

}
