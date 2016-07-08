package app.bai.com.foodpai.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by JasonLi on 2016/6/8.
 */
public class ShowViewPagerAdapter extends PagerAdapter
{
    List<ImageView> imageViews;
    public ShowViewPagerAdapter(List<ImageView> imageViews){
        this.imageViews = imageViews;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(imageViews.get(position));
    }
    public void addAll(List<ImageView> dd){
        imageViews.addAll(dd);
        notifyDataSetChanged();
    }


}


