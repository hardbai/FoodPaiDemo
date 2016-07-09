package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.bai.com.foodpai.R;

public class DraftsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);

        Toolbar tb_drafts = (Toolbar) findViewById(R.id.tb_drafts_id);
        tb_drafts.setTitle("");
        setSupportActionBar(tb_drafts);
        tb_drafts.setNavigationIcon(R.drawable.ic_back_dark);
        tb_drafts.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
