package com.example.liuyulong.myapplication.viper_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liuyulong.myapplication.R;

public class ListTestAdapter extends BaseAdapter {

    private String[] data = new String[] { "hello", "world","hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello",  };
    private LayoutInflater inflater;

    public ListTestAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.length;
    }
    @Override
    public String getItem(int position) {
        return data[position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String content = getItem(position);
        holder.tv.setText(content);
        return convertView;
    }
    static class ViewHolder {
        TextView tv;
    }
}

