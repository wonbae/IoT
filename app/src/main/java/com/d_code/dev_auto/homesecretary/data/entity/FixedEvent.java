package com.d_code.dev_auto.homesecretary.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by mcauto on 2017. 9. 1..
 */
@Entity(nameInDb = "FixedEvents")
public class FixedEvent {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "title")
    public String title;

    @Property(nameInDb = "detail")
    private String detail;

    @Generated(hash = 844506024)
    public FixedEvent(Long id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    @Generated(hash = 608689103)
    public FixedEvent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    @Override
    public String toString() {
        return title;
    }
    public FixedEvent getEmpty(){
        FixedEvent empty = new FixedEvent();
        empty.setTitle("+ 새 항목 추가");
        return empty;
    }

}
