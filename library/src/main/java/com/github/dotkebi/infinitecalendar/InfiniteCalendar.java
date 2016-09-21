package com.github.dotkebi.infinitecalendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.antonyt.infiniteviewpager.InfinitePagerAdapter;

/**
 * @author by dotkebi@gmail.com on 2016-09-20.
 */
public class InfiniteCalendar extends LinearLayout {

    private InfiniteVerticalViewPager viewPager;

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
        PagerAdapter adapter = new InfinitePagerAdapter(new CalendarPageAdapter());

        viewPager = new InfiniteVerticalViewPager(context);
        viewPager.setAdapter(adapter);

        addView(viewPager);
    }

}
