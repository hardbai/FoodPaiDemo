package app.bai.com.foodpai.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 16-7-6.
 */
public class MyGridView extends GridView{

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        int heightSpec;
//
//        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
//            heightSpec = MeasureSpec.makeMeasureSpec(
//                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        }
//        else {
//            heightSpec = heightMeasureSpec;
//        }
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);



    }
}
