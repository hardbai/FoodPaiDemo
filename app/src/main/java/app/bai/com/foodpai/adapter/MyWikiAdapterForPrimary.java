package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.FoodForWiki;

/**
 * Created by Administrator on 16-7-6.
 */
public class MyWikiAdapterForPrimary extends BaseAdapter{
    private List<FoodForWiki.GroupBean> data;
    private Context mContext;
    private int groupIndex;

    /**
     * 构造函数
     */
    public MyWikiAdapterForPrimary() {
    }

    public MyWikiAdapterForPrimary(int i, Context mContext, List<FoodForWiki.GroupBean> data) {
        this.mContext = mContext;
        this.data = data;
        this.groupIndex = i;
    }

    @Override
    public int getCount() {
        if(data.size()==0){
            return  data.size();
        }else{
            return data.get(groupIndex).getCategories().size();

        }
    }

    @Override
    public Object getItem(int i) {
        return data.get(groupIndex).getCategories().get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(groupIndex).getCategories().get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.wiki_food_item,null);
            holder.ivHolder = (ImageView) convertView.findViewById(R.id.item_iv_id);
            holder.tvHolder = (TextView) convertView.findViewById(R.id.item_tv_id);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(data.get(groupIndex).getCategories().get(i).getImage_url()).into(holder.ivHolder);
        holder.tvHolder.setText(data.get(groupIndex).getCategories().get(i).getName());
        return convertView;
    }

    public void addAll (List<FoodForWiki.GroupBean> dd){
        data.addAll(dd);
        notifyDataSetChanged();
    }

}
class ViewHolder{
    ImageView ivHolder;
    TextView tvHolder;
}
