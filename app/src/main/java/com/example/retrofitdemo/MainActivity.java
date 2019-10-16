package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mBtnGet;
    private Button mBtnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnGet = findViewById(R.id.btn_get);
        mBtnGet.setOnClickListener((view) -> get());
        mBtnPost = findViewById(R.id.btn_post);
        mBtnPost.setOnClickListener((view) -> post());
    }

    private void get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<String> call = api.getResult1();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                runOnUiThread(() -> {
                            String result = response.body();
                            Toast.makeText(MainActivity.this, "onSuccess: " + result,
                                    Toast.LENGTH_SHORT).show();
                        }
                );
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "onFailure",
                            Toast.LENGTH_SHORT).show();
                    Log.e(TAG, t.getMessage());
                });
            }
        });

    }

    private void post() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<String> call = api.getResult2();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                runOnUiThread(() -> {
                            String result = response.body();
                            Toast.makeText(MainActivity.this, "onSuccess: " + result,
                                    Toast.LENGTH_SHORT).show();
                        }
                );
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "onFailure",
                            Toast.LENGTH_SHORT).show();
                    Log.e(TAG, t.getMessage());
                });
            }
        });
    }
}

interface API {
    @GET(" ")
    Call<String> getResult1();

    @POST("user")
    Call<String> getResult2();
}

