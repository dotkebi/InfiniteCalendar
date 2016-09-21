package com.github.dotkebi.infinitecalendar;

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

    private Calendar calendar;

    public DayAdapter(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public int getCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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

        viewHolder.day.setText(String.format(Locale.KOREA, "%d", position + 1));
        viewHolder.contents.setText("");
        return convertView;
    }

    class ViewHolder {
        TextView day;
        TextView contents;
    }

}
