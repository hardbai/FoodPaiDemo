package app.bai.com.foodpai.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.Toast;

import app.bai.com.foodpai.MyAdapter.MyAdapter;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.utils.MyListView;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class MeFragment extends BaseFragment {
    private View view;
    private String[] data;
    private PopupWindow mPopWindow;

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

        MyAdapter adapter = new MyAdapter(getActivity(), data);
        //设置适配器
        lv.setAdapter(adapter);

        //给ListView添加监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (data[i]) {

                    case "账号安全":
                        Toast.makeText(getContext(), "账号安全", Toast.LENGTH_LONG).show();
                        break;
                    case "清除缓存":
                        Toast.makeText(getContext(), "清除缓存", Toast.LENGTH_LONG).show();
                        break;
                    case "给我们提个建议":
                        Toast.makeText(getContext(), "给我们提个建议", Toast.LENGTH_LONG).show();
                        break;
                    case "评个分吧":
                        Toast.makeText(getContext(), "评个分吧", Toast.LENGTH_LONG).show();
                        break;
                    case "将食物派分享给朋友们":
                        Toast.makeText(getContext(), "将食物派分享给朋友们", Toast.LENGTH_LONG).show();
                        break;
                    case "HealthKit设置":
                        //点击弹出popupwindow
                       showPopupWindow();
                        //Toast.makeText(getContext(), "HealthKit设置", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    //popupwindow显示和点击屏幕消失操作
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.me_fragment_layout, null);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
    }
