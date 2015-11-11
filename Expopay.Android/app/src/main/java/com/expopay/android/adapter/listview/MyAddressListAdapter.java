package com.expopay.android.adapter.listview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.activity.AddressDetailsActivity;
import com.expopay.android.model.AddressEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class MyAddressListAdapter extends BaseAdapter {
    Context context;
    List<AddressEntity> data;
    private Map<Integer, Boolean> status;

    public MyAddressListAdapter(Context context, List<AddressEntity> data) {
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
    public View getView(int i, View v, ViewGroup parent) {
        final int index = i;
        final AddressEntity address = data.get(index);
        ViewHolder vh = null;
        if (v == null) {
            vh = new ViewHolder();
            v = LayoutInflater.from(context).inflate(R.layout.view_myaddress_item, null);
            vh.mobileText = (TextView) v.findViewById(R.id.address_item_mobile);
            vh.nameText = (TextView) v.findViewById(R.id.address_item_name);
            vh.isDefaultText = (TextView) v.findViewById(R.id.address_item_isdefualt);
            vh.addressText = (TextView) v.findViewById(R.id.address_item_address);
            vh.radio = (RadioButton) v.findViewById(R.id.address_item_radio);
            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        boolean isDefault = "0".equals(address.getIsDefault());
        vh.mobileText.setText(address.getMobile());
        vh.nameText.setText(address.getPersonName());
        vh.isDefaultText.setVisibility(!isDefault ? View.VISIBLE : View.GONE);
        vh.radio.setChecked(isDefault);
        vh.addressText.setText(address.getProvinceName() + address.getCityName() + address.getDistrictName() + address.getAddress());
        vh.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioCheckd(index);
            }
        });
        if (status.get(index) == null) {
            status.put(index, vh.radio.isChecked());
        }
        vh.radio.setChecked(status.get(index));
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), AddressDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("address",address);
                context.startActivity(intent);
            }
        });
        if (i % 2 == 0) {
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            v.setBackgroundColor(Color.parseColor("#f9f9f9"));
        }
        return v;
    }

    private class ViewHolder {
        TextView nameText;
        TextView mobileText;
        TextView isDefaultText;
        TextView addressText;
        RadioButton radio;
    }

    public void setData(List<AddressEntity> data) {
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
