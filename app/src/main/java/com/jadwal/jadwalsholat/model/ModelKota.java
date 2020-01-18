package com.jadwal.jadwalsholat.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelKota {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("kota")
    @Expose
    private List<Kotum> kota = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Kotum> getKota() {
        return kota;
    }

    public void setKota(List<Kotum> kota) {
        this.kota = kota;
    }

}
