package com.example.retrofitapicall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button getData;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData = findViewById(R.id.get_data);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {

                        Toast.makeText(MainActivity.this, " API Called Successfully... ", Toast.LENGTH_SHORT).show();

                        Log.e(TAG, "onResponse: code: " +response.code());


                        ArrayList<Model.data> data = response.body().getData();

                        for(Model.data data1 : data){
                            Log.e(TAG, "onResponse: emails : " +data1.getEmail());

                        }


                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onFailure: code: " +t.getMessage());

                    }
                });
            }
        });


    }
}