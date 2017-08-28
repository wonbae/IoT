package com.d_code.dev_auto.homesecretary.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by mcauto on 2017-08-29.
 */
@Table(name = "CheckList")
public class CheckList extends Model {
    @Column(name="title")
    public String title;
    @Column(name="detail")
    public String detail;

    public static List<CheckList> getAll(){
        return new Select().from(CheckList.class).orderBy("title DESC").execute();
    }
}
