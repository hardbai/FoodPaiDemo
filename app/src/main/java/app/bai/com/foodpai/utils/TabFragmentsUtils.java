package app.bai.com.foodpai.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import app.bai.com.foodpai.fragment.BaseFragment;

/**
 * 切换Fragment的工具类
 * 满足条件：radioButton 切换 fragment
 */
public class TabFragmentsUtils implements RadioGroup.OnCheckedChangeListener {

    //当前的fragment
    private int currentFragment;
    //RadioGroup
    private RadioGroup radioGroup;
    //所有切换的所有的fragments集合
    private List<BaseFragment> fragments;
    //Fragment的管理器
    private FragmentManager fragmentManager;
    //Fragment的占位
    private int containerId;
    public TabFragmentsUtils(RadioGroup radioGroup, List<BaseFragment> fragments, FragmentManager fragmentManager, int containerId)
    {
        this.radioGroup = radioGroup ;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        //让当前类实现这个切换监听接口
        radioGroup.setOnCheckedChangeListener(this);
        //默认选中0个
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        currentFragment=0;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton child = (RadioButton) radioGroup.getChildAt(i);
            if(checkedId == child.getId())
            {
                //让当前的控件不可见
                fragments.get(currentFragment).onStop();
                if(fragments.get(i).isAdded())
                {
                    fragments.get(i).onStart();
                }else
                {
                    fragmentManager.beginTransaction().add(containerId,fragments.get(i)).commit();
                }
                showTab(i);
            }

        }
    }
    //显示当前的fragment
    private void showTab(int index) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(i==index)
            {
                fragmentTransaction.show(fragment);
            }else
            {
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commit();
        }
        currentFragment = index;
    }
}
