package app.bai.com.foodpai.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import app.bai.com.foodpai.R;

public class AnalysisActivity extends AppCompatActivity {

    private Toolbar tb_analysis;
    private RelativeLayout serch_food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        tb_analysis = (Toolbar)findViewById(R.id.tb_analysis_id);
        tb_analysis.setTitle("");
        setSupportActionBar(tb_analysis);
        tb_analysis.setNavigationIcon(R.drawable.ic_back_dark);
        tb_analysis.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        serch_food = (RelativeLayout) findViewById(R.id.rl_serch_food_id);
        serch_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),FoodAnalysisiActivity.class);
                startActivity(intent);
            }
        });
    }
}
