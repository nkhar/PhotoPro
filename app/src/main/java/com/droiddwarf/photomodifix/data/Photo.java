package com.droiddwarf.photomodifix.data;

import com.afollestad.inquiry.annotations.Column;

import java.io.Serializable;

public class Photo implements Serializable {

    public Photo() {
    }

    @Column
    public long _id;
    @Column
    public String _data;
    @Column
    public long datetaken;


}
