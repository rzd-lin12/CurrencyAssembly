package com.android.rzd.currencyassembly;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;


/**
 * Created by Administrator on 2016/8/26.
 * 本ViewHolder参考自：
 * RecyclerView中Adapter和ViewHolder的封装 - DevWiki
 * http://blog.devwiki.net/index.php/2016/05/22/Recycler-View-Adapter-ViewHolder.html
 * 图片加载用的是谷歌的 Glide；
 */
public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewArray;
    private Context context;
    private View currentView;

    protected CurrencyViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        viewArray = new SparseArray<>();
    }


    public static CurrencyViewHolder getInstance(View view) {
        return new CurrencyViewHolder(view);
    }

    public static CurrencyViewHolder getInstance(ViewGroup parent, @LayoutRes int resource) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return getInstance(view);
    }

    /**
     * 根据id找控件
     * 并把控件设为全局，方便设置控件的属性
     *
     * @param viewId
     * @return
     */
    public CurrencyViewHolder findView(@IdRes int viewId) {
        currentView = viewArray.get(viewId);
        if (currentView == null) {
            currentView = itemView.findViewById(viewId);
            viewArray.put(viewId, currentView);
        }
        return this;
    }

    /**
     * 根据id获取控件；
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        findView(viewId);
        return (T) currentView;
    }

    /**
     * 获取当前控件；
     *
     * @param <T>
     * @return
     */
    public <T extends View> T getCurrentView() {
        isViewEmty();
        return (T) currentView;
    }

    /**
     * 设置当前控件的单击事件
     *
     * @param onClickListener
     * @return
     */
    public CurrencyViewHolder setOnClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(null, onClickListener);
        return this;
    }

    public CurrencyViewHolder setOnClickListener(Object tag, View.OnClickListener onClickListener) {
        currentView.setTag(tag);
        currentView.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置当前控件的长按事件
     *
     * @param onLongClickListener
     * @return
     */
    public CurrencyViewHolder setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        setOnLongClickListener(null, onLongClickListener);
        return this;
    }

    public CurrencyViewHolder setOnLongClickListener(@Nullable Object tag, View.OnLongClickListener onLongClickListener) {
        currentView.setTag(tag);
        currentView.setOnLongClickListener(onLongClickListener);
        return this;
    }

    /**
     * 设置当前控件的Text；
     *
     * @param charSequence
     * @return
     * @throws Exception
     */
    public CurrencyViewHolder setText(CharSequence charSequence) {
        isViewEmty();
        transformationText().setText(charSequence);
        return this;
    }

    public CurrencyViewHolder setText(@StringRes int resource) {
        isViewEmty();
        transformationText().setText(resource);
        return this;
    }

    public CurrencyViewHolder setText(@IdRes int viewId, CharSequence charSequence) {
        findView(viewId);
        transformationText().setText(charSequence);
        return this;
    }

    public CurrencyViewHolder setText(@IdRes int viewId, @StringRes int resource) {
        findView(viewId);
        transformationText().setText(resource);
        return this;
    }

    /**
     * 设置当前控件的TextSize;
     *
     * @param size
     * @return
     * @throws Exception
     */
    public CurrencyViewHolder setTextSize(float size) {
        isViewEmty();
        transformationText().setTextSize(size);
        return this;
    }

    /**
     * 设置当前控件的TextColor;
     *
     * @param color
     * @return
     * @throws Exception
     */
    public CurrencyViewHolder setTextColor(@ColorInt int color) {
        isViewEmty();
        transformationText().setTextColor(color);
        return this;
    }

    /**
     * 设置当前控件的图片
     *
     * @param url
     * @return
     */
    public CurrencyViewHolder setImage(String url) {
        isViewEmty();
        Glide.with(context).load(url).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(Uri uri) {
        isViewEmty();
        Glide.with(context).load(uri).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(@AnyRes int resource) {
        isViewEmty();
        Glide.with(context).load(resource).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(File file) {
        isViewEmty();
        Glide.with(context).load(file).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(@IdRes int viewId, String url) {
        findView(viewId);
        Glide.with(context).load(url).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(@IdRes int viewId, Uri uri) {
        findView(viewId);
        Glide.with(context).load(uri).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(@IdRes int viewId, @AnyRes int resource) {
        findView(viewId);
        Glide.with(context).load(resource).into(transformationImage());
        return this;
    }

    public CurrencyViewHolder setImage(@IdRes int viewId, File file) {
        findView(viewId);
        Glide.with(context).load(file).into(transformationImage());
        return this;
    }

    //    =======================我=====是=====分=====隔======线=====哈=====哈====哈====================
    private void isViewEmty() {
        if (currentView == null) {
            Log.e("Exception", "NullPointerException: ", new Throwable("currentView为NULL,请调用findView()方法设置你需要的View"));
        }

    }

    private TextView transformationText() {
        TextView textView = null;
        try {
            textView = (TextView) currentView;
        } catch (ClassCastException e) {
            Log.e("Exception", "ClassCastException: ", new Throwable("控件不是TextView或TextView的子类"));
        }

        return textView;
    }

    private ImageView transformationImage() {
        ImageView imageView = null;
        try {
            imageView = (ImageView) currentView;
        } catch (ClassCastException e) {
            Log.e("Exception", "ClassCastException: ", new Throwable("控件不是ImageView或ImageView的子类"));
        }
        return imageView;
    }
}
