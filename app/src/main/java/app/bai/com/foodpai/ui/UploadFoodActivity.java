package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.bai.com.foodpai.R;

public class UploadFoodActivity extends AppCompatActivity {
    private EditText et_brand;
    private EditText et_name;
    private EditText et_otherName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food);
        Toolbar tb_upload_food = (Toolbar) findViewById(R.id.tb_upload_food_id);
        tb_upload_food.setTitle("");
        setSupportActionBar(tb_upload_food);
        tb_upload_food.setNavigationIcon(R.drawable.ic_back_dark);
        tb_upload_food.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initWidgets();
    }

    private void initWidgets() {
        EditText et_brand = (EditText) findViewById(R.id.et_brand_id);
        EditText et_name = (EditText) findViewById(R.id.et_name_id);
        EditText et_otherName = (EditText) findViewById(R.id.et_otherName_id);
    }

    public void finish(View view){
        Toast.makeText(getApplication(),"上传成功!",Toast.LENGTH_LONG).show();
    }
}
