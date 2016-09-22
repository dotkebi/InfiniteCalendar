package com.github.dotkebi.infinitecalendar;

import java.util.Calendar;

/**
 * @author by dotkebi@gmail.com on 2016-02-28.
 */
public class CalendarHelper {
    /**
     * 조회기간
     */
    private Calendar startCalendar;
    private Calendar previousStartCalendar;
    private Calendar endCalendar;

    /**
     * 조회기간 조정 카운터
     */
    private int steps;

    private int startDay;

    private OnControllerDisableListner onControllerDisableListner;
    public interface OnControllerDisableListner {
        void onControllerDisableListner(boolean flag);
    }

    /**
     * constructor
     */
    public CalendarHelper(int startDay, OnControllerDisableListner onControllerDisableListner) {
        System.out.println(startDay);
        this.startDay = startDay;
        this.onControllerDisableListner = onControllerDisableListner;
        initCalendar();
    }

    public void initCalendar() {
        steps = 0;
        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        previousStartCalendar = Calendar.getInstance();

        endCalendar.setTime(startCalendar.getTime());
        endCalendar.add(Calendar.MONTH, 1);

        endCalendar.set(
                Calendar.DAY_OF_MONTH
                , (startDay > 29)
                        ? endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                        : Math.min(startDay, endCalendar.get(Calendar.DAY_OF_MONTH))
        );
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);

        startDayCorrector();
        if (startCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() > 0) {
            previousMonth();
            startDayCorrector();
        }

        if (onControllerDisableListner != null) {
            onControllerDisableListner.onControllerDisableListner(
                    !((endCalendar.get(Calendar.MONTH)) == Calendar.getInstance().get(Calendar.MONTH))
            );
        }
    }

    private void calendarCorrertor() {
        startDayCorrector();
        if (startCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() > 0) {
            previousMonth();
            startDayCorrector();
        }

        endCalendar.set(
                previousStartCalendar.get(Calendar.YEAR)
                , previousStartCalendar.get(Calendar.MONTH)
                , previousStartCalendar.get(Calendar.DAY_OF_MONTH)
        );
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);

        if (endCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() > 0) {
            endCalendar = Calendar.getInstance();
        }
    }

    private void startDayCorrector() {
        startCalendar.set(Calendar.DAY_OF_MONTH
                , Math.min(startDay, startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        );
    }

    public void previousMonth() {
        --steps;

        previousStartCalendar.set(
                startCalendar.get(Calendar.YEAR)
                , startCalendar.get(Calendar.MONTH)
                , startCalendar.get(Calendar.DAY_OF_MONTH)
        );
        startCalendar.add(Calendar.MONTH, -1);
        calendarCorrertor();

        if (onControllerDisableListner != null) {
            onControllerDisableListner.onControllerDisableListner(true);
        }
    }

    public void nextMonth() {
        if (steps == -1) {
            if (onControllerDisableListner != null) {
                onControllerDisableListner.onControllerDisableListner(false);
            }
            ++steps;
            initCalendar();
        } else {
            ++steps;
            startCalendar.add(Calendar.MONTH, 1);
            previousStartCalendar.set(
                    startCalendar.get(Calendar.YEAR)
                    , startCalendar.get(Calendar.MONTH) + 1
                    , startCalendar.get(Calendar.DAY_OF_MONTH)
            );

            previousStartCalendar.set(Calendar.DAY_OF_MONTH
                    , Math.min(startDay, previousStartCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            );
            calendarCorrertor();
        }
    }

    public void setStartCalendar(int year, int monthOfYear, int dayOfMonth) {
        startCalendar.set(year, monthOfYear, dayOfMonth);
    }

    public void setEndCalendar(int year, int monthOfYear, int dayOfMonth) {
        endCalendar.set(year, monthOfYear, dayOfMonth);
    }

    public long getMinimimdateMills() {
        return startCalendar.getTimeInMillis();
    }

    public int getStartYear() {
        return startCalendar.get(Calendar.YEAR);
    }

    public int getStartMonth() {
        return startCalendar.get(Calendar.MONTH) + 1;
    }

    public int getStartDayOfMonth() {
        return startCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getEndYear() {
        return endCalendar.get(Calendar.YEAR);
    }

    public int getEndMonth() {
        return endCalendar.get(Calendar.MONTH) + 1;
    }

    public int getEndDayOfMonth() {
        return endCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public Calendar getStartCalendar() {
        return startCalendar;
    }

    /**
     * 윤년 체크
     */
    private boolean isLeapYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        return (year % 4 == 0 && year % 100 != 0)
                || year % 400 == 0
                ;
    }
}