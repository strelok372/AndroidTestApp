package com.example.testappl.Sampler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.testappl.R;

import java.util.ArrayList;

public class SamplerAdapter extends RecyclerView.Adapter<SamplerAdapter.SamplerViewHolder> {
    ArrayList<SamplerItem> mArrayList = new ArrayList<>();
    int step[] = {500, 1000, 2000, 4000};
    String[] scroll = {"1/8", "1/4", "1/2", "1"};
    Context mContext;
    int a = 0;
    final String TAG = "My log: ";
    final int[] icon = {R.drawable.ic_record, R.drawable.ic_stop, R.drawable.ic_play, R.drawable.ic_pause};

    protected SamplerAdapter() {
    }

//    SamplerAdapter() {
//    }

    //Шаг №0 раздувание xml элемента и его отправка на связку в холдер
    @NonNull
    @Override
    public SamplerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_sampler, viewGroup, false);
        return new SamplerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SamplerViewHolder samplerViewHolder, final int i) {
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv_sh_main:
                        mArrayList.get(i).setStatus(mArrayList.get(i).state);
                        if (mArrayList.get(i).state < 3) {
                            mArrayList.get(i).state += 1;
                        } else {
                            mArrayList.get(i).state -= 1;
                        }
                        notifyItemChanged(i);
                        break;
                    case R.id.iv_sh_close:
                        mArrayList.remove(i);
                        break;
                    case R.id.iv_sh_newtrack:
                        mArrayList.get(i).state = 0;
                        notifyItemChanged(i);
                        break;
                }

            }
        };
        samplerViewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mArrayList.get(i).samplerPlayer.setRepeat(step[(int) id]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        samplerViewHolder.mainButton.setOnClickListener(onClick);
        samplerViewHolder.closeTrack.setOnClickListener(onClick);
        samplerViewHolder.retryRecord.setOnClickListener(onClick);
        samplerViewHolder.mainButton.setImageResource(icon[mArrayList.get(i).state]);
    }

    //Количество элементов
    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    //Шаг №1
    //Связываем компоненты полученного View из XML с реальными компонентами
    class SamplerViewHolder extends RecyclerView.ViewHolder {
        ImageView mainButton;
        Spinner spinner;
        ImageView closeTrack;
        ImageView retryRecord;


        public SamplerViewHolder(@NonNull View itemView) {
            super(itemView);
            mainButton = itemView.findViewById(R.id.iv_sh_main);
            spinner = itemView.findViewById(R.id.s_sh_bar);
            closeTrack = itemView.findViewById(R.id.iv_sh_close);
            retryRecord = itemView.findViewById(R.id.iv_sh_newtrack);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, scroll);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }
    }

    void add() {
        mArrayList.add(new SamplerItem(mArrayList.size()));
    }
}
