package com.ptja.android.mms.activity.statisic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ptja.android.mms.R;
import com.ptja.android.mms.adapter.BottomMenuAdapter;
import com.ptja.android.mms.bean.EquipTypeBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hz.framework.android.View.HorizontalListView;
import hz.framework.android.base.BaseActivity;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class EquipTypeStatisicActivity extends BaseActivity {
    private BarChart mChart;
    HorizontalListView bottomView;
    BottomMenuAdapter adapter;
    private Typeface mTf;
    private List<EquipTypeBean.ResponseBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_type_statisic);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "装备类型统计", null, 0, 0,null);

        getData();
        initChart();

    }
    private void getData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("year","2016");
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "获取数据", null, UrlConstants.URL_QUERY_EQUIP_TYPE_STATISIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    EquipTypeBean bean = com.alibaba.fastjson.JSONObject.parseObject(s,EquipTypeBean.class);
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
        mChart.setBackgroundColor(0xfff2f2f2);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

    }

    private void initData(int position){
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.get(position).getStatistics().size(); i++) {
            xVals.add(data.get(position).getStatistics().get(i).getEquipment_type_name());
            yVals1.add(new BarEntry(data.get(position).getStatistics().get(i).getCount(),i));
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setYVals(yVals1);
            mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.animateXY(1000, 1000);
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet");
            set1.setBarSpacePercent(35f);
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(xVals, dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTf);
            mChart.animateXY(1000, 1000);
            mChart.setData(data);
        }
        mChart.postInvalidate();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                Toast.makeText(EquipTypeStatisicActivity.this, (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                data= (List<EquipTypeBean.ResponseBean>) msg.obj;
                if (data.size()>0){
                    bottomView = (HorizontalListView) findViewById(R.id.bottomMenu);
                    adapter = new BottomMenuAdapter(getApplicationContext(),data);
                    bottomView.setAdapter(adapter);

                    initData(0);
                    bottomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            initData(position);
                            adapter.setSelected(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }


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
