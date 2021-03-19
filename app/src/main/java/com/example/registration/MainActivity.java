package com.example.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView,mRecyclerView1;

    List<Flower> mFlowers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.main_page);


        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mFlowers = new ArrayList<>();


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView1 = findViewById(R.id.recyclerview1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView1.setLayoutManager(layoutManager1);


        FlowerAdapter adapter = new FlowerAdapter(mFlowers);
        mRecyclerView.setAdapter(adapter);

        ScrollAdapter adapter1 = new ScrollAdapter(mFlowers);
        mRecyclerView1.setAdapter(adapter1);

        mProgressBar.setVisibility(View.VISIBLE);

        FlowersAPI flowersAPI = FlowersAPI.retrofit.create(FlowersAPI.class);
        final Call<List<Flower>> call = flowersAPI.getData();
        call.enqueue(new Callback<List<Flower>>() {
                         @Override
                         public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                             // response.isSuccessfull() возвращает true если код ответа 2xx
                             if (response.isSuccessful()) {
                                 mFlowers.addAll(response.body());
                                 mRecyclerView.getAdapter().notifyDataSetChanged();
                                 mProgressBar.setVisibility(View.INVISIBLE);
                             } else {
                                 // Обрабатываем ошибку
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();
                                     mProgressBar.setVisibility(View.INVISIBLE);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Flower>> call, Throwable throwable) {
                             Toast.makeText(MainActivity.this, "Что-то пошло не так",
                                     Toast.LENGTH_SHORT).show();
                             mProgressBar.setVisibility(View.INVISIBLE);
                         }
                     }
        );


        ScrollAPI scrollAPI = ScrollAPI.retrofit.create(ScrollAPI.class);
        final Call<List<Flower>> call1 = scrollAPI.getData();
        call1.enqueue(new Callback<List<Flower>>() {
                          @Override
                          public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                              // response.isSuccessfull() возвращает true если код ответа 2xx
                              if (response.isSuccessful()) {
                                  mFlowers.addAll(response.body());
                                  mRecyclerView1.getAdapter().notifyDataSetChanged();
                                  mProgressBar.setVisibility(View.INVISIBLE);
                              } else {
                                  // Обрабатываем ошибку
                                  ResponseBody errorBody = response.errorBody();
                                  try {
                                      Toast.makeText(MainActivity.this, errorBody.string(),
                                              Toast.LENGTH_SHORT).show();
                                      mProgressBar.setVisibility(View.INVISIBLE);
                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                              }
                          }

                          @Override
                          public void onFailure(Call<List<Flower>> call, Throwable throwable) {
                              Toast.makeText(MainActivity.this, "Что-то пошло не так",
                                      Toast.LENGTH_SHORT).show();
                              mProgressBar.setVisibility(View.INVISIBLE);
                          }
                      }
        );



    }

}