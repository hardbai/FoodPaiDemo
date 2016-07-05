package app.bai.com.foodpai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.bai.com.foodpai.R;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class GuangFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guang_fragment_layout,null);
        return view;
    }
}
