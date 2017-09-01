package com.d_code.dev_auto.homesecretary.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mcauto on 2017. 9. 1..
 */
@Entity(nameInDb = "FixedEvents")
public class FixedEvent {
    @Id
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
}
