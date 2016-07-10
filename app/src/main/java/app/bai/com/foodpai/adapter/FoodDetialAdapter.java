package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.FoodDetialForWiki;

/**
 * Created by Administrator on 16-7-10.
 */
public class FoodDetialAdapter extends BaseAdapter{
    private List<FoodDetialForWiki.UnitsBean> units;
    private Context dContext;
    public FoodDetialAdapter() {
    }

    public FoodDetialAdapter(List<FoodDetialForWiki.UnitsBean> units, Context dContext) {
        this.units = units;
        this.dContext = dContext;
    }

    @Override
    public int getCount() {
        return units.size();
    }

    @Override
    public Object getItem(int i) {
        return units.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DetialHolder detialHolder;
        if(view == null){
            detialHolder = new DetialHolder();
            view = LayoutInflater.from(dContext).inflate(R.layout.wiki_food_detial_units_item,null);
            detialHolder.first = (TextView) view.findViewById(R.id.wiki_food_detial_units_item_fir_id);
            detialHolder.second = (TextView) view.findViewById(R.id.wiki_food_detial_units_item_sc_id);
            detialHolder.third = (TextView) view.findViewById(R.id.wiki_food_detial_units_item_th_id);
            view.setTag(detialHolder);
        }else{
            detialHolder = (DetialHolder) view.getTag();
        }
            detialHolder.first.setText(units.get(i).getAmount()+units.get(i).getUnit());
            detialHolder.second.setText(units.get(i).getWeight()+"克");
            detialHolder.third.setText(units.get(i).getCalory()+"克");
        return view;
    }

}
class DetialHolder{
    TextView first;
    TextView second;
    TextView third;
}
