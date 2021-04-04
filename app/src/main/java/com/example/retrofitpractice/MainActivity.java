package com.example.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView showTV;
    private String url="https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTV= findViewById(R.id.show_tv);
        showTV.setText("");

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices=retrofit.create(ApiServices.class);
        Call<List<Model>> call = apiServices.getModels();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                List<Model> data=response.body();
                for(int i=0;i<data.size();i++)
                {
                    showTV.append(" id: "+ data.get(i).getId()+"\nTitle :"+data.get(i).getTitle()+"\n \n \n");
                }

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
               Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_LONG).show();
            }
        });

    }
}