package com.android.rzd.currencyassembly;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by Administrator on 2016/8/30.
 * Item类型的管理器
 */

public class ItemViewEntranceGroup<T> {
    SparseArrayCompat<ItemViewEntrance<T>> typeArrayCompat = new SparseArrayCompat<>();

    public ItemViewEntranceGroup(int viewType, ItemViewEntrance<T> entrance) {
        addItemType(viewType, entrance);
    }

    public ItemViewEntranceGroup(SparseArrayCompat<ItemViewEntrance<T>> typeArrayCompat) {
        addItemType(typeArrayCompat);
    }

    public ItemViewEntranceGroup<T> addItemType(int viewType, ItemViewEntrance<T> entrance) {
        typeArrayCompat.put(viewType, entrance);
        return this;
    }

    public ItemViewEntranceGroup<T> addItemType(SparseArrayCompat<ItemViewEntrance<T>> entrances) {
        for (int i = 0; i < entrances.size(); i++)
            addItemType(entrances.keyAt(i), entrances.valueAt(i));
        return this;
    }

    public ItemViewEntrance<T> itemEntranceAt(int index) {
        return typeArrayCompat.valueAt(index);
    }

    public ItemViewEntrance<T> itemEntranceOfType(int type) {
        return typeArrayCompat.get(type);
    }
}
