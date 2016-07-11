package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.bai.com.foodpai.MyApp;
import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.FoodDetialForWiki;

/**
 * Created by Administrator on 16-7-11.
 */
public class MoreElementAdapter extends BaseAdapter{
    private Context mContext;

    private String[] elementNames;

    private String[] elements;

    public MoreElementAdapter(Context mContext, FoodDetialForWiki.IngredientBean ingredientBean, String[] elementNames) {
        this.mContext = mContext;
        this.elementNames = elementNames;
        elements= new String[]{
                ingredientBean.getCalory(),
                ingredientBean.getProtein(),
                ingredientBean.getFat(),
                ingredientBean.getCarbohydrate(),
                ingredientBean.getFiber_dietary(),
                ingredientBean.getVitamin_a(),
                ingredientBean.getVitamin_c(),
                ingredientBean.getVitamin_e(),
                ingredientBean.getCarotene(),
                ingredientBean.getThiamine(),
                ingredientBean.getLactoflavin(),
                ingredientBean.getNiacin(),
                ingredientBean.getCholesterol(),
                ingredientBean.getMagnesium(),
                ingredientBean.getCalcium(),
                ingredientBean.getIron(),
                ingredientBean.getZinc(),
                ingredientBean.getCopper(),
                ingredientBean.getManganese(),
                ingredientBean.getKalium(),
                ingredientBean.getPhosphor(),
                ingredientBean.getNatrium(),
                ingredientBean.getSelenium()
        };
    }

    @Override
    public int getCount() {
        return elements.length;
    }

    @Override
    public Object getItem(int i) {
        return elements[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ElementHolder holder = null;
        if(view ==null){
            holder = new ElementHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.total_element_item,null);
            holder.tv1 = (TextView) view.findViewById(R.id.elementName);
            holder.tv2 = (TextView) view.findViewById(R.id.elementValue);
            view.setTag(holder);
        }else{
            holder = (ElementHolder) view.getTag();
        }
        holder.tv1.setText(elementNames[i]);
        holder.tv2.setText(elements[i]);
        return view;
    }
}
class ElementHolder{
    public TextView tv1,tv2;

}
