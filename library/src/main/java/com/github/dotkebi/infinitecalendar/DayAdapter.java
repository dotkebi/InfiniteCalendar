package com.github.dotkebi.infinitecalendar;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author by dotkebi@gmail.com on 2016-09-21.
 */
public class DayAdapter extends BaseAdapter {

    private Calendar calendar = Calendar.getInstance();

    public DayAdapter(Calendar calendar) {
        this.calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        //this.calendar = calendar;
        Log.i("refresh", calendar.getTime().toString());
    }

    @Override
    public int getCount() {
//        Calendar thisMonth = Calendar.getInstance();
//        thisMonth.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        //return thisMonth.get(Calendar.DAY_OF_WEEK) - 1 + thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1 + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Integer getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = View.inflate(parent.getContext(), R.layout.calendar_day, null);
            viewHolder.day = (TextView) convertView.findViewById(R.id.day);
            viewHolder.contents = (TextView) convertView.findViewById(R.id.contents);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int previous = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (position > previous) {
            viewHolder.day.setText(String.format(Locale.KOREA, "%d", position - previous));
        }
        viewHolder.contents.setText("");
        return convertView;
    }

    class ViewHolder {
        TextView day;
        TextView contents;
    }

}
