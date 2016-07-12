package app.bai.com.foodpai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.exception.DbException;

import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.adapter.showSearchHistoryAdapter;
import app.bai.com.foodpai.bean.Search;
import app.bai.com.foodpai.ui.SearchDitalActivity;

/**
 * Created by Administrator on 16-7-12.
 */
public class Search_Fragment extends BaseFragment {

    private ListView analysis_search_record;
    private TextView clear_record;
    private List<Search> data;
    private showSearchHistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.analysis_search,null);

        analysis_search_record = (ListView) view.findViewById(R.id.analysis_search_record_id);

        clear_record = (TextView) view.findViewById(R.id.clear_record_id);

        aboutListView();

        clear_record.setOnClickListener(new View.OnClickListener() {
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


        return view;
    }

    private void aboutListView() {

        try {
            data = MyApp.getApp().getDbUtils().findAll(Search.class);
            adapter = new showSearchHistoryAdapter(data,getContext(),R.layout.searchhistory_lv_item);
            analysis_search_record.setAdapter(adapter);

            analysis_search_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getContext(),SearchDitalActivity.class);
                    intent.putExtra("name",adapter.getItem(i).getName());
                    Log.d("name","--------"+adapter.getItem(i).getName());
                    startActivity(intent);
                }
            });
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
