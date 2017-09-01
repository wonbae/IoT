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

    /**
     * type : 0(날씨 - 저장용 아님), 1(길 찾기), 2(준비물)
     */
    @Property(nameInDb = "type")
    private int type;

    @Property(nameInDb = "pathUri")
    private String pathUri;

    @Property(nameInDb = "departureToArrival")
    private String departureToArrival;

    @Property(nameInDb = "detail")
    private String detail;

    @Property(nameInDb = "icode")
    private int iCode;

    @Generated(hash = 1151438511)
    public Event(Long id, String title, Date date, int type, String pathUri,
            String departureToArrival, String detail, int iCode) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.type = type;
        this.pathUri = pathUri;
        this.departureToArrival = departureToArrival;
        this.detail = detail;
        this.iCode = iCode;
    }

    @Generated(hash = 344677835)
    public Event() {
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPathUri() {
        return this.pathUri;
    }

    public void setPathUri(String pathUri) {
        this.pathUri = pathUri;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getICode() {
        return this.iCode;
    }

    public void setICode(int iCode) {
        this.iCode = iCode;
    }

    @Override
    public String toString() {
        return title;
    }
    public Event getEmpty(Date d){
        Event empty = new Event();
        empty.setTitle("+ 새 항목 추가");
        empty.setDate(d);
        return empty;
    }

    public String getDepartureToArrival() {
        return this.departureToArrival;
    }

    public void setDepartureToArrival(String departureToArrival) {
        this.departureToArrival = departureToArrival;
    }
}
