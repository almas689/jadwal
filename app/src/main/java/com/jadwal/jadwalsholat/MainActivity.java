package com.jadwal.jadwalsholat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;

import com.jadwal.jadwalsholat.api.ApiService;
import com.jadwal.jadwalsholat.api.ApiUrl;
import com.jadwal.jadwalsholat.model.Data;
import com.jadwal.jadwalsholat.model.Jadwal;
import com.jadwal.jadwalsholat.MenuActivity;
import com.jadwal.jadwalsholat.model.ModelJadwal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv_ashar_value,tv_dhuha_value,tv_dzuhur_value,tv_isya_value,tv_maghrib_value,tv_subuh_value;

    private ProgressDialog progressDialog;
    String pattern = "YYYY-MM-DD";
    DateFormat df = new SimpleDateFormat(pattern);
    Date today = Calendar.getInstance().getTime();
    public String todayAsString = df.format(today);
    public String tgl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        tv_ashar_value = findViewById(R.id.tv_ashar_value);
        tv_dhuha_value = findViewById(R.id.tv_dhuha_value);
        tv_dzuhur_value = findViewById(R.id.tv_dzuhur_value);
        tv_isya_value = findViewById(R.id.tv_isya_value);
        tv_maghrib_value = findViewById(R.id.tv_maghrib_value);
        tv_subuh_value = findViewById(R.id.tv_subuh_value);

        getJadwal();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getJadwal(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait / Silahkan tunggu ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Intent intent=getIntent();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ModelJadwal> call = apiService.getJadwal(intent.getStringExtra("kode"),todayAsString);

        call.enqueue(new Callback<ModelJadwal>() {
            @Override
            public void onResponse(Call<ModelJadwal> call, Response<ModelJadwal> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    getSupportActionBar().setTitle(response.body().getJadwal().getData().getTanggal());
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    tv_ashar_value.setText(response.body().getJadwal().getData().getAshar());
                    tv_dhuha_value.setText(response.body().getJadwal().getData().getDhuha());
                    tv_dzuhur_value.setText(response.body().getJadwal().getData().getDzuhur());
                    tv_isya_value.setText(response.body().getJadwal().getData().getIsya());
                    tv_maghrib_value.setText(response.body().getJadwal().getData().getMaghrib());
                    tv_subuh_value.setText(response.body().getJadwal().getData().getSubuh());

                } else {

                }
            }

            @Override
            public void onFailure(Call<ModelJadwal> call, Throwable t) {

            }
        });
    }
}
