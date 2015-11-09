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
import com.expopay.android.model.PasswordQuestionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by misxu012 on 2015/11/3.
 */
public class ChooseQuestionListAdapter extends BaseAdapter {
    Context context;
    List<PasswordQuestionEntity> data;
    private Map<Integer, Boolean> status;

    public ChooseQuestionListAdapter(Context context, List<PasswordQuestionEntity> data) {
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
        final PasswordQuestionEntity e = data.get(index);
        final View view = LayoutInflater.from(context).inflate(R.layout.view_choosecard_item, null);
        final RadioButton radio = (RadioButton) view.findViewById(R.id.choosecard_item_radio);
        final TextView cardNumberText = (TextView) view.findViewById(R.id.choosecard_item_cardnumber);
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
        cardNumberText.setText(e.getSecuQuestion());
        return view;
    }

    public List<PasswordQuestionEntity> getData() {
        return data;
    }

    public void setData(List<PasswordQuestionEntity> data) {
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
