package com.github.dotkebi.infinitecalendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.antonyt.infiniteviewpager.InfinitePagerAdapter;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class InfiniteCalendar extends LinearLayout {

    private InfiniteVerticalViewPager viewPager;
    private int page;

    public InfiniteCalendar(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public InfiniteCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(context, attrs, 0, 0);
        }
    }

    public InfiniteCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(context, attrs, defStyleAttr, 0);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfiniteCalendar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            init(context, attrs, defStyleAttr, defStyleRes);
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.TagInputer, defStyleAttr, defStyleRes
        );

        try {
            maxCountOfTags = a.getInteger(R.styleable.TagInputer_maxCountOfTags, 0);
            maxLengthOfEachTags = a.getInteger(R.styleable.TagInputer_maxLengthOfEachTags, 0);
        } finally {
            a.recycle();
        }*/
        init(context);
    }

    private void init(Context context) {
        //PagerAdapter adapter = new InfinitePagerAdapter(new CalendarPageAdapter());
        final CalendarPageAdapter adapter = new CalendarPageAdapter();

        final InfiniteCalendarPageAdapter infiniteCalendarPageAdapter = new InfiniteCalendarPageAdapter(adapter, new OnCalendarPageChangeListener() {
            @Override
            public void onCalendarPageChangeListener(int page) {
                InfiniteCalendar.this.page = page;
                Log.i("page", "" + page);
            }
        });

        viewPager = new InfiniteVerticalViewPager(context);
        viewPager.setAdapter(infiniteCalendarPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position %= 2;
                Log.i("position", "" + position);
                if (page > 0) {
                    if (position == 1) {
                        adapter.nextMonth();
                    } else if (position == 0) {
                        adapter.previousMonth();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        addView(viewPager);
    }

}
