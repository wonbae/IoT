package com.d_code.dev_auto.homesecretary.presentation.view.component.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEvent;
import com.d_code.dev_auto.homesecretary.presentation.view.component.listener.FixedEventClickListener;
import com.d_code.dev_auto.homesecretary.presentation.view.component.viewholder.FixedEventViewHolder;
import com.d_code.dev_auto.homesecretary.presentation.view.component.viewholder.NotifyItemViewHolder;

import java.util.ArrayList;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class FixedEventAdapter extends RecyclerView.Adapter<FixedEventViewHolder> {
    private ArrayList<FixedEvent> fixedEvents = new ArrayList<>();
    private View.OnClickListener listener;

    @Override
    public FixedEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fixed, parent, false);
        return new FixedEventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FixedEventViewHolder holder, int position) {
        holder.fixedEventTitle.setText(fixedEvents.get(position).getTitle());
        holder.fixedEventTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
        holder.fixedEventTitle.setHint(position+"");
    }

    public void setClickListener(View.OnClickListener callback){
        this.listener = callback;
    }

    @Override
    public int getItemCount() {
        return fixedEvents.size();
    }

    public void addFixedEvent(FixedEvent fixedEvent){
        fixedEvents.add(fixedEvent);
        notifyDataSetChanged();
    }
    public void addFixedEventAll(ArrayList<FixedEvent> fixedEvents)
    {
        this.fixedEvents = fixedEvents;
        notifyDataSetChanged();
    }
    public void setFixedEvent(int position, FixedEvent event){
        fixedEvents.set(position, event);
    }
    public ArrayList<FixedEvent> getFixedEventAll(){
        return fixedEvents;
    }

}
