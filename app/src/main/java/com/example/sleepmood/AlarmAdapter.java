package com.example.sleepmood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ItemViewHolder>{
    private ArrayList<AlarmData> listAlarm = new ArrayList<>();
    private ArrayList<Integer> listweek = new ArrayList<>();
    Context context;


    public AlarmAdapter(Context context, ArrayList<AlarmData> listAlarm) {
        this.context = context;
        this.listAlarm = listAlarm;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_alarm, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        // EventAdaper와 다른것은 holder final로 바꿨다.
        //listData : 현재 recyclerView로 가지고 있는 데이터 그릇을 의미
        //position : 현재 recyclerView의 position
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listAlarm.get(position));

//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        String a = listAlarm.get(0).getAlarmDate();
        return listAlarm.size();
    }


    void addItem(AlarmData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listAlarm.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private TextView textView3;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.item_time);
            textView2 = (TextView) itemView.findViewById(R.id.item_ampm);
            textView3 = (TextView) itemView.findViewById(R.id.item_routine);
        }

        void onBind(AlarmData data) {
            textView1.setText(data.getAlarmTime());
            textView2.setText(data.getAlarmDate());

        }
    }
}

