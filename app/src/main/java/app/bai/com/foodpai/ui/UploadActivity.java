package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.bai.com.foodpai.R;

public class UploadActivity extends AppCompatActivity {

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


    }
}
