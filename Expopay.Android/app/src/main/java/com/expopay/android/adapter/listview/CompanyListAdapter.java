package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.CompanyEntity;

import java.util.List;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class CompanyListAdapter extends BaseAdapter {
    Context context;
    List<CompanyEntity> data;

    public CompanyListAdapter(Context context, List<CompanyEntity> data) {
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
        CompanyEntity c = data.get(position);
        ViewHolder vh =null;
        if (null != convertView) {
            vh = (ViewHolder)convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_myactionbar, null);
            vh = new ViewHolder();
            convertView.setTag(vh);
        }
        vh.compantName.setText(c.getCompanyName());
        return convertView;
    }
}

class ViewHolder {
    TextView compantName;
}