package com.expopay.android.adapter.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.expopay.android.R;
import com.expopay.android.model.PropertiesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<PropertiesEntity> data;
    private Context context;
    private Map<Integer,Boolean> map;

    private static String str;
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }


    public PropertiesAdapter(Context context, List<PropertiesEntity> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.map =new HashMap<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PropertiesEntity entity = data.get(position);
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.view_button_item, null);
            holder.button = (Button) convertView.findViewById(R.id.buttonItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.button.setText(entity.getProperties());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Integer key:map.keySet()){
                    map.put(key,false);
                }
                map.put(position,true);
                notifyDataSetChanged();
            }
        });
        if(!map.containsKey(position)){
            map.put(position,false);
        }
        setChecked(holder.button, map.get(position));

        return convertView;
    }

    //ViewHolder静态类
    static class ViewHolder {
        public Button button;
    }

    private void setChecked(Button button, boolean checked) {
        if (checked) {
            setStr(button.getText().toString().trim());
            button.setBackgroundResource(R.mipmap.mall_outline);
        }else{
            button.setBackgroundResource(R.drawable._button_selectproduct);
        }
    }
}
