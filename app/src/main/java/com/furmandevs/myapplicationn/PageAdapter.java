package com.furmandevs.myapplicationn;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabItem;

public class PageAdapter extends FragmentPagerAdapter {

    private int tabsNum = 2;
    private TabItem menuItem, premiumItem;

    public PageAdapter(@NonNull FragmentManager fm, int tabsNum) {
        super(fm);
        this.tabsNum = tabsNum;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0 :
                return new MenuFragment1();
            case 1 :
                return  new PremiumFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabsNum;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
