package com.example.sleepmood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ItemViewHolder> {
    private ArrayList<CalendarData> listCalendar = new ArrayList<>();
    Context context;


    public CalendarAdapter(Context context, ArrayList<CalendarData> listCalendar) {
        this.context = context;
        this.listCalendar = listCalendar;
    }

    @NonNull
    @Override
    public CalendarAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_calendar, parent, false);
        return new CalendarAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CalendarAdapter.ItemViewHolder holder, final int position) {
        // EventAdaper와 다른것은 holder final로 바꿨다.
        //listData : 현재 recyclerView로 가지고 있는 데이터 그릇을 의미
        //position : 현재 recyclerView의 position
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listCalendar.get(position));

//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listCalendar.size();
    }


    void addItem(CalendarData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listCalendar.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.cardview_start);
            textView2 = (TextView) itemView.findViewById(R.id.cardview_end);
            textView3 = (TextView) itemView.findViewById(R.id.cardview_title);
            textView4 = (TextView) itemView.findViewById(R.id.cardview_descrption);
        }

        void onBind(CalendarData data) {
            textView1.setText(data.start);
            textView2.setText(data.end);
            textView3.setText(data.title);
            textView4.setText(data.description);

        }
    }
}

