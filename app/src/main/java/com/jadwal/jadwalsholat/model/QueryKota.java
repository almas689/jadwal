package com.jadwal.jadwalsholat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryKota {

    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}