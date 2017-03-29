package com.ptja.android.mms.activity.statisic;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipStatusBean;
import com.ptja.android.mms.bean.EquipTotalBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hz.framework.android.base.BaseActivity;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class EquipStatusStatisicActivity extends BaseActivity {
    private BarChart mChart;

    private Typeface tf;
    private List<EquipStatusBean.ResponseBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_status_statisic);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "装备状态统计", null, 0, 0,null);

        getData();

    }
    private void getData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("year","2016");
        params.put("month","5");
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "获取数据", null, UrlConstants.URL_QUERY_EQUIP_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    EquipStatusBean bean = com.alibaba.fastjson.JSONObject.parseObject(s,EquipStatusBean.class);
                    if (bean.getCode()==0){
                        Message msg = new Message();
                        msg.what = 2;
                        msg.obj = bean.getResponse();
                        mHandler.sendMessage(msg);
                    }else{
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = bean.getMsg();
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    private void initChart(){
        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setBackgroundColor(0xffffffff);
        mChart.setDescription("");

//        mChart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        l.setTypeface(tf);
        l.setYOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(tf);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(30f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);
        initData();
    }

    private void initData(){
        ArrayList<String> xVals = new ArrayList<String>();

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.size(); i++) {
            xVals.add(data.get(i).getDay()+"日");
            yVals1.add(new BarEntry(data.get(i).getUsing(),i));
            yVals2.add(new BarEntry(data.get(i).getIdle(),i));
        }



        BarDataSet set1, set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet)mChart.getData().getDataSetByIndex(1);
            set1.setYVals(yVals1);
            set2.setYVals(yVals2);
            mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create 3 datasets with different types
            set1 = new BarDataSet(yVals1, "已使用");
            // set1.setColors(ColorTemplate.createColors(getApplicationContext(),
            // ColorTemplate.FRESH_COLORS));
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "未使用");
            set2.setColor(Color.rgb(164, 228, 251));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);

            BarData data = new BarData(xVals, dataSets);
//        data.setValueFormatter(new LargeValueFormatter());

            // add space between the dataset groups in percent of bar-width
            data.setGroupSpace(80f);
            data.setValueTypeface(tf);

            mChart.setData(data);
        }

        mChart.invalidate();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                Toast.makeText(EquipStatusStatisicActivity.this, (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                data= (List<EquipStatusBean.ResponseBean>) msg.obj;
                initChart();
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onClick(View v) {

    }
}
