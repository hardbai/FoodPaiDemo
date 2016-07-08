package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.ListFoodForWiki;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 16-7-7.
 */
public class MyWikiListAdapter extends BaseAdapter{
    private Context mContext;
    private List<ListFoodForWiki.FoodsBean> foods;

    public MyWikiListAdapter(Context mContext, List<ListFoodForWiki.FoodsBean> foods) {
        this.mContext = mContext;
        this.foods = foods;
    }

    public MyWikiListAdapter() {
    }

    @Override
    public int getCount() {

        return foods.size();
    }

    @Override
    public Object getItem(int i) {
        return foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return foods.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewHolder holder;
        if(view == null){
            holder = new ListViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.wiki_list_food_item,null);
            holder.listViewHolder_iv_id = (ImageView) view.findViewById(R.id.wiki_list_item_iv_id);
            holder.listViewHolder_tv_name_id = (TextView) view.findViewById(R.id.wiki_list_item_name_iv_id);
            holder.listViewHolder_tv_heat_id = (TextView) view.findViewById(R.id.wiki_list_item_heat_iv_id);
            holder.listViewHolder_color_iv_id = (ImageView) view.findViewById(R.id.wiki_list_item_color_iv_id);
            view.setTag(holder);
        }else{
            holder = (ListViewHolder) view.getTag();
        }
        Glide.with(mContext).load(foods.get(i).getThumb_image_url()).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.listViewHolder_iv_id);
        holder.listViewHolder_tv_name_id.setText(foods.get(i).getName());
        holder.listViewHolder_tv_heat_id.setText(foods.get(i).getCalory());
        holder.listViewHolder_color_iv_id.setImageResource(R.drawable.ic_food_light_yellow);
        return view;
    }

    public void add(List<ListFoodForWiki.FoodsBean> dd,boolean isOrder) {
        if(isOrder==true){
            foods.clear();
        }
        foods.addAll(dd);
        notifyDataSetChanged();

    }
    public void reverseData(){
        Collections.reverse(foods);
        notifyDataSetChanged();
    }
}
class ListViewHolder{
    ImageView listViewHolder_iv_id;
    TextView listViewHolder_tv_name_id;
    TextView listViewHolder_tv_heat_id;
    ImageView listViewHolder_color_iv_id;
}
