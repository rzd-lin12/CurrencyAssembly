package com.android.rzd.currencyassembly;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/13.
 */

public abstract class CurrencyCalendarAdapter {
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    abstract View onCreatItem(ViewGroup parent);

    abstract View onCreatTitle(ViewGroup parent, String weekName);

    abstract void disposeItem(ItemCalendarHolder itemCalendar);

}
