package com.android.rzd.currencyassembly;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/13.
 */

public class ItemCalendarHolder {
    private View itemView;
    private int year;
    private int month;
    private int dayth;
    private int week;

    public ItemCalendarHolder(View itemView) {
        this.itemView = itemView;
    }

    public void setText(CharSequence charSequence) {
        ((TextView) itemView).setText(charSequence);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDayth(int dayth) {
        this.dayth = dayth;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public View getItemView() {
        return itemView;
    }

    public void setEnabled(boolean enabled) {
        if (enabled)
            itemView.setVisibility(View.VISIBLE);
        else
            itemView.setVisibility(View.INVISIBLE);
        itemView.setEnabled(enabled);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayth() {
        return dayth;
    }

    public int getWeek() {
        return week;
    }
}
