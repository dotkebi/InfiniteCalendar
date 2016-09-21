package com.github.dotkebi.infinitecalendar;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;

import static com.github.dotkebi.infinitecalendar.R.id.gridView;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class CalendarPageAdapter extends PagerAdapter {

    private Calendar calendar = Calendar.getInstance();
    private GridView gridView;

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.calendar_form, null);

        TextView textView = (TextView) view.findViewById(R.id.status);

        String[] week = container.getResources().getStringArray(R.array.week);
        textView.setText(Arrays.toString(week));

        gridView = (GridView) view.findViewById(R.id.gridView);

        if (position == 0) {
            calendar.add(Calendar.MONTH, -1);
        } else if (position == 2) {
            calendar.add(Calendar.MONTH, 1);
        }
        gridView.setAdapter(new DayAdapter(calendar));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
