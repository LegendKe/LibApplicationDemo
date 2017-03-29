package com.ptja.android.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.EquipApproveHistoryActivity;
import com.ptja.android.mms.activity.login.LoginActivity;
import com.ptja.android.mms.activity.task.HistoryTaskListActivity;
import com.ptja.android.mms.adapter.FragmentAdapter;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.fragment.FragmentEquipApply;
import com.ptja.android.mms.widget.MyRadioButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.View.NoScrollViewPager;
import hz.framework.android.app.AppManager;
import hz.framework.android.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {


    @Bind(R.id.views)
    NoScrollViewPager views;
    @Bind(R.id.tab_task)
    MyRadioButton tabTask;
    @Bind(R.id.tab_equip_apply_approve)
    MyRadioButton tabEquipApplyApprove;
    @Bind(R.id.tab_scan)
    MyRadioButton tabScan;
    @Bind(R.id.tab_apply_track)
    MyRadioButton tabApplyTrack;
    @Bind(R.id.tab_equip_apply)
    MyRadioButton tabEquipApply;
    @Bind(R.id.tab_search)
    MyRadioButton tabSearch;
    @Bind(R.id.tab_equip)
    MyRadioButton tabEquip;
    @Bind(R.id.tab_statistic_task)
    MyRadioButton tabStatistic_task;
    @Bind(R.id.tab_statistic_equip)
    MyRadioButton tabStatistic_equip;
    @Bind(R.id.tab_statistic_truck)
    MyRadioButton tabStatistic_truck;
    @Bind(R.id.tab_location)
    MyRadioButton tabLocation;
    @Bind(R.id.tab_truck)
    MyRadioButton tabTruck;
    @Bind(R.id.tab_statistic)
    MyRadioButton tabStatistic;
    @Bind(R.id.tab_contingent)
    MyRadioButton tabContingent;
    @Bind(R.id.tab_equip_require)
    MyRadioButton tabEquipRequire;
    @Bind(R.id.maskView)
    View maskView;
    @Bind(R.id.main)
    FrameLayout main;

    public static final int TAB_TASK = 0;
    public static final int TAB_EQUIP_APPLY_APPROVE = 1;
    public static final int TAB_SCAN = 2;
    public static final int TAB_EQUIP_APPLY = 3;
    public static final int TAB_APPLY_TRACK = 4;
    public static final int TAB_SEARCH = 5;
    public static final int TAB_EQUIP = 6;
    public static final int TAB_TRUCK = 7;
    public static final int TAB_STATISIC = 8;
    public static final int TAB_STATISIC_TASK = 9;
    public static final int TAB_STATISIC_EQUIP = 10;
    public static final int TAB_STATISIC_TRUCK = 11;
    public static final int TAB_LOCATION = 12;
    public static final int TAB_CONTINGENT = 13;
    public static final int TAB_EQUIP_REQUIRE = 14;

    private int[] tabs;
    private MyRadioButton tab_btns[] ;
    private String tab_title[] = new String[]{"任务","审核审批","扫描","装备申领","记录跟踪","搜索","装备","车辆","统计","任务统计","装备统计","车辆统计","定位","队伍","申购"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tab_btns = new MyRadioButton[]{tabTask,tabEquipApplyApprove,tabScan,tabEquipApply,tabApplyTrack,tabSearch,tabEquip,tabTruck,tabStatistic,tabStatistic_task,tabStatistic_equip,tabStatistic_truck,tabLocation, tabContingent,tabEquipRequire};
        views.setNoScroll(true);
        initTabs();
        tab_btns[tabs[0]].setChecked(true);
        if (tabs[0]==0){
            if (GlobeVariable.UserInfos.getUser_type_id().equals(GlobeVariable.USER_TYPE_CAPTAIN+"")){
                setHeader(R.drawable.setup, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),SetupActivity.class));
                    }
                },tab_title[tabs[0]],null,0,0,null);

            }else{
                setHeader(R.drawable.setup, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                    }
                }, tab_title[tabs[0]], null, 0, R.drawable.finished, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), HistoryTaskListActivity.class));
                    }
                });
            }



        }else if(tabs[0]==TAB_EQUIP_APPLY){
            setHeader(R.drawable.setup, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                }
            }, tab_title[tabs[0]], null, 0, R.drawable.submit, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentEquipApply.instance.submit();
                }
            });

        }else if(tabs[0]==TAB_EQUIP_APPLY_APPROVE){
            setHeader(R.drawable.setup, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                }
            }, tab_title[tabs[0]], null, 0, R.drawable.finished, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), EquipApproveHistoryActivity.class));
                }
            });
        }else{
            setHeader(R.drawable.setup,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                }
            },tab_title[tabs[0]],null,0,0,null);
        }


        FragmentAdapter adapter = new FragmentAdapter(
                getSupportFragmentManager(),tabs);
        views.setAdapter(adapter);
        views.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
//                tab_btns[id].setChecked(true);
                if (tabs[id]==TAB_TASK) {
                    if (GlobeVariable.UserInfos.getUser_type_id().equals(GlobeVariable.USER_TYPE_CAPTAIN+"")){
                        setHeader(R.drawable.setup, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(),SetupActivity.class));
                            }
                        },tab_title[tabs[id]],null,0,0,null);

                    }else{
                        setHeader(R.drawable.setup, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                            }
                        }, tab_title[tabs[id]], null, 0, R.drawable.finished, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(), HistoryTaskListActivity.class));
                            }
                        });

                    }
                }else if(tabs[id]==TAB_EQUIP_APPLY_APPROVE){
                    setHeader(R.drawable.setup, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                        }
                    }, tab_title[tabs[id]], null, 0, R.drawable.finished, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), EquipApproveHistoryActivity.class));
                        }
                    });
                }else if(tabs[id]==TAB_EQUIP_APPLY){
                    setHeader(R.drawable.setup, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                        }
                    }, tab_title[tabs[id]], null, 0, R.drawable.submit, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentEquipApply.instance.submit();
                        }
                    });

                }else{
                    setHeader(R.drawable.setup,new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), SetupActivity.class));
                        }
                    },tab_title[tabs[id]],null,0,0,null);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    protected void onResume() {
        if (GlobeVariable.UserInfos == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        super.onResume();
    }

    private void initTabs() {
        switch (Integer.parseInt(GlobeVariable.UserInfos.getUser_type_id())){
            case GlobeVariable.USER_TYPE_BOSS:
                tabs = new int[]{TAB_EQUIP_APPLY_APPROVE,TAB_STATISIC_TASK,TAB_STATISIC_EQUIP,TAB_STATISIC_TRUCK,TAB_LOCATION};
                break;
            case GlobeVariable.USER_TYPE_CAPTAIN:
                tabs = new int[]{TAB_TASK,TAB_EQUIP,TAB_TRUCK,TAB_STATISIC,TAB_CONTINGENT};
                break;
            case GlobeVariable.USER_TYPE_STORE_ADMIN:
                tabs = new int[]{TAB_TASK,TAB_SCAN,TAB_SEARCH,TAB_EQUIP_REQUIRE};
                break;
            case GlobeVariable.USER_TYPE_TRUCK_ADMIN:
                tabs = new int[]{TAB_TASK,TAB_SCAN,TAB_SEARCH};
                break;
            case GlobeVariable.USER_TYPE_SOLDIER:
                tabs = new int[]{TAB_EQUIP_APPLY,TAB_APPLY_TRACK};
                break;
        }
        for (int i = 0;i<tab_btns.length;i++){
            tab_btns[i].setVisibility(View.GONE);
        }

        for (int i = 0;i<tabs.length;i++){
            tab_btns[tabs[i]].setVisibility(View.VISIBLE);
            tab_btns[tabs[i]].setOnCheckedChangeListener(new OnCheckChangeListener(tab_btns[tabs[i]].getId()));
        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onCancel() {

    }
    private long mExitTime;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                AppManager.getAppManager().AppExit(getApplicationContext());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }
    class OnCheckChangeListener implements CompoundButton.OnCheckedChangeListener{

       private int viewId;

       public OnCheckChangeListener(int viewId) {
           this.viewId = viewId;
       }


       @Override
       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
           if (isChecked && buttonView.isChecked()){
               for (int i = 0;i<tab_btns.length;i++){
                   if (this.viewId == tab_btns[i].getId()){
                       for (int j = 0;j<tabs.length;j++){
                           if(tabs[j] == i){
                               views.setCurrentItem(j);
                               return;
                           }
                       }
                   }
               }
           }
       }
   }

}
