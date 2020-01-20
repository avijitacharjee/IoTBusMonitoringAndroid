package com.example.iotbusmonitoringsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BusListAdapter extends BaseAdapter {

    Context context;
    private String[] values;

    public BusListAdapter(Context context,String values[])
    {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater= LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.but_list_item,parent,false);
            viewHolder.setTextView((TextView) convertView.findViewById(R.id.category_list_item_text_view));

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.textView.setText(values[position]);

        return convertView;
    }
    static class ViewHolder{
        private TextView textView;

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
