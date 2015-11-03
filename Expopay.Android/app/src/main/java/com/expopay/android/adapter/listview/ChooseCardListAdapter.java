package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextClock;
import android.widget.TextView;

import com.expopay.android.R;
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
        return data.size();
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
        CardEntity e = data.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.view_choosecard_item, null);
        TextView cardNumberText = (TextView) view.findViewById(R.id.choosecard_item_cardnumber);
        RadioButton radio = (RadioButton) view.findViewById(R.id.choosecard_item_radio);
        cardNumberText.setText(e.getCardNumber());
        return view;
    }

    public List<CardEntity> getData() {
        return data;
    }

    public void setData(List<CardEntity> data) {
        this.data = data;
    }
}
