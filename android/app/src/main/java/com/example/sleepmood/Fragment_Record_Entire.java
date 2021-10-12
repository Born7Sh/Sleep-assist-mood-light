package com.example.sleepmood;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

//MPAndroidChart import
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Record_Entire extends Fragment {

    private int StartTime;
    private int EndTime;
    private int TotalTime;
    private int DB;

    private TextView entire_SleepTime;
    private TextView entire_Element;
    private TextView entire_Date;
    private TextView sleepScore;
    private TextView sleepCount;

    private ReportData dayData;
    private List<ReportData> rd;

    private SharedPreferences pref;
    private String checkFirst;

    private SharedPreferences pref_id;
    private String user_id;

    private int averageData;
    private int averageSleepTime;

    LineChart chart;
    org.eazegraph.lib.charts.PieChart mPieChart;

    List<String> xAxisValues = new ArrayList<>(Arrays.asList("일", "일", "일", "일", "일", "일", "일"));
    // 시간 설정

    ArrayList<ReportData> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__record__entire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chart = view.findViewById(R.id.chart);
        // background color
        mPieChart = (org.eazegraph.lib.charts.PieChart) view.findViewById(R.id.chart_pie);

        entire_SleepTime = view.findViewById(R.id.entire_SleepTime);
        entire_Element = view.findViewById(R.id.entire_Element);
        entire_Date = view.findViewById(R.id.entire_Date);
        sleepScore = view.findViewById(R.id.sleepScore);
        sleepCount = view.findViewById(R.id.entire_SleepCount);
        getReportAll();

    }

    private void getReportAll() {

        SharedPreferences sp = getActivity().getSharedPreferences("reportAll", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().create();
        Collection<?> values = sp.getAll().values();

        for (Object value : values) {
            String json = (String) value;
            items.add(gson.fromJson(json, ReportData.class));
        }

        Log.v("알림", "데이터 사이즈" + items.size());
        if(items.size()<=0){
            entire_SleepTime.setText("없음");
            entire_Date.setText("데이터가 존재하지 않습니다.");
            entire_Element.setText("수면데이터를 쌓아주세요");
            sleepScore.setText("없음");
            sleepCount.setText("없음");

        }else{
            setAppText();
        }


        // 데이터가 7개 이하면 다 보여주기
        // 데이터가 7개 - 30 사이면 / 4개마다 대푯값 넣어서 보여주기
        // 데이터 30개 이상이면
    }

    public void setAppText() {
        entire_SleepTime.setText(Integer.toString((int) items.get(0).getSleeping_time()));
        sleepScore.setText(Integer.toString(items.get(0).getScore()));
        sleepCount.setText(Integer.toString(items.size()) + "개");
        setGrp();

    }


    private void setPieGrp() {
        // 파이차트

        mPieChart.clearChart();
        // 점수를 value에 넣고
        // 아래 value에는 점수 - 100 하면 될거같음
        mPieChart.addPieSlice(new PieModel("수면 점수",
                averageData, Color.parseColor("#4376FE")));
        mPieChart.addPieSlice(new PieModel("",
                averageData, Color.parseColor("#7FE7FE")));

        mPieChart.startAnimation();
    }

    private void setGrp() {
        chart.setBackgroundColor(Color.WHITE);
        // disable description text
        chart.getDescription().setEnabled(false);
        // enable touch gestures
        chart.setTouchEnabled(true);
        // set listeners
        chart.setDrawGridBackground(false);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);
        // force pinch zoom along both axis
        chart.setPinchZoom(true);

        if (items.size() <= 7) { // 일별로

            for (int i = 0; i < items.size(); i++) {
                String a = Integer.toString(i + 1);
                xAxisValues.set(i, a + "일");
            }

        } else if (7 < items.size() || items.size() <= 30) { // 주차별로

            for (int i = 0; i <= items.size() / 7; i++) {
                String a = Integer.toString(i + 1);
                xAxisValues.set(i, a + "주");
            }

        } else { // 월별로
            for (int i = 0; i <= items.size() / 30; i++) {
                String a = Integer.toString(i + 1);
                xAxisValues.set(i, a + "월");
            }
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            chart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
            xAxis.setGranularity(1f);
            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(100f);
            yAxis.setAxisMinimum(0);
        }
        if (items.size() <= 7) { // 일별로

            setData(items.size(), 100);


        } else if (7 < items.size() || items.size() <= 30) { // 주차별로

            setData(items.size() / 7 + 1, 100);

        } else { // 월별로
            setData(items.size() / 30 + 1, 100);
        }


    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        ArrayList<Integer> WeekValues = new ArrayList<>();
        ArrayList<Integer> MonthValues = new ArrayList<>();

        int average = 0;

        for (int i = 0; i < count; i++) {

            int val = items.get(i).getScore();
            averageData = val + averageData;
            average = average + val;
            averageSleepTime = (int) (items.get(i).getSleeping_time() + averageSleepTime);

            if ((i % 7) == 0) {
                WeekValues.add(average / 7);
                average = 0;

            }
            if (i == count - 1 && (items.size() > 30 || items.size() > 7)) {
                average = average / (average % 7);
                WeekValues.add(average);
            }

        }

        averageSleepTime = averageSleepTime / items.size();
        entire_SleepTime.setText(Integer.toString(averageSleepTime) + "시간");
        averageData = averageData / items.size();
        sleepScore.setText(Integer.toString(averageData));

        if (items.size() <= 7) {
            for (int i = 0; i < count; i++) {
                int val = items.get(i).getScore();
                values.add(new Entry(i, val, getResources().getDrawable(R.drawable.alarm_else)));
            }
        } else if (7 < items.size() || items.size() <= 30) { // 주차별로
            for (int i = 0; i < WeekValues.size(); i++) {
                int val = WeekValues.get(i);
                values.add(new Entry(i, val, getResources().getDrawable(R.drawable.alarm_else)));
            }

        } else { // 월별로
            // 월별도 같으면 진행하겠음
        }


        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "수면 점수");

            set1.setDrawIcons(false);


            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {

                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.alarm_else);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
            setPieGrp();
        }
    }

    void setXAxisLabel(int pivot) {

    }

    void setGraValue() {

    }
}