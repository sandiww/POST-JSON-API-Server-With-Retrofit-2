package com.codewith.sandisuryadi.postretrofit;

import  android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codewith.sandisuryadi.postretrofit.api.BaseApiService;
import com.codewith.sandisuryadi.postretrofit.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahMatkulActivity extends AppCompatActivity {
    @BindView(R.id.etNamaDosen)
    EditText etNamaDosen;
    @BindView(R.id.etNamaMatkul)
    EditText etNamaMatkul;
    @BindView(R.id.btnSimpanMatkul)
    Button btnSimpanMatkul;
    ProgressDialog loading;

    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matkul);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnSimpanMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSimpanMatkul();
                // As soon as button is clicked, set is as empty
                etNamaDosen.setText("");
                etNamaMatkul.setText("");
            }
        });
    }

    private void requestSimpanMatkul(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false );

        mApiService.simpanMatkulRequest(etNamaDosen.getText().toString(), etNamaMatkul.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Toast.makeText(mContext, "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal Simpan Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
