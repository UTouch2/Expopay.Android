package com.expopay.android.adapter.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.AddressEntity;

import java.util.List;

public class ChooseAddressAdapter extends BaseAdapter {
    Context context;
    List<AddressEntity> data;
    private LayoutInflater mInflater;

    public ChooseAddressAdapter(Context context, List<AddressEntity> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
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
    public View getView(int position, View view, ViewGroup parent) {
        final AddressEntity entity = data.get(position);
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            view = mInflater.inflate(R.layout.view_chooseaddress_item, null);
            holder.chooseAddressNane = (TextView) view.findViewById(R.id.chooseaddress_item_name);
            holder.chooseAddressPhone = (TextView) view.findViewById(R.id.chooseaddress_item_phone);
            holder.chooseAddressAddress = (TextView) view.findViewById(R.id.chooseaddress_item_address);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.chooseAddressNane.setText(entity.getPersonName());
        holder.chooseAddressPhone.setText(entity.getMobile());
        holder.chooseAddressAddress.setText(entity.getAddress());

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            view.setBackgroundColor(Color.parseColor("#F8F8F8"));
        }

        return view;
    }

    public List<AddressEntity> getData() {
        return data;
    }

    public void setData(List<AddressEntity> data) {
        this.data = data;
    }

    static class ViewHolder {
        public TextView chooseAddressNane;
        public TextView chooseAddressPhone;
        public TextView chooseAddressAddress;
    }
}
