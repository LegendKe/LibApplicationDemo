package com.ptja.android.mms.activity.statisic;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipDeptBean;
import com.ptja.android.mms.bean.EquipStatusBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hz.framework.android.base.BaseActivity;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class EquipDeptRateStatisicActivity extends BaseActivity {
    private PieChart mPieChart;
    private Typeface tf;
    private List<EquipDeptBean.ResponseBean.StatisticsBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_dept_rate_statisic);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "装备部门占比", null, 0, 0,null);

        getData();

    }
    private void getData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("year","2016");
        params.put("month","5");
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "获取数据", null, UrlConstants.URL_QUERY_EQUIP_DEPT, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    EquipDeptBean bean = com.alibaba.fastjson.JSONObject.parseObject(s,EquipDeptBean.class);
                    if (bean.getCode()==0){
                        Message msg = new Message();
                        msg.what = 2;
                        msg.obj = bean.getResponse().getStatistics();
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
        mPieChart = (PieChart) findViewById(R.id.chart1);
        mPieChart.setUsePercentValues(true);
        mPieChart.setDescription("");
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

//        mPieChart.setCenterText(generateCenterSpannableText());

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);


        initData();

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mPieChart.spin(2000, 0, 360);

        Legend l = mPieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    private void initData(){
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < data.size(); i++) {
            xVals.add(data.get(i).getDept_name());
            yVals1.add(new Entry(data.get(i).getAmount(), i));
        }


        PieDataSet dataSet = new PieDataSet(yVals1, "部门装备数量");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTypeface(tf);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.setDrawHoleEnabled(false);
        // undo all highlights
        mPieChart.highlightValues(null);

        mPieChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                Toast.makeText(EquipDeptRateStatisicActivity.this, (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                data= (List<EquipDeptBean.ResponseBean.StatisticsBean>) msg.obj;
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
