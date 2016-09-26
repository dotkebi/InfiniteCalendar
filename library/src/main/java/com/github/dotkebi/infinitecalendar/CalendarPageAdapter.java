package com.github.dotkebi.infinitecalendar;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class CalendarPageAdapter extends PagerAdapter {

    private static final int MONTH_OF_YEAR = 12;

    //private CalendarHelper calendar = new CalendarHelper(1, null);
    private Calendar calendar = Calendar.getInstance();
    private GridView gridView;
    //private List<DayAdapter.ViewHolder> viewHolderList = new ArrayList<>();
    private SparseArray<ViewHolder> items = new SparseArray<>();

    public CalendarPageAdapter() {
    }

    @Override
    public int getCount() {
        return MONTH_OF_YEAR;
    }

   /* @Override
    public int getItemPosition(Object object) {
        return MONTH_OF_YEAR - ((ViewHolder) object).position;
    }*/

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.calendar_form, null);

        //int diff = calendar.get(Calendar.MONTH) - position;
        int diff = (MONTH_OF_YEAR - 1 - position) * -1;
        Calendar thisMonth = Calendar.getInstance();
        //thisMonth.set(Calendar.MONTH, diff);
        thisMonth.add(Calendar.MONTH, diff);

        TextView month = (TextView) view.findViewById(R.id.calendar_status);
        //month.setText(String.format(Locale.KOREA, "%04d-%02d", thisMonth.getStartYear(), thisMonth.getStartMonth()));
        month.setText(String.format(Locale.KOREA, "%04d-%02d", thisMonth.get(Calendar.YEAR), thisMonth.get(Calendar.MONTH) + 1));

        String[] week = container.getResources().getStringArray(R.array.week);

        TextView textView = (TextView) view.findViewById(R.id.status);
        textView.setText(Arrays.toString(week));

        gridView = (GridView) view.findViewById(R.id.gridView);
        //gridView.setAdapter(new DayAdapter(thisMonth.getStartCalendar()));
        gridView.setAdapter(new DayAdapter(thisMonth));

        container.addView(view);

        ViewHolder viewHolder = new ViewHolder(position, view);
        items.append(position, viewHolder);

        return viewHolder;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        final ViewHolder holder = (ViewHolder) view;
        container.removeView(holder.calendarView);

        items.remove(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        final ViewHolder holder = (ViewHolder) object;
        return view == holder.calendarView;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    /*public void nextMonth() {
        calendar.nextMonth();
        gridView.setAdapter(new DayAdapter(calendar.getStartCalendar()));
    }

    public void previousMonth() {
        calendar.previousMonth();
        gridView.setAdapter(new DayAdapter(calendar.getStartCalendar()));
    }*/

    private static class ViewHolder {
        public int position;
        public View calendarView;

        public ViewHolder(int position, View calendarView) {
            this.position = position;
            this.calendarView = calendarView;
        }
    }

}
