package app.bai.com.foodpai.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.UploadFood;

/**
 * Created by Administrator on 16-7-10.
 */
public class MyFoodDetailsAdapter extends BaseAdapter {
    private List<UploadFood> food;
    private Context context;

    public MyFoodDetailsAdapter(List<UploadFood> food, Context context) {
        this.food = food;
        this.context = context;
    }

    @Override
    public int getCount() {
        return food.size();
    }

    @Override
    public Object getItem(int i) {
        return food.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.uploadfooddetails, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.brand.setText(food.get(i).getBrand());
        viewHolder.name.setText(food.get(i).getName());
        viewHolder.otherName.setText(food.get(i).getOtherName());
        return view;
    }

    public class ViewHolder {
        private final TextView brand;
        private final TextView name;
        private final TextView otherName;

        public ViewHolder(View view) {
            brand = ((TextView) view.findViewById(R.id.tv_food_details_brand_id));
            name = ((TextView) view.findViewById(R.id.tv_food_details_name_id));
            otherName = ((TextView) view.findViewById(R.id.tv_food_details_otherName_id));
        }
    }
}
