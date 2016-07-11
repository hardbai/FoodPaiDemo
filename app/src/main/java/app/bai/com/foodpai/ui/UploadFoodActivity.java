package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.exception.DbException;

import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.UploadFood;

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
        et_brand = (EditText) findViewById(R.id.et_brand_id);
        et_name = (EditText) findViewById(R.id.et_name_id);
        et_otherName = (EditText) findViewById(R.id.et_otherName_id);
    }

    public void finish(View view) {
        //获取输入的信息
        String brand = et_brand.getText().toString();
        String name = et_name.getText().toString();
        String otherName = et_otherName.getText().toString();
        if (brand != null && brand.length() > 0 && name != null && name.length() > 0 && otherName != null && otherName.length() > 0) {
            UploadFood food = new UploadFood(brand, name, otherName);
            if (checkDeails(food)) {
                try {
                    MyApp.getApp().getDbUtils().saveOrUpdate(food);
                    Toast.makeText(getApplication(), "上传成功!", Toast.LENGTH_LONG).show();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(UploadFoodActivity.this, "该食物已存在", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "请完善食物信息！", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkDeails(UploadFood food) {
        try {

            List<UploadFood> datas = MyApp.getApp().getDbUtils().findAll(UploadFood.class);
            for (UploadFood ff : datas) {
                if (ff.getBrand().equals(food.getBrand()) && ff.getName().equals(food.getName()) && ff.getOtherName().equals(food.getOtherName())) {
                    return false;
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return true;
    }
}