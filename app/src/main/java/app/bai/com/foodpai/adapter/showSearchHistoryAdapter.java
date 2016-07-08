package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.Search;

/**
 * Created by Administrator on 2016/7/8.
 */
public class showSearchHistoryAdapter extends MyAdapter<Search> {
    private TextView foodName;
    private List<Search> data;
    public showSearchHistoryAdapter(List<Search> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.data = data;
    }



    @Override
    public void bindData(ViewHolder vh, Search search) {
        foodName = ((TextView) vh.getView(R.id.tv_foodName));
        foodName.setText(search.getName());
    }
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }
}
