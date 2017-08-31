package com.d_code.dev_auto.homesecretary.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;


/**
 * Created by mcauto on 2017-08-29.
 */
@Entity(nameInDb = "Events")
public class Event {
    @Property(nameInDb = "title")
    public String title;

    @Property(nameInDb = "date")
    private Date date;

    @Property(nameInDb = "pathUri")
    private String pathUri;

    @Generated(hash = 996856254)
    public Event(String title, Date date, String pathUri) {
        this.title = title;
        this.date = date;
        this.pathUri = pathUri;
    }

    @Generated(hash = 344677835)
    public Event() {
    }
    public Event getEmpty(){
        return new Event("+ 새 항목 추가", new Date(), null);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPathUri() {
        return this.pathUri;
    }

    public void setPathUri(String pathUri) {
        this.pathUri = pathUri;
    }

    @Override
    public String toString() {
        return title.toString();
    }
}
