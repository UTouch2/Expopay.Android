package com.expopay.android.adapter.listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.activity.AddressDetailsActivity;
import com.expopay.android.model.AddressEntity;

import java.util.List;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class MyAddressListAdapter extends BaseAdapter {
    Context context;
    List<AddressEntity> data;
    public MyAddressListAdapter(Context context, List<AddressEntity> data) {
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
    public View getView(int i, View v, ViewGroup parent) {
        final AddressEntity address = data.get(i);
        ViewHolder vh =null;
        if( v ==null){
            vh = new ViewHolder();
            v = LayoutInflater.from(context).inflate(R.layout.view_myaddress_item,null);
            vh.mobileText = (TextView)v.findViewById(R.id.address_item_mobile);
            vh.nameText = (TextView)v.findViewById(R.id.address_item_name);
            vh.isDefaultText = (TextView)v.findViewById(R.id.address_item_isdefualt);
            vh.addressText = (TextView)v.findViewById(R.id.address_item_address);
            vh.redadio = (RadioButton)v.findViewById(R.id.address_item_radio);
            v.setTag(vh);
        }else{
            vh = (ViewHolder)v.getTag();
        }
        boolean isDefault = "0".equals(address.getIsDefault());
        vh.mobileText.setText(address.getMobile());
        vh.nameText.setText(address.getPersonName());
        vh.isDefaultText.setVisibility(!isDefault ? View.VISIBLE : View.GONE);
        vh.redadio.setChecked(isDefault);
        vh.addressText.setText(address.getProvinceName() + address.getCityName() + address.getDistrictName() + address.getAddress());
        vh.redadio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), AddressDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return v;
    }
    private class ViewHolder{
        TextView nameText;
        TextView mobileText;
        TextView isDefaultText;
        TextView addressText;
        RadioButton redadio;
    }
}
