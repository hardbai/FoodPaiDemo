package app.bai.com.foodpai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.exception.DbException;

import java.util.List;

import app.bai.com.foodpai.MyAdapter.MyFoodDetailsAdapter;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.UploadFood;

/**
 * Created by Administrator on 16-7-12.
 */
public class Upload_Fragment extends BaseFragment {

    private ListView analysis_my_upload_food;
    private List<UploadFood> food;
    private MyFoodDetailsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_upload_food,null);

        analysis_my_upload_food = (ListView) view.findViewById(R.id.analysis_my_upload_food_id);

        aboutListView();

        return view;

    }

    private void aboutListView() {

        try {
            food = MyApp.getApp().getDbUtils().findAll(UploadFood.class);
            adapter = new MyFoodDetailsAdapter(food, getContext());
            analysis_my_upload_food.setAdapter(adapter);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
