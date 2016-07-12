package app.bai.com.foodpai.MyAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 16-7-12.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter{

    private String[] searchNames;
    private List<Fragment> fragments;
    public MyViewPagerAdapter(FragmentManager fm,String[] searchNames,List<Fragment> fragments) {
        super(fm);
        this.searchNames = searchNames;
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

    public CharSequence getPageTitle(int position) {
        return searchNames[position];
    }
}
