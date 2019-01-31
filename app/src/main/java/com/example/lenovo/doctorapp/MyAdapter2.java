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

import static com.example.lenovo.doctorapp.R.id.img;

/**
 * Created by lenovo on 7/25/2017.
 */

public class MyAdapter2 extends BaseAdapter {
    private List<BeanPatient> mylist;
    //String name;

    private LayoutInflater lf = null;
    Context ctx = null;

    public MyAdapter2(Activity activity, List<BeanPatient> mylist)
    {
        ctx = activity.getApplicationContext();
        this.mylist = mylist;
        lf = LayoutInflater.from(activity);
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
            convertView = lf.inflate(R.layout.customlayout, parent,false);
        }
        TextView txttoken=(TextView)convertView.findViewById(R.id.txttoken);
        TextView txtname=(TextView)convertView.findViewById(R.id.txtname);
        TextView txtphone=(TextView)convertView.findViewById(R.id.txtphone);
        TextView txtproblem=(TextView)convertView.findViewById(R.id.txtproblem);
        TextView txtdate=(TextView)convertView.findViewById(R.id.txtdate);
        ImageView im=(ImageView)convertView.findViewById(img);

        BeanPatient f=mylist.get(position);
        txttoken.setText(f.getToken());
        txtname.setText(f.getName());
        txtphone.setText(f.getPhone());
        txtproblem.setText(f.getProblem());
        txtdate.setText(f.getDate());

        byte[]arr=   f.getPic();
        ByteArrayInputStream bs=new ByteArrayInputStream(arr);

        Bitmap bm= BitmapFactory.decodeStream(bs);
        im.setImageBitmap(bm);

        return convertView;
    }
}