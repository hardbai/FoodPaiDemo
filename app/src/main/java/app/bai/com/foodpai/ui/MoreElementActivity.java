package app.bai.com.foodpai.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import app.bai.com.foodpai.R;

public class MoreElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_element);
        PullToRefreshScrollView scrollView = null;
    }
}
