package com.example.sleepmood;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;

import static android.content.Context.MODE_PRIVATE;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ItemViewHolder>{
    private ArrayList<AlarmData> listAlarm = new ArrayList<>();
    private ArrayList<Integer> listweek = new ArrayList<>();
    Context context;
    MainActivity activity;

    SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();
    private String json;
    int alarmNum;

    public AlarmAdapter(Context context, ArrayList<AlarmData> listAlarm, MainActivity activity) {
        this.context = context;
        this.listAlarm = listAlarm;
        this.activity = activity;

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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setItemNumber(position);
                activity.onFragmentChange("alarmAdd");
            }
        });


        holder.on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked != true){
                    listAlarm.get(position).setOnOf(false);

                    sharedPreferences = context.getSharedPreferences( "AlarmData", MODE_PRIVATE );
                    Gson gson2 = new Gson();
                    json = gson2.toJson(listAlarm.get(position), AlarmData.class);
                    int a = position;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(String.valueOf(position+1), json);
                    editor.apply();


                }else{
                    listAlarm.get(position).setOnOf(true);
                    sharedPreferences = context.getSharedPreferences( "AlarmData", MODE_PRIVATE );
                    Gson gson = new Gson();
                    json = gson.toJson(listAlarm.get(position), AlarmData.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(String.valueOf(position+1), json);
                    editor.apply();
                }
            }
        });

    }
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
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

        public Switch on_off;

        public CardView cardView;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.item_time);
            textView2 = (TextView) itemView.findViewById(R.id.item_ampm);
            textView3 = (TextView) itemView.findViewById(R.id.item_routine);
            on_off = (Switch) itemView.findViewById(R.id.alarm_switch);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

        void onBind(AlarmData data) {
            textView1.setText(data.getAlarmTime());
            textView2.setText(data.getAlarmDate());

            if(data.isOnOf() == true) {
                on_off.setChecked(true);
            }else {
                on_off.setChecked(false);
            }
        }
    }
}

