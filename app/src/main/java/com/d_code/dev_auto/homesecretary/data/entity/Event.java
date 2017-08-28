package com.d_code.dev_auto.homesecretary.data.entity;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by mcauto on 2017-08-29.
 */
@Table(name = "Events")
public class Event extends Model {
    @Column(name = "title")
    public String title;
    @Column(name = "date")
    public java.util.Date date;

    public static List<Event> getAll(){
        return new Select().from(Event.class).orderBy("title DESC").execute();
    }
    public static void delete(Event event){
        event.delete();
    }
    public static void deleteAll(List<Event> events)
    {
        for(int i=0; i<events.size();i++)
            events.get(i).delete();
    }
}
