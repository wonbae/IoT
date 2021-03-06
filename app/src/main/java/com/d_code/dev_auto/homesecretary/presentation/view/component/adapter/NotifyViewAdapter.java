package com.d_code.dev_auto.homesecretary.presentation.view.component.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.entity.Event;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEvent;
import com.d_code.dev_auto.homesecretary.data.model.Weather;
import com.d_code.dev_auto.homesecretary.presentation.view.activity.RoadSearchActivity;
import com.d_code.dev_auto.homesecretary.presentation.view.component.viewholder.NotifyItemViewHolder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class NotifyViewAdapter extends RecyclerView.Adapter<NotifyItemViewHolder> {
    private List<Event> events = new ArrayList<>();
    private List<FixedEvent> fixedEvents = new ArrayList<>();
    private Context context;
    public NotifyViewAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public NotifyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify, parent, false);
        return new NotifyItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotifyItemViewHolder holder,final int position) {
        Event event = events.get(position);
        switch(event.getType())
        {
            case 0: // 날씨 (오직 하나, db에 저장되지 않음)
                Drawable weatherIcon = context.getDrawable(Weather.icon_drawable_id[event.getICode()]);
                Drawable weatherBackground = context.getDrawable(Weather.bg_drawable_id[event.getICode()]);
                holder.icon.setImageDrawable(weatherIcon);
                holder.name.setText(event.getTitle());
                holder.comment.setText(event.getDetail());
                holder.background.setImageDrawable(weatherBackground);
                break;
            case 1: // 지도 (복수 개 가능, 저장된 정보)
                IconicsDrawable marker = new IconicsDrawable(context)
                        .icon(FontAwesome.Icon.faw_map_marker)
                        .color(Color.WHITE)
                        .sizeDp(24);
                holder.icon.setImageDrawable(marker);
                holder.name.setText("길 찾기");
                holder.comment.setText(event.getDepartureToArrival());
                holder.background.setImageDrawable(context.getDrawable(R.drawable.destination_section));
                holder.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(events.get(position).getPathUri() != null)  {
                            Intent i = new Intent(context, RoadSearchActivity.class);
                            i.putExtra("pathURI", events.get(position).getPathUri());
                            context.startActivity(i);
                        }else{
                            Toast.makeText(context, "일정이 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void addWeatherEvent(Event event){
        if(events.size()>0){
            events.set(0,event);
        }
        else{
            events.add(event);
        }
        notifyDataSetChanged();
    }
    public void addActivityEvent(ArrayList<Event> list){
        for(int i=0;i<list.size();i++)
            events.add(list.get(i));
        notifyDataSetChanged();
    }
    public void addFixedEvent(ArrayList<FixedEvent> list){
        for(int i=0;i<list.size();i++)
            fixedEvents.add(list.get(i));
        notifyDataSetChanged();
    }
    public void addEvent(Event event){
        events.add(event);
        notifyDataSetChanged();
    }
}
