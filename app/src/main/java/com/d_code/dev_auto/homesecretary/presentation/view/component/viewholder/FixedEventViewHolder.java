package com.d_code.dev_auto.homesecretary.presentation.view.component.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.R;

import org.w3c.dom.Text;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class FixedEventViewHolder extends RecyclerView.ViewHolder {
    public TextView fixedEventTitle;
    public FixedEventViewHolder(View itemView) {
        super(itemView);
        fixedEventTitle = (TextView)itemView.findViewById(R.id.fixed_event_title);


    }
}
