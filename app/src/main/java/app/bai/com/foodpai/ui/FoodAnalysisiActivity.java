package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.bai.com.foodpai.R;

public class FoodAnalysisiActivity extends AppCompatActivity {
    private Toolbar tb_food_analysis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analysisi);
        tb_food_analysis = (Toolbar)findViewById(R.id.tb_food_analysis_id);
        tb_food_analysis.setTitle("");
        setSupportActionBar(tb_food_analysis);
        tb_food_analysis.setNavigationIcon(R.drawable.ic_back_dark);
        tb_food_analysis.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
