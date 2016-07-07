package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,PlatformActionListener {

    private Toolbar toolbar;
    private ImageView qq;
    private ImageView weChat;
    private ImageView weibo;
    private ImageView bohe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_login);
        initView();
        initToolbar();
        listenner();
    }

    private void listenner() {
        qq.setOnClickListener(this);
        weChat.setOnClickListener(this);
        weibo.setOnClickListener(this);
        bohe.setOnClickListener(this);
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        toolbar = ((Toolbar) findViewById(R.id.login_toolBar));
        qq = ((ImageView) findViewById(R.id.login_qq_icon));
        weChat = ((ImageView) findViewById(R.id.login_weixin_icon));
        weibo = ((ImageView) findViewById(R.id.login_weibo_icon));
        bohe = ((ImageView) findViewById(R.id.login_bohe_icon));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_qq_icon:
                loginDeal(new QQ(this));
                break;
            case R.id.login_weixin_icon:
                loginDeal(new Wechat(this));
                break;
            case R.id.login_weibo_icon:
                loginDeal(new SinaWeibo(this));
                break;
            case R.id.login_bohe_icon:
                loginDeal(new QZone(this));
                break;

        }
    }

    /**
     * 授权成功
     * @param platform:平台信息
     * @param i:标注功能【1.要功能不要数据，2.要数据不要功能】
     * @param hashMap:用户的数据
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if(i == Platform.ACTION_USER_INFOR)//要数据不要功能
        {
            Set<Map.Entry<String, Object>> entries = hashMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Log.i("info","key:"+entry.getKey()+"\tvalue:"+entry.getValue());
            }
            String icon = platform.getDb().getUserIcon();
            String name = platform.getDb().getUserName();
            MyApp.isLogin = true;

            LoginActivity.this.finish();
        }
        else if(i == Platform.ACTION_AUTHORIZING)//要功能不要数据
        {
            //跳转到成功认证的界面
            Intent intent = new Intent(getApplication(),ContentActivity.class);
            intent.putExtra("login",true);
            startActivity(intent);
        }
    }



    /**
     * 授权失败
     * @param platform
     * @param i
     * @param throwable:异常信息
     */
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
    }



    /**
     * 取消登陆
     * @param platform
     * @param i
     */
    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this,"取消登录",Toast.LENGTH_LONG).show();
    }
    public void loginDeal(Platform platform)
    {
        String userId = platform.getDb().getUserId();
        if(!TextUtils.isEmpty(userId))
        {
            //已经登陆过,直接跳转到你需要跳转的处理页面
            MyApp.isLogin = true;
            Toast.makeText(this,"您已登录",Toast.LENGTH_LONG).show();
        }
        else{
            //注册授权监听
            platform.setPlatformActionListener(this);
            //认证用户合法信息【要功能不要数据】
//            platform.authorize();
            platform.showUser(null);
            
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
