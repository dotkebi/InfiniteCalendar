package com.github.dotkebi.infinitecalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class CalendarPageAdapter extends FragmentPagerAdapter {

    public CalendarPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

}
