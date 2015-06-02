package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by oliwer on 30/04/2015.
 */
public class ImageAdapter  extends BaseAdapter {

        private Context mContext;
        public ArrayList<Item> list;

        public ImageAdapter(Context c,ArrayList<Item> list)
        {
            mContext = c;
            this.list = list;
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

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);


                imageView.setLayoutParams(new GridView.LayoutParams(50, 50));

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


                imageView.setPadding(0,0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }


             imageView.setImageResource(list.get(position).getImg());

            return imageView;
        }

    }

