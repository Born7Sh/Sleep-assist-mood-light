package com.example.sleepmood;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

//MPAndroidChart import
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;


public class Fragment_Record_Entire extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__record__entire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineChart lineChart;

        ArrayList<Entry> entry1 = new ArrayList<>();
        ArrayList<Entry> entry2 = new ArrayList<>();
        ArrayList<Entry> entry3 = new ArrayList<>();


        lineChart = (LineChart) view.findViewById(R.id.chart);//layout의 id
        LineData chartData = new LineData();

        entry1.add(new Entry(1, 2));
        entry1.add(new Entry(2, 3));
        entry1.add(new Entry(3, 4));

        entry2.add(new Entry(3, 5));
        entry2.add(new Entry(4, 6));
        entry2.add(new Entry(6, 7));

        entry3.add(new Entry(4, 5));
        entry3.add(new Entry(5, 6));
        entry3.add(new Entry(7, 8));

        /* 만약 (2, 3) add하고 (2, 5)한다고해서
         기존 (2, 3)이 사라지는게 아니라 x가 2인곳에 y가 3, 5의 점이 찍힘 */

        LineDataSet set1 = new LineDataSet(entry1, "얕은 수면");
        chartData.addDataSet(set1);

        LineDataSet set2 = new LineDataSet(entry2, "램 수면");
        chartData.addDataSet(set2);

        LineDataSet set3 = new LineDataSet(entry3, "깊은 수면");
        chartData.addDataSet(set3);
        lineChart.setData(chartData);
        lineChart.invalidate();



        // 파이차트
        org.eazegraph.lib.charts.PieChart mPieChart = (org.eazegraph.lib.charts.PieChart) view.findViewById(R.id.chart_pie);
        mPieChart.clearChart();
        // 점수를 value에 넣고
        // 아래 value에는 점수 - 100 하면 될거같음
        mPieChart.addPieSlice(new PieModel("수면 효율", 60, Color.parseColor("#4376FE")));
        mPieChart.addPieSlice(new PieModel("", 40, Color.parseColor("#7FE7FE")));

        mPieChart.startAnimation();




    }
}