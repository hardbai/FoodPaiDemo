package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.SearchResult;

/**
 * Created by Administrator on 2016/7/10.
 */
public class SearchResultAdapter extends MyAdapter<SearchResult.ItemsBean> {
    private ImageView iv_food;
    private TextView tv_name;
    private TextView tv_calory;
    private Context context;

    public SearchResultAdapter(List<SearchResult.ItemsBean> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;
    }

    @Override
    public void bindData(ViewHolder vh, SearchResult.ItemsBean itemsBean) {

        iv_food = ((ImageView) vh.getView(R.id.img_food));
         Glide.with(context).load(itemsBean.getThumb_image_url()).into(iv_food);
        tv_name = ((TextView) vh.getView(R.id.name));
        tv_name.setText(itemsBean.getName());
        tv_calory = ((TextView) vh.getView(R.id.calory));
        tv_calory.setText(itemsBean.getCalory()+" 千卡/100克");
    }

}