package app.bai.com.foodpai.MyAdapter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import app.bai.com.foodpai.fragment.Collec_Fragment;

/**
 * Created by Administrator on 16-7-8.
 */
public class ViewPageAdapter extends FragmentPagerAdapter {
    private String[] tabNames ;
    private List<Collec_Fragment> fragments;
    public ViewPageAdapter(FragmentManager fm,String[] tabNames , List<Collec_Fragment> fragments) {
        super(fm);
        this.tabNames = tabNames;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
