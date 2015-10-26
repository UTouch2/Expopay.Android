package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.CityEntity;
import com.expopay.android.model.CompanyEntity;

import java.util.List;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class CityListAdapter extends BaseAdapter {
    Context context;
    List<CityEntity> data;

    public CityListAdapter(Context context, List<CityEntity> data) {
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        CityEntity c = data.get(position);
        ViewHolder vh = null;
        if (null != convertView) {
            vh = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_citylist_item, null);
            vh = new ViewHolder();
            vh.compantName = (TextView) convertView.findViewById(R.id.citylist_item_name);
            vh.arrow = (ImageView)convertView.findViewById(R.id.citylist_item_arraw);
            convertView.setTag(vh);
        }
        //convertView.setTag(2, c);
        vh.compantName.setText(c.getName());
        if(c.getLevel() == 3){
            vh.arrow.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView compantName;
        ImageView arrow;
    }
}

