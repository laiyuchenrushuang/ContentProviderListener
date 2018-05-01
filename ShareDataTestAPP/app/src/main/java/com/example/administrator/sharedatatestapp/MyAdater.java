package com.example.administrator.sharedatatestapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MyAdater extends BaseAdapter {
    private ArrayList<String[]> mList;
    private LayoutInflater mLayoutInflater;
    private static final String TAG = "MyAdater";

    public MyAdater(Context context, ArrayList<String[]> list) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.text1);
            TextView appName = (TextView) convertView.findViewById(R.id.text2);
            TextView requestCount = (TextView) convertView.findViewById(R.id.text3);

            viewHolder = new ViewHolder(name, appName, requestCount);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String[] textArray = mList.get(position);
        Log.d(TAG, "textArray[0]=  "+textArray[0]+"textArray[1] ="+textArray[1]+"textArray[2] ="+textArray[2]);
        viewHolder.name.setText(textArray[0]);
        viewHolder.appName.setText(textArray[2]);
        viewHolder.requestCount.setText(textArray[1]);

        return convertView;
    }

    public static class ViewHolder{
        TextView name;
        TextView appName;
        TextView requestCount;

        public ViewHolder(TextView name, TextView appName, TextView requestCount) {
            this.name = name;
            this.appName = appName;
            this.requestCount = requestCount;
        }
    }
}
