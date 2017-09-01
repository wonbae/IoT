package com.d_code.dev_auto.homesecretary.presentation.view.component.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.R;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class NotifyItemViewHolder extends RecyclerView.ViewHolder{
    public ImageView icon;
    public TextView name;
    public TextView comment;
    public ImageView background;

    public NotifyItemViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        name = (TextView) itemView.findViewById(R.id.name);
        comment = (TextView) itemView.findViewById(R.id.comment);
        background = (ImageView) itemView.findViewById(R.id.notify_background);
    }
}

