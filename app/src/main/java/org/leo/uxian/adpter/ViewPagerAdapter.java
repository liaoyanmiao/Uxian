package org.leo.uxian.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragmentList(List<Fragment> fragments){
        if (!fragmentList.isEmpty()){
            fragmentList.clear();
        }
        fragmentList.addAll(fragments);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
