package com.d_code.dev_auto.homesecretary.presentation.view.component.adapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.d_code.dev_auto.homesecretary.presentation.view.fragment.CalendarFragment;
import com.d_code.dev_auto.homesecretary.presentation.view.fragment.FixedCheckListFragment;
import com.d_code.dev_auto.homesecretary.presentation.view.fragment.ScheduleFragment;
import com.d_code.dev_auto.homesecretary.presentation.view.fragment.SettingFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS =3;

    public TabPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ScheduleFragment.newInstance();
            case 1:
                return CalendarFragment.newInstance();
            case 2:
                return FixedCheckListFragment.newInstance();
            case 3:
                return SettingFragment.newInstance();
            default:
                return new Fragment();
        }
    }
    public void setCount(int num){
        this.NUM_ITEMS = num;
    }
}