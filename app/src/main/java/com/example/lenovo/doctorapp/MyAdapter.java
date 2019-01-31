package com.example.lenovo.doctorapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by lenovo on 7/23/2017.
 */

public class MyAdapter extends BaseAdapter

{
    private List<BeanClerk> mylist;
    //String name;

    private LayoutInflater lf=null;
    Context ctx=null;
    public MyAdapter(Activity activity, List<BeanClerk>mylist)
    {
        ctx= activity.getApplicationContext();
        this.mylist=mylist;
        lf=LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null) {
            convertView = lf.inflate(R.layout.mycustom, parent,false);
        }
        TextView tv=(TextView)convertView.findViewById(R.id.tv);
        ImageView im=(ImageView)convertView.findViewById(R.id.img1) ;
        TextView tv1=(TextView)convertView.findViewById(R.id.tv1);
        TextView tvpass=(TextView)convertView.findViewById(R.id.tvpass);
        TextView tvemail=(TextView)convertView.findViewById(R.id.tvemail);
        TextView tvphone=(TextView)convertView.findViewById(R.id.tvphone);


        BeanClerk f=mylist.get(position);
        tv1.setText(f.getId());
        tv.setText(f.getName());
        tvpass.setText(f.getPassword());
        tvemail.setText(f.getEmail());
        tvphone.setText(f.getPhone());


        byte[]arr=   f.getClerkPic();
        ByteArrayInputStream bs=new ByteArrayInputStream(arr);

        Bitmap bm=BitmapFactory.decodeStream(bs);
        im.setImageBitmap(bm);

        return convertView;
    }
}
