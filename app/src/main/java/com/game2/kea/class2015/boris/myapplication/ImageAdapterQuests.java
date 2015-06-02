package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oliwer on 08/05/2015.
 */
public class ImageAdapterQuests extends BaseAdapter{
    private Context mContext;

    public ArrayList<Quest> list;
    private static LayoutInflater inflater=null;

    public ImageAdapterQuests(Context c,ArrayList<Quest> list)
    {
        mContext = c;
        this.list = list;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

   public class Holder
   {
       TextView tv;
       ImageView img;
   }

   public View getView(final int position, View convertView, ViewGroup parent) {


       Holder holder=new Holder();
       View rowView;

       rowView = inflater.inflate(R.layout.program_list, null);
       holder.tv=(TextView) rowView.findViewById(R.id.textView1);
       holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
       holder.tv.setText(list.get(position).getName());

       return rowView;
   }
}
