package com.android.rzd.currencyassembly;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 * 该adapter封装参考自鸿洋大神的万用adapter：
 *      https://github.com/hongyangAndroid/baseAdapter
 *
 * （本人菜鸟一枚，只是在基础上稍微改动了一下，不足之处多多指教）
 */
public class CurrencyAdAdapter<T> extends RecyclerView.Adapter<CurrencyViewHolder> {
    private List<T> mList;
    private ItemViewEntranceGroup entranceGroup;
    private ItemViewEntrance<T> entrance;
    private TypeInterface typeInterface;

    public CurrencyAdAdapter(List<T> mList, ItemViewEntranceGroup entranceGroup, TypeInterface typeInterface) {
        this.mList = mList;
        this.entranceGroup = entranceGroup;
        this.typeInterface = typeInterface;
    }

    public CurrencyAdAdapter(List<T> mList, SparseArrayCompat arrayCompat, TypeInterface typeInterface) {
        this.mList = mList;
        this.entranceGroup = new ItemViewEntranceGroup(arrayCompat);
        this.typeInterface = typeInterface;
    }

    @Override
    public int getItemViewType(int position) {
        entrance = typeInterface.getItemViewType(position, entranceGroup);
        return super.getItemViewType(position);
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CurrencyViewHolder.getInstance(parent, entrance.getLayoutId());
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
            entrance.convert(holder,mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmList(List<T> mList) {
        this.mList = mList;
    }

    public void addList(List<T> list) {
        this.mList.addAll(list);
    }

    public List<T> getmList() {
        return mList;
    }
}
