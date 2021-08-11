package com.example.sleepmood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ItemViewHolder>{
    private ArrayList<DiaryData> listDiary = new ArrayList<>();
    Context context;


    public DiaryAdapter(Context context, ArrayList<DiaryData> listDiary) {
        this.context = context;
        this.listDiary = listDiary;
    }

    @NonNull
    @Override
    public DiaryAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_diary, parent, false);
        return new DiaryAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiaryAdapter.ItemViewHolder holder, final int position) {
        // EventAdaper와 다른것은 holder final로 바꿨다.
        //listData : 현재 recyclerView로 가지고 있는 데이터 그릇을 의미
        //position : 현재 recyclerView의 position
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listDiary.get(position));

//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listDiary.size();
    }


    void addItem(DiaryData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listDiary.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.diary_date);
            textView2 = (TextView) itemView.findViewById(R.id.diary_content);
        }

        void onBind(DiaryData data) {
            textView1.setText(data.getDiary_date());
            textView2.setText(data.getDiary_content());

        }
    }
}


