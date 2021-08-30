package com.example.sleepmood;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static androidx.viewpager.widget.PagerAdapter.POSITION_NONE;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ItemViewHolder>{
    private ArrayList<AlarmData> listAlarm = new ArrayList<>();
    private ArrayList<Integer> listweek = new ArrayList<>();
    Context context;

    MainActivity activity;

    SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();
    private String json;
    int alarmNum;

    Fragment_Home_AlarmList fragment_home_alarmList;



    AlarmManager alarm_manager;
    PendingIntent pendingIntent;

    public AlarmAdapter(Context context, ArrayList<AlarmData> listAlarm, MainActivity activity, Fragment_Home_AlarmList fragmentHomeAlarmList) {
        this.context = context;
        this.listAlarm = listAlarm;
        this.activity = activity;
        this.fragment_home_alarmList = fragment_home_alarmList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_alarm, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (@NonNull final ItemViewHolder holder, final int position) {
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

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent my_intent =
                        new Intent(context, Alarm_Receiver.class);
                alarm_manager = (AlarmManager)
                        activity.getSystemService(ALARM_SERVICE);

                sharedPreferences = context.
                        getSharedPreferences( "AlarmData", MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(String.valueOf(position+1));
                editor.commit();

                pendingIntent = PendingIntent.getBroadcast(context,
                        position, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_manager.cancel(pendingIntent);

                activity.onFragmentChange("refresh");

            }
        });

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

        public ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.item_time);
            textView2 = (TextView) itemView.findViewById(R.id.item_ampm);
            textView3 = (TextView) itemView.findViewById(R.id.item_routine);
            on_off = (Switch) itemView.findViewById(R.id.alarm_switch);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (ImageView) itemView.findViewById(R.id.alarmCancel);
        }

        void onBind(AlarmData data) {
            textView1.setText(data.getAlarmTime());
            textView2.setText(data.getAlarmDate());

            List<Integer> week = new ArrayList<>();
            week = data.getAlarmRepeatWeek();
            Collections.sort(week);

            List<String> days = new ArrayList<>();
            for (int i : week)
                switch (i) {
                    case 0:
                        days.add("일");
                        break;
                    case 1:
                        days.add("월");
                        break;
                    case 2:
                        days.add("화");
                        break;
                    case 3:
                        days.add("수");
                        break;
                    case 4:
                        days.add("목");
                        break;
                    case 5:
                        days.add("금");
                        break;
                    case 6:
                        days.add("토");
                        break;
                    default:
                        break;
                }
            String day_text = TextUtils.join(" ", days);
            textView3.setText(day_text);

            if(data.isOnOf() == true) {
                on_off.setChecked(true);
            }else {
                on_off.setChecked(false);
            }
        }
    }


}

