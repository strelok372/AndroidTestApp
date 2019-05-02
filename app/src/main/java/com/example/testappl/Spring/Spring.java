package com.example.testappl.Spring;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testappl.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Spring extends AppCompatActivity {
    TextView tvLog;
    EditText editText;
    RecyclerView recyclerView;
    List<SpringUser> userList;
    RVSpringAdapter rvSpringAdapter;
    SpringService springService;
    int userId = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);

        tvLog = findViewById(R.id.tv_spring_log);
        recyclerView = findViewById(R.id.rv_spring);
        editText = findViewById(R.id.et_spring);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        springService = retrofit.create(SpringService.class);
        userList = new ArrayList<>();
        getRecords();

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        rvSpringAdapter = new RVSpringAdapter(userList); //передать лист сюда
        recyclerView.setAdapter(rvSpringAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.b_spring_load:
                rvSpringAdapter.notifyDataSetChanged();
                break;
            case R.id.b_spring_add:
                createRec();
                break;
        }
    }

    public void getRecords(){
        Call<List<SpringUser>> call = springService.getRecordsByUser(userId);

        call.enqueue(new Callback<List<SpringUser>>() {
            @Override
            public void onResponse(Call<List<SpringUser>> call, Response<List<SpringUser>> response) {
                if (!response.isSuccessful()){
                    tvLog.setText("Code: " + response.code());
                    return;
                }
                List<SpringUser> list = response.body();
                if (list != null){
                    userList.addAll(list);
                    rvSpringAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<SpringUser>> call, Throwable t) {
                tvLog.setText(t.getMessage());
            }
        });
    }

    private void createRec(){
        final SpringUser user = new SpringUser(editText.getText().toString(), userId);
        Call<SpringUser> call = springService.newRecord(user);
        call.enqueue(new Callback<SpringUser>() {
            @Override
            public void onResponse(Call<SpringUser> call, Response<SpringUser> response) {
                if (!response.isSuccessful()){
                    tvLog.setText("Log: " + response.code());
                    return;
                }else {
                    userList.add(user);
                }

            }

            @Override
            public void onFailure(Call<SpringUser> call, Throwable t) {
                tvLog.setText(t.getMessage());
            }
        });
    }

    private void updateRec(){
        final SpringUser springUser = new SpringUser(); //потом надо заполнить будет
        final int selId = 0;
        Call<SpringUser> call = springService.updRecord(selId, springUser);
        call.enqueue(new Callback<SpringUser>() {
            @Override
            public void onResponse(Call<SpringUser> call, Response<SpringUser> response) {
                if (!response.isSuccessful()){
                    String log = "Log: " + response.code();
                    tvLog.setText(log);
                }else {
                    userList.set(selId, springUser);
                }
            }

            @Override
            public void onFailure(Call<SpringUser> call, Throwable t) {

            }
        });
    }

    private void deleteRec(){
        final int selId = 0;
        Call<Void> call = springService.delRecord(selId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    String log = "Log: " + response.code();
                    tvLog.setText(log);
                }else {
                    userList.remove(selId);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}

