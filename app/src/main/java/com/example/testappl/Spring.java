package com.example.testappl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Spring extends AppCompatActivity {
    TextView tvLog;
    RecyclerView recyclerView;
    List<SpringUser> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);

        checkPerm();
        tvLog = findViewById(R.id.tv_spring_log);
        recyclerView = findViewById(R.id.rv_spring);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpringService springService = retrofit.create(SpringService.class);

        Call<List<SpringUser>> call = springService.fetchAllUsers();

        call.enqueue(new Callback<List<SpringUser>>() {
            @Override
            public void onResponse(Call<List<SpringUser>> call, Response<List<SpringUser>> response) {
                if (!response.isSuccessful()){
                    tvLog.setText("Code: " + response.code());
                    return;
                }
                List<SpringUser> list = response.body();

                for (SpringUser usr : list){
//                    String content = "";
//                    content += "ID: " + usr.getId() + "\n";
//                    content += "Name: " + usr.getName() + "\n";

                    userList.add(new SpringUser(usr.getId(), usr.getName()));
                }
            }

            @Override
            public void onFailure(Call<List<SpringUser>> call, Throwable t) {
                tvLog.setText(t.getMessage());
            }
        });

        final RVSpringAdapter rvSpringAdapter = new RVSpringAdapter(userList); //передать лист сюда
        recyclerView.setAdapter(rvSpringAdapter);
    }

    void checkPerm() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }
    }
}

class RVSpringAdapter extends RecyclerView.Adapter<RVSpringAdapter.SpringViewHolder>{
    List<SpringUser> userList;

    public RVSpringAdapter(List<SpringUser> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public SpringViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_spring, viewGroup, false);
        return new SpringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpringViewHolder viewHolder, int i) {
        viewHolder.textView.setText("");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SpringViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public SpringViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_spring);
        }
    }
}
