package app.bai.com.foodpai.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.FoodCollect;

/**
 * Created by Administrator on 16-7-11.
 */
public class MyFoodCollcetAdapter extends BaseAdapter {

    private List<FoodCollect> foodCollect;
    private Context context;

    public MyFoodCollcetAdapter(List<FoodCollect> foodCollect, Context context) {
        this.foodCollect = foodCollect;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodCollect.size();
    }

    @Override
    public Object getItem(int i) {
        return foodCollect.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.food_collect_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) view.getTag());
        }
        viewHolder.tv_food_collection.setText(foodCollect.get(i).getName());
        viewHolder.tv_food_collection_calory.setText(foodCollect.get(i).getCalory());
        Glide.with(context).load(foodCollect.get(i).getImgUrl()).into(viewHolder.iv_food_collection);
        return view;
    }
    public void addAll(List<FoodCollect> dd){
        foodCollect.addAll(dd);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        private ImageView iv_food_collection;
        private TextView tv_food_collection;
        private TextView tv_food_collection_calory;

        public ViewHolder(View view) {
            iv_food_collection = (ImageView) view.findViewById(R.id.iv_food_collection_id);
            tv_food_collection = (TextView) view.findViewById(R.id.tv_food_collection_id);
            tv_food_collection_calory = (TextView) view.findViewById(R.id.tv_food_collection_calory_id);
        }

    }
}
