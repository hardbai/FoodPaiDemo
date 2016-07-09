package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import app.bai.com.foodpai.R;

public class UploadActivity extends AppCompatActivity {

    private RelativeLayout upload_my_upload_food;
    private RelativeLayout upload_draft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar upload_toolbar = (Toolbar) findViewById(R.id.tb_upload_id);
        upload_toolbar.setTitle("");
        setSupportActionBar(upload_toolbar);
        upload_toolbar.setNavigationIcon(R.drawable.ic_back_dark);
        upload_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //初始化界面控件
        initWidgets();
        //点击<我上传的食物>
        uploadFood();
        //点击<草稿箱>
        aboutDrafts();
    }

    private void aboutDrafts() {
        upload_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),DraftsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void uploadFood() {
        upload_my_upload_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),UploadFoodDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidgets() {
        upload_my_upload_food = (RelativeLayout) findViewById(R.id.upload_my_upload_food_id);
        upload_draft = (RelativeLayout) findViewById(R.id.upload_draft_id);
    }
    public void startUpload(View view){
        Intent intent = new Intent(getApplication(),UploadFoodActivity.class);
        startActivity(intent);
    }

}

