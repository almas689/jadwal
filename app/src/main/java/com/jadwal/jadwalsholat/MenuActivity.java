package com.jadwal.jadwalsholat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.jadwal.jadwalsholat.api.ApiService;
import com.jadwal.jadwalsholat.api.ApiUrl;

import com.jadwal.jadwalsholat.model.ModelKota;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    public EditText tv_kota_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tv_kota_value = findViewById(R.id.tv_kota_value);

        findViewById(R.id.btn_jadwal_sholat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getKota();

//                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });
    }


    private void getKota(){
        progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setMessage("Silahkan tunggu, sedang mencari kota ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        ApiService apiService = retrofit.create(ApiService.class);
        Call<ModelKota> call = apiService.getKota(tv_kota_value.getText().toString());

        call.enqueue(new Callback<ModelKota>() {
            @Override
            public void onResponse(Call<ModelKota> call, Response<ModelKota> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {



                    String message= response.body().getKota().get(0).getId();

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("kode", message);

                    startActivity(intent);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ModelKota> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MenuActivity.this, "Server Sibuk..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
