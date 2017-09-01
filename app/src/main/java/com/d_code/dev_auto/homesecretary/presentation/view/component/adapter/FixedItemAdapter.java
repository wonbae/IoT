package com.d_code.dev_auto.homesecretary.presentation.view.component.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.data.entity.FixedEvent;

import java.util.ArrayList;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class FixedItemAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FixedEvent> fixedEvents;

    public FixedItemAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return fixedEvents.size();
    }

    public Object getItem(int position) {
        return fixedEvents.get(position);
    }

    public long getItemId(int position) {
        return fixedEvents.get(position).getId();
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null)
        {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(85,85));
        } else
        {
            textView = (TextView) convertView;
        }
        textView.setText(fixedEvents.get(position).getTitle());

        return textView;
    }
}