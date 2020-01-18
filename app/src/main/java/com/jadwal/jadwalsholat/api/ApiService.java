package com.jadwal.jadwalsholat.api;

import com.jadwal.jadwalsholat.model.Data;
import com.jadwal.jadwalsholat.model.Jadwal;
import com.jadwal.jadwalsholat.model.ModelJadwal;
import com.jadwal.jadwalsholat.model.ModelKota;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("jadwal/kota/{kota}/tanggal/{tanggal}")
    Call<ModelJadwal> getJadwal(@Path("kota") String kota, @Path("tanggal") String tanggal);

    @GET("kota/nama/{namakota}")
    Call<ModelKota> getKota (@Path("namakota") String namakota);
}
