package com.github.dotkebi.infinitecalendar;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class CalendarPageAdapter extends PagerAdapter {

    private CalendarHelper calendar = new CalendarHelper(1, null);
    private GridView gridView;

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.calendar_form, null);

        TextView month = (TextView) view.findViewById(R.id.calendar_status);
        month.setText(String.format(Locale.KOREA, "%04d-%02d", calendar.getStartYear(), calendar.getStartMonth()));

        String[] week = container.getResources().getStringArray(R.array.week);

        TextView textView = (TextView) view.findViewById(R.id.status);
        textView.setText(Arrays.toString(week));

        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new DayAdapter(calendar.getStartCalendar()));

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

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    public void nextMonth() {
        calendar.nextMonth();
        gridView.setAdapter(new DayAdapter(calendar.getStartCalendar()));
    }

    public void previousMonth() {
        calendar.previousMonth();
        gridView.setAdapter(new DayAdapter(calendar.getStartCalendar()));
    }

}
