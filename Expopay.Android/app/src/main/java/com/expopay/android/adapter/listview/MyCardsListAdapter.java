package com.expopay.android.adapter.listview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.activity.CardDetailsActivity;
import com.expopay.android.model.CardEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class MyCardsListAdapter extends BaseAdapter {
    Context context;
    List<CardEntity> data;
    private Map<Integer, Boolean> status;

    public MyCardsListAdapter(Context context, List<CardEntity> data) {
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
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {
        final int index = i;
        final CardEntity card = data.get(i);
        ViewHodler vh = null;
        if (null == v) {
            v = LayoutInflater.from(context).inflate(R.layout.view_mycards_item, null);
            vh = new ViewHodler();
            vh.cardNumberText = (TextView) v.findViewById(R.id.mycards_item_cardnumber);
            vh.cardBalanceText = (TextView) v.findViewById(R.id.mycards_item_balance);
            vh.isDefaultText = (TextView) v.findViewById(R.id.mycards_item_isdefault);
            vh.cardTypeText = (TextView) v.findViewById(R.id.mycards_item_cardtype);
            vh.radio = (RadioButton) v.findViewById(R.id.mycards_item_radio);
            v.setTag(vh);
        } else {
            vh = (ViewHodler) v.getTag();
        }
        vh.cardNumberText.setText(card.getCardNumber());
        vh.cardBalanceText.setText(card.getBalance());
        Boolean isDefault = card.getIsDefault().equals("1");
        vh.isDefaultText.setVisibility(isDefault ? View.VISIBLE : View.GONE);
        vh.cardTypeText.setText(card.getCardType());
        vh.radio.setChecked(isDefault);
        vh.radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setRadioCheckd(index);
            }
        });
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), CardDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("card",card);
                context.startActivity(intent);
            }
        });
        if (status.get(index) == null) {
            status.put(index, vh.radio.isChecked());
        }
        vh.radio.setChecked(status.get(index));
        if (i % 2 == 0) {
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            v.setBackgroundColor(Color.parseColor("#f9f9f9"));
        }
        return v;
    }

    private class ViewHodler {
        TextView cardNumberText;
        TextView cardBalanceText;
        TextView isDefaultText;
        TextView cardTypeText;
        RadioButton radio;
    }

    public List<CardEntity> getData() {
        return data;
    }

    public void setData(List<CardEntity> data) {
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
