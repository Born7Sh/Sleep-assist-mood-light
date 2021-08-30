package com.example.sleepmood;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

//MPAndroidChart import
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;


public class Fragment_Record_Entire extends Fragment {

    private int StartTime;
    private int EndTime;
    private int TotalTime;
    private int DB;

    LineChart lineChart;

    ArrayList<Entry> entry1 = new ArrayList<>(); // 얕 수면
    ArrayList<Entry> entry2 = new ArrayList<>(); // 렘 수면
    ArrayList<Entry> entry3 = new ArrayList<>(); // 깊 수면

    ArrayList<String> xAxisLabel = new ArrayList<>(); // 시간 설정

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StartTime = 0100;
        EndTime = 0700;

        if (StartTime >= 1200 && EndTime < 1200){ // 그날 저녁에 자서 다음날 아침에 일어난거
            TotalTime =  ((2400 - StartTime) * -1) + EndTime;
            setXAxisLabel(0);
        }else {
            TotalTime = EndTime-StartTime;
            setXAxisLabel(1);
        }

        return inflater.inflate(R.layout.fragment__record__entire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lineChart = (LineChart) view.findViewById(R.id.chart);//layout의 id
        LineData chartData = new LineData();

        entry1.add(new Entry(0100, 2));
        entry1.add(new Entry(0200, 3));
        entry1.add(new Entry(0300, 4));
        entry1.add(new Entry(0500, 2));
        entry1.add(new Entry(0600, 3));
        entry1.add(new Entry(0700, 4));

//        entry2.add(new Entry(3, 5));
//        entry2.add(new Entry(4, 6));
//        entry2.add(new Entry(6, 7));
//
//        entry3.add(new Entry(4, 5));
//        entry3.add(new Entry(5, 6));
//        entry3.add(new Entry(7, 8));

        /* 만약 (2, 3) add하고 (2, 5)한다고해서
         기존 (2, 3)이 사라지는게 아니라 x가 2인곳에 y가 3, 5의 점이 찍힘 */




        LineDataSet set1 = new LineDataSet(entry1, "얕은 수면");
        set1.setLineWidth(2); // 선 굵기
        set1.setCircleRadius(6); // 곡률
        chartData.addDataSet(set1);
        set1.setCircleColor(Color.BLACK);
        set1.setCircleHoleRadius(Color.BLACK);

        LineDataSet set2 = new LineDataSet(entry2, "램 수면");
        chartData.addDataSet(set2);
        set2.setCircleColor(Color.RED);
        set2.setCircleHoleRadius(Color.RED);

        LineDataSet set3 = new LineDataSet(entry3, "깊은 수면");
        chartData.addDataSet(set3);
        set3.setCircleColor(Color.BLUE);
        set3.setCircleHoleRadius(Color.BLUE);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisLabel));

        xAxis.setPosition(XAxis.XAxisPosition.TOP); //x 축 표시에 대한 위치 설정
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.color2)); // X축 텍스트컬러설정
        xAxis.setGridColor(ContextCompat.getColor(getContext(), R.color.color2)); // X축 줄의 컬러 설정

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.black)); //Y축 텍스트 컬러 설정
        yAxisLeft.setGridColor(ContextCompat.getColor(getContext(), R.color.black)); // Y축 줄의 컬러 설정

        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        lineChart.setData(chartData);
        lineChart.invalidate();


        // 파이차트
        org.eazegraph.lib.charts.PieChart mPieChart =
                (org.eazegraph.lib.charts.PieChart)
                        view.findViewById(R.id.chart_pie);
        mPieChart.clearChart();
        // 점수를 value에 넣고
        // 아래 value에는 점수 - 100 하면 될거같음
        mPieChart.addPieSlice(new PieModel("수면 효율",
                60, Color.parseColor("#4376FE")));
        mPieChart.addPieSlice(new PieModel("",
                40, Color.parseColor("#7FE7FE")));

        mPieChart.startAnimation();


    }

    void setXAxisLabel(int pivot) {

    }

    void setGraValue(){

    }
}