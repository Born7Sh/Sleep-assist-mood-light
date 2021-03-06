package com.example.sleepmood;

import android.app.Activity;
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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Record_Day extends Fragment {

    LineChart chart;

    List<String> xAxisValues = new ArrayList<>(Arrays.asList("24", "01", "02", "03", "04", "05", "06", "07"));

    private TextView sleepDate;
    private TextView sleepElement;
    private TextView sleepScore;
    private TextView sleepTime;
    private TextView sleepRecommend;


    private ReportData dayData;
    private List<ReportData> rd;

    private SharedPreferences pref;
    private String checkFirst;

    private SharedPreferences pref_id;
    private String user_id;

    ArrayList<ReportData> items = new ArrayList<>();

    org.eazegraph.lib.charts.PieChart mPieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__record__day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sleepDate = view.findViewById(R.id.sleepDate);
        sleepElement = view.findViewById(R.id.sleepElement);
        sleepScore = view.findViewById(R.id.sleepScore);
        sleepTime = view.findViewById(R.id.sleepTime);
        sleepRecommend = view.findViewById(R.id.sleepRecommend);

        mPieChart = (org.eazegraph.lib.charts.PieChart) view.findViewById(R.id.chart_pie);

        chart = view.findViewById(R.id.chart);
        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        pref_id = getActivity().getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id", "NULL");
        // background color

        getReportDataDay();

    }

    public void getReportDataDay() {

        SharedPreferences sp = getActivity().getSharedPreferences("reportAll", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().create();
        Collection<?> values = sp.getAll().values();

        for (Object value : values) {
            String json = (String) value;
            items.add(gson.fromJson(json, ReportData.class));
        }
        if(items.size()<=0){
            sleepTime.setText("??????");
            sleepDate.setText("???????????? ???????????? ????????????.");
            sleepElement.setText("?????????????????? ???????????????");
            sleepScore.setText("??????");

        }else{
            setAppText();
        }
    }

    public void setAppText() {
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // ?????? ?????? ???
        sleepDate.setText(dateFormat.format(curDate));
        sleepElement.setText(items.get(0).getElements());
        sleepScore.setText(Integer.toString(items.get(0).getScore()) + "???");
        sleepTime.setText(Integer.toString((int) items.get(0).getSleeping_time()) + "??????");
        setOurChart();
    }


    private void setPieGrp() {
        // ????????????

        mPieChart.clearChart();
        // ????????? value??? ??????
        // ?????? value?????? ?????? - 100 ?????? ????????????
//        mPieChart.addPieSlice(new PieModel("?????? ??????",
//                items.get(0).getScore(), Color.parseColor("#4376FE")));
//        mPieChart.addPieSlice(new PieModel("",
//                (100 - items.get(0).getScore()), Color.parseColor("#7FE7FE")));

        mPieChart.addPieSlice(new PieModel("?????? ??????",
                (items.get(0).getScore()), Color.parseColor("#4376FE")));
        mPieChart.addPieSlice(new PieModel("",
                (items.get(0).getScore()), Color.parseColor("#7FE7FE")));


        mPieChart.startAnimation();

    }

    public void setOurChart() {
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

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            chart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));

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


        setData(8, 100);
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.alarm_else)));
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
            set1 = new LineDataSet(values, "?????? ??????");

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

}