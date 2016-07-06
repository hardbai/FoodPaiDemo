package app.bai.com.foodpai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import app.bai.com.foodpai.MyAdapter.MyAdapter;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.utils.MyListView;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class MeFragment extends BaseFragment {
    private View view;
    private String[] data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_fragment_layout, null);

        aboutListView();
        return view;
    }

    private void aboutListView() {

        //获取所需控件
        MyListView lv = (MyListView) view.findViewById(R.id.lv_id);
        //准备数据源
        data = getResources().getStringArray(R.array.choices);
        //定义适配器

        MyAdapter adapter = new MyAdapter(getActivity(),data);
        //设置适配器
        lv.setAdapter(adapter);
    }

}
