package com.github.dotkebi.infinitecalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class CalendarFragment extends Fragment {

    private static final String KEY_YEAR = "year";
    private static final String KEY_MONTH = "month";

    private Calendar calendar = Calendar.getInstance();
    private GridView gridView;

    public CalendarFragment() {
    }

    public static CalendarFragment newInstance(int year, int month) {
        CalendarFragment fragment = new CalendarFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_YEAR, year);
        bundle.putInt(KEY_MONTH, month);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            int year = getArguments().getInt(KEY_YEAR);
            int month = getArguments().getInt(KEY_MONTH);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
        }
        View view = inflater.inflate(R.layout.calendar_form, container, false);

        TextView textView = (TextView) view.findViewById(R.id.status);

        String[] week = getResources().getStringArray(R.array.week);
        textView.setText(Arrays.toString(week));

        gridView = (GridView) view.findViewById(R.id.gridView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView.setAdapter(new DayAdapter(calendar));
    }

}
