package com.github.dotkebi.infinitecalendar;

import android.support.v4.view.PagerAdapter;
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

    private Calendar calendar = Calendar.getInstance();
    private int previous;

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.calendar_form, null);

        TextView month = (TextView) view.findViewById(R.id.month);
        month.setText(String.format(Locale.KOREA, "%04d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));

        String[] week = container.getResources().getStringArray(R.array.week);

        TextView textView = (TextView) view.findViewById(R.id.status);
        textView.setText(Arrays.toString(week));

        GridView gridView = (GridView) view.findViewById(R.id.gridView);

        if (previous > position) {
            calendar.add(Calendar.MONTH, -1);
        } else if (previous < position) {
            calendar.add(Calendar.MONTH, 1);
        }
        previous = position;
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
