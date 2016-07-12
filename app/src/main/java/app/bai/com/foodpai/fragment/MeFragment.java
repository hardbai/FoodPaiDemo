package app.bai.com.foodpai.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import app.bai.com.foodpai.MyAdapter.MyAdapter;
import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.User;
import app.bai.com.foodpai.ui.AnalysisActivity;
import app.bai.com.foodpai.ui.CollectActivity;
import app.bai.com.foodpai.ui.LoginActivity;
import app.bai.com.foodpai.ui.UploadActivity;
import app.bai.com.foodpai.utils.MyListView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 86724 on 2016/7/5 0005.
 */
public class MeFragment extends BaseFragment {
    private View view;
    private String[] data;
    private PopupWindow mPopWindow;
    public static Button btn_exit;
    private static ImageView imageView;
    static Context context;
    private static Button login;
    public static Handler loginHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            User user = ((User) msg.obj);
            Glide.with(context).load(user.getUserIcon()).bitmapTransform(new CropCircleTransformation(context)).into(imageView);
            btn_exit.setVisibility(View.VISIBLE);
            login.setText(user.getUserName());
            login.setEnabled(false);
        }
    };
    private Intent intent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_fragment_layout, null);
        intent = new Intent(getContext(),LoginActivity.class);
        imageView = ((ImageView) view.findViewById(R.id.iv_login_id));
        btn_exit = (Button) view.findViewById(R.id.btn_exit_id);
        if(MyApp.getApp().config.getBoolean("isLogin",false)){
            btn_exit.setVisibility(View.VISIBLE);
        }else {
            btn_exit.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.ic_analyse_default).bitmapTransform(new CropCircleTransformation(getContext())).into(imageView);
        }
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeLogin();
            }
        });
        //登录操作
        aboutLogin();
        //关于<饮食分析>的操作
        aboutAnalysis();
        //关于<上传食物>的操作
        aboutUpload();
        //关于<我的收藏>的操作
        aboutCollect();
        //关于listView的操作
        aboutListView();

        context = getContext();
        //about(out 0f login);
        return view;
    }


    private void aboutLogin() {

        login = ((Button) view.findViewById(R.id.btn_login_id));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void aboutAnalysis() {
        View rl_analysis = view.findViewById(R.id.rl_analysis_id);
        rl_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApp.getApp().config.getBoolean("isLogin",false)){
                    Intent intent = new Intent(getContext(), AnalysisActivity.class);
                    startActivity(intent);
                }else {
                    startActivity(intent);
                }

            }
        });
    }
    private void aboutUpload() {
        View rl_upload = view.findViewById(R.id.rl_upload_id);
        rl_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApp.getApp().config.getBoolean("isLogin",false)){
                    Intent intent = new Intent(getContext(), UploadActivity.class);
                    startActivity(intent);
                }else {
                    startActivity(intent);
                }

            }
        });
    }
    private void aboutCollect() {
        View rl_collect = view.findViewById(R.id.rl_collect_id);
        rl_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApp.getApp().config.getBoolean("isLogin",false)){
                    Intent intent = new Intent(getContext(), CollectActivity.class);
                    startActivity(intent);
                }else {
                    startActivity(intent);
                }

            }
        });
    }
    public void removeLogin(){
        Glide.with(getContext()).load(R.drawable.ic_analyse_default).bitmapTransform(new CropCircleTransformation(getContext())).into(imageView);
        MyApp.getApp().config.edit().putBoolean("isLogin",false).commit();
        btn_exit.setVisibility(View.GONE);
        LoginActivity.removeHandler.sendEmptyMessage(1);
        login.setEnabled(true);
        login.setText("点击登录");
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
                        MyApp.getApp().showShare("FoodPai","http://www.boohee.com/","A app about delicious food!");
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
