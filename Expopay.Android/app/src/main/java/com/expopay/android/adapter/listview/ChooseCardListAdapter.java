package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.expopay.android.model.CardEntity;

import java.util.List;

/**
 * Created by misxu012 on 2015/11/3.
 */
public class ChooseCardListAdapter extends BaseAdapter {
    Context context;
    List<CardEntity> data;

    public ChooseCardListAdapter(Context context, List<CardEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public List<CardEntity> getData() {
        return data;
    }
}
