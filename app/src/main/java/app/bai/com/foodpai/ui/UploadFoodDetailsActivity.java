package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.bai.com.foodpai.R;

public class UploadFoodDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food_details);

        Toolbar  tb_my_upload_food = (Toolbar) findViewById(R.id.tb_my_upload_food_id);
        tb_my_upload_food.setTitle("");
        setSupportActionBar(tb_my_upload_food);
        tb_my_upload_food.setNavigationIcon(R.drawable.ic_back_dark);
        tb_my_upload_food.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
