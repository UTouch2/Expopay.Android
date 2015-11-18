package com.expopay.android.adapter.gridview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.activity.ProductDetailsActivity;
import com.expopay.android.model.MallProductEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class MallProductAdapter extends BaseAdapter{

    private LayoutInflater inflater ;
    private List<MallProductEntity> data ;
    private Context context;

    public MallProductAdapter(Context context,List<MallProductEntity> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final MallProductEntity entity = data.get(position);
        final ViewHolder holder;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = inflater.inflate(R.layout.view_mall, null);
            holder.productImg = (ImageView) convertView.findViewById(R.id.image_grid_item);
            holder.productName = (TextView) convertView.findViewById(R.id.text1_grid_item);
            holder.orderAmount = (TextView) convertView.findViewById(R.id.text2_grid_item);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.productImg.setImageBitmap(returnBitMap("http://p1.so.qhimg.com/t0110fcac150d39c481.jpg"));//entity.getProductImg()
        holder.productName.setText(entity.getProductName());
        holder.orderAmount.setText(entity.getOrderAmount());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);

                Bitmap bmp=((BitmapDrawable)holder.productImg.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte [] bitmapByte =baos.toByteArray();
                intent.putExtra("bitmap", bitmapByte);

                intent.putExtra("productName",holder.productName.getText().toString().trim());
                intent.putExtra("orderAmount",holder.orderAmount.getText().toString().trim());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    //ViewHolder静态类
    static class ViewHolder {
        public ImageView productImg;
        public TextView productName;
        public TextView orderAmount;
    }

    public static Bitmap returnBitMap(String url) {
        Log.i("returnBitMap", "url=" + url);
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
