package com.d_code.dev_auto.homesecretary.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;


/**
 * Created by mcauto on 2017-08-29.
 */
@Entity(nameInDb = "Events")
public class Event {
    @Id
    private Long id;

    @Property(nameInDb = "title")
    public String title;

    @Property(nameInDb = "date")
    private Date date;

    @Property(nameInDb = "pathUri")
    private String pathUri;

    @Generated(hash = 1605621807)
    public Event(Long id, String title, Date date, String pathUri) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.pathUri = pathUri;
    }

    @Generated(hash = 344677835)
    public Event() {
    }
    public Event getEmpty(Date d){
        return new Event(id, "+ 새 항목 추가", d, "");
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
        return title;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
