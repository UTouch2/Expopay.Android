package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.CardEntity;
import com.expopay.android.model.WegTransactionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by misxu012 on 2015/11/3.
 */
public class ChooseWegTransAdapter extends BaseAdapter {
    Context context;
    List<WegTransactionEntity> data;
    private Map<Integer, Boolean> status;

    public ChooseWegTransAdapter(Context context, List<WegTransactionEntity> data) {
        this.context = context;
        this.data = data;
        this.status = new HashMap<>();
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
        final int index = position;
        final ViewGroup vg = parent;
        final WegTransactionEntity e = data.get(index);
        final View view = LayoutInflater.from(context).inflate(R.layout.view_choosewegtrans_item, null);
        final RadioButton radio = (RadioButton) view.findViewById(R.id.chooseweg_item_radio);
        final TextView paramText = (TextView) view.findViewById(R.id.chooseweg_item_paramtext);
        final TextView paramValue = (TextView) view.findViewById(R.id.chooseweg_item_paramvalue);
        final TextView amount = (TextView) view.findViewById(R.id.chooseweg_item_amount);

        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioCheckd(index);
                vg.setTag(e);
            }
        });
        if (status.get(index) == null) {
            status.put(index, radio.isChecked());
        }
        radio.setChecked(status.get(index));
        amount.setText(e.getAmount());
        paramText.setText(e.getPublicParamText());
        paramValue.setText(e.getPublicParamValue());
        return view;
    }

    public List<WegTransactionEntity> getData() {
        return data;
    }

    public void setData(List<WegTransactionEntity> data) {
        this.data = data;
    }

    private void setRadioCheckd(int index) {
        for (Integer key : status.keySet()) {
            status.put(key, false);
        }
        status.put(index, true);
        notifyDataSetChanged();
    }
}
