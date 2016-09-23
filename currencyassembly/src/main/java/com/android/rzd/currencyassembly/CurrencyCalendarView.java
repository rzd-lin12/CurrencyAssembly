package com.android.rzd.currencyassembly;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/13.
 */

public class CurrencyCalendarView extends ViewGroup {
    private int thisDay;
    private int thisMonth;
    private int thisYear;
    private int firstDayWeek;
    private int monthWeeks;
    private int monthDayNum;
    private ItemCalendarHolder[] dateViews;
    private String[] weekNames = {"日", "一", "二", "三", "四", "五", "六"};
    private int previousDaynum;
    private int previousMonth;
    private int nextMonth;
    private Calendar calendar;
    private String TAG = "calendar";

    public void setCurrencyCalendarAdapter(CurrencyCalendarAdapter currencyCalendarAdapter) {
        this.currencyCalendarAdapter = currencyCalendarAdapter;
        setDate(new Date());
    }

    private CurrencyCalendarAdapter currencyCalendarAdapter;

    public CurrencyCalendarView(Context context) {
        this(context, null);
    }

    public CurrencyCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurrencyCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "CurrencyCalendarView: ");
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        init();
    }

    private void init() {
        removeAllViews();
        initCalendar();
        initItemView();
    }

    public void next() {
        calendar.set(Calendar.MONTH, thisMonth);
        init();
    }

    public void previous() {
        calendar.set(Calendar.MONTH, thisMonth-2);
        init();
    }

    public void setDate(int year, int month, int day) {
        calendar.set(year, month, day);
        init();
    }

    public void setDate(Date date) {
        calendar.setTime(date);
        init();
    }

    private void initCalendar() {
        thisYear = calendar.get(Calendar.YEAR);
        thisMonth = calendar.get(Calendar.MONTH) + 1;
        thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        星期区间为(0-6)，为了方便后面使用故意的；
        firstDayWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        Log.i(TAG, "initCalendar: " + firstDayWeek + "   " + calendar.getTime() + "   " + thisMonth);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
//        本月有几个星期
        monthWeeks = calendar.get(Calendar.WEEK_OF_MONTH);
//        本月有多少天
        monthDayNum = calendar.get(Calendar.DAY_OF_MONTH);
//============================上一个月=========================================
        calendar.add(Calendar.MONTH, -1);
        previousMonth = calendar.get(Calendar.MONTH) + 1;
//        上个月有多少天
        previousDaynum = calendar.get(Calendar.DAY_OF_MONTH);
//============================下一个月=========================================
        calendar.add(Calendar.MONTH, 2);
        nextMonth = calendar.get(Calendar.MONTH) + 1;
    }

    private void initItemView() {
        dateViews = new ItemCalendarHolder[monthWeeks * 7];
        for (int i = 0; i < 7; i++) {
            if (currencyCalendarAdapter == null)
                break;
            addView(currencyCalendarAdapter.onCreatTitle(this, weekNames[i]));
        }
        for (int i = 0; i < dateViews.length; i++) {
//            计算星期
            int week = i % 7;
//            计算出现在到了几号，超过本月最多天数，就进入下一个月；
            int date = i - firstDayWeek + 1;
            if (currencyCalendarAdapter == null)
                break;
            View view = currencyCalendarAdapter.onCreatItem(this);
            if (view == null)
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_date, this, false);
            dateViews[i] = new ItemCalendarHolder(view);
            if (i < firstDayWeek) {
                if (thisMonth == 1)
                    dateViews[i].setYear(thisYear - 1);
                else
                    dateViews[i].setYear(thisYear);
                int day = previousDaynum - firstDayWeek + 1;
                dateViews[i].setMonth(previousMonth);
                dateViews[i].setDayth(day);
                dateViews[i].setWeek(week);
                dateViews[i].setEnabled(false);
                dateViews[i].setText(day + "");

            } else if (date > monthDayNum) {
                if (thisMonth == 12)
                    dateViews[i].setYear(thisYear + 1);
                else
                    dateViews[i].setYear(thisYear);
                int day = date - monthDayNum;
                dateViews[i].setMonth(nextMonth);
                dateViews[i].setDayth(day);
                dateViews[i].setWeek(week);
                dateViews[i].setEnabled(false);
                dateViews[i].setText(day + "");
            } else {
                dateViews[i].setYear(thisYear);
                dateViews[i].setMonth(thisMonth);
                dateViews[i].setDayth(date);
                dateViews[i].setWeek(week);
                dateViews[i].setText(date + "");
            }
            currencyCalendarAdapter.disposeItem(dateViews[i]);

            addView(dateViews[i].getItemView());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int maxWidth = 0, maxHeight = 0, widthTitle = 0, widthContent = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                View child;
                for (int i = 0; i < 7; i++) {
                    child = getChildAt(i);
                    widthTitle += child.getMeasuredWidth();
                }
                for (int i = 7; i < 14; i++) {
                    child = getChildAt(i);
                    widthContent += child.getMeasuredWidth();
                }
                maxWidth = Math.max(widthTitle, widthContent);
                maxWidth = maxWidth < widthSize ? maxWidth : widthSize;
                maxHeight = getChildAt(0).getMeasuredHeight();
                maxHeight += getChildAt(7).getMeasuredHeight();
                break;
            case MeasureSpec.EXACTLY:
                maxWidth = widthSize;
                maxHeight = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                maxWidth = widthSize;
                maxHeight = heightSize;
                break;
        }
        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec),
                resolveSize(maxHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        int childWidth = (r - l - getPaddingLeft() - getPaddingRight()) / 7;
        int paddingLeft = getPaddingLeft();
        int height = getPaddingTop();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            if (i % 7 == 0 && i >= 7) {
                height += childHeight;
            }
            int childLeft = paddingLeft + childWidth * (i % 7);
            child.getLayoutParams().width = childWidth;
            child.layout(childLeft, height, childLeft + childWidth, height + childHeight);
        }
    }

}
