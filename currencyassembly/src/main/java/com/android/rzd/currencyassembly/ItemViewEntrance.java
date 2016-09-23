package com.android.rzd.currencyassembly;

/**
 *  Item每个类型的实现接口
 * @param <T>
 */
public interface ItemViewEntrance<T> {
    int getLayoutId();

    void convert(CurrencyViewHolder viewHolder, T t);
}