package com.expopay.android.adapter.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.http.listener.BitmapRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.OrderItemEntity;
import com.expopay.android.model.PaymentOrderEntity;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class PaymentOrderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<PaymentOrderEntity> data ;
    private Context context;

    public PaymentOrderAdapter(Context context,List<PaymentOrderEntity> data) {
        this.data = data;
        //根据context上下文加载布局
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        //在此适配器中所代表的数据集中的条目数
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        //获取数据集中与指定索引对应的数据项
        return data.get(i);
    }

    @Override
    public long getItemId(int position) {
        //获取在列表中与指定索引对应的行id
        return position;
    }

    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PaymentOrderEntity entity = data.get(position);
        List<OrderItemEntity> itemEntityList = entity.getOrderItems();
        final OrderItemEntity itemEntity = itemEntityList.get(position);
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.view_payment_order_item, null);
            holder.productImg = (ImageView) convertView.findViewById(R.id.imgPaymentOrder);
            holder.productName = (TextView) convertView.findViewById(R.id.titlePaymentOrder);
            holder.orderAmount = (TextView) convertView.findViewById(R.id.pricePaymentOrder);
            holder.orderTime = (TextView) convertView.findViewById(R.id.datePaymentOrder);
            holder.orderStatus = (TextView) convertView.findViewById(R.id.statePaymentOrder);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.productName.setText(entity.getPublicParamName());
        holder.orderAmount.setText(entity.getOrderAmount());
        holder.orderTime.setText(entity.getOrderTime());
        holder.orderStatus.setText(entity.getOrderStatus());
        if (MyApplication.cache.getBitmapFromMemCache(itemEntity.getProductImg()) != null) {
            holder.productImg.setImageBitmap(MyApplication.cache.getBitmapFromMemCache(itemEntity.getProductImg()));
        } else {
            Request request = new Request(itemEntity.getProductImg(), RequestMethod.GET);
            final ViewHolder finalHolder = holder;
            request.setIRequestListener(new BitmapRequestListener() {
                @Override
                public void onFilure(Exception e) {

                }

                @Override
                public void onSuccess(Object o) {
                    finalHolder.productImg.setImageBitmap((Bitmap) o);
                    MyApplication.cache.addBitmapToMemoryCache(itemEntity.getProductImg(), (Bitmap) o);
                }

                @Override
                public void onProgressUpdate(int i, int i1) {

                }
            });
            request.execute();
        }

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#f9f9f9"));
        }

        return convertView;
    }
    //ViewHolder静态类
    static class ViewHolder {
        public ImageView productImg;
        public TextView productName;
        public TextView orderAmount;
        public TextView orderTime;
        public TextView orderStatus;
    }

    public List<PaymentOrderEntity> getData() {
        return data;
    }

    public void setData(List<PaymentOrderEntity> data) {
        this.data = data;
    }
}
