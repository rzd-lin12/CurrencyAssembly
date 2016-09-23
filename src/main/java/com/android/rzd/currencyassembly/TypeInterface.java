package com.android.rzd.currencyassembly;

/**
 * Created by Administrator on 2016/8/30.
 * <p/>
 * 该接口用于选择哪个类型的Item去处理数据
 */
public interface TypeInterface<T> {
    ItemViewEntrance<T> getItemViewType(int position, ItemViewEntranceGroup<T> typeGroup);
}
