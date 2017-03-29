package com.ptja.android.mms.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.fragment.FragmentApplyTrack;
import com.ptja.android.mms.fragment.FragmentCapture;
import com.ptja.android.mms.activity.MainActivity;
import com.ptja.android.mms.fragment.FragmentContingent;
import com.ptja.android.mms.fragment.FragmentEquipApply;
import com.ptja.android.mms.fragment.FragmentEquipApplyApprove;
import com.ptja.android.mms.fragment.FragmentEquipment;
import com.ptja.android.mms.fragment.FragmentEquipment_Captain;
import com.ptja.android.mms.fragment.FragmentLocation;
import com.ptja.android.mms.fragment.FragmentSearch;
import com.ptja.android.mms.fragment.FragmentSetup;
import com.ptja.android.mms.fragment.FragmentStatisic;
import com.ptja.android.mms.fragment.FragmentStatisicEquip;
import com.ptja.android.mms.fragment.FragmentStatisicTask;
import com.ptja.android.mms.fragment.FragmentStatisicTruck;
import com.ptja.android.mms.fragment.FragmentTask;
import com.ptja.android.mms.fragment.FragmentTask_Captain;
import com.ptja.android.mms.fragment.FragmentTruck;

public class FragmentAdapter extends FragmentPagerAdapter {
    public int[] tabs;

    public FragmentAdapter(FragmentManager fm, int[] tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int id) {
        switch (tabs[id]) {
            case MainActivity.TAB_TASK:
                if (Integer.parseInt(GlobeVariable.UserInfos.getUser_type_id())==GlobeVariable.USER_TYPE_CAPTAIN){
                    FragmentTask_Captain taskFragment = new FragmentTask_Captain();
                    return taskFragment;
                }else{
                    FragmentTask taskFragment = new FragmentTask();
                    return taskFragment;
                }
            case MainActivity.TAB_EQUIP_APPLY_APPROVE:
                FragmentEquipApplyApprove fragmentEquipApplyApprove = new FragmentEquipApplyApprove();
                return fragmentEquipApplyApprove;
            case MainActivity.TAB_SCAN:
                FragmentCapture categoryFragment = new FragmentCapture();
                return categoryFragment;
            case MainActivity.TAB_APPLY_TRACK:
                FragmentApplyTrack applyTrackFragment = new FragmentApplyTrack();
                return applyTrackFragment;
            case MainActivity.TAB_EQUIP_APPLY:
                FragmentEquipApply equipApplyFragment = new FragmentEquipApply();
                return equipApplyFragment;
            case MainActivity.TAB_EQUIP:
                if (Integer.parseInt(GlobeVariable.UserInfos.getUser_type_id())==GlobeVariable.USER_TYPE_CAPTAIN){
                    FragmentEquipment_Captain equipmentFragment = new FragmentEquipment_Captain();
                    return equipmentFragment;
                }else{
                    FragmentEquipment equipmentFragment = new FragmentEquipment();
                    return equipmentFragment;
                }
            case MainActivity.TAB_EQUIP_REQUIRE:
                FragmentEquipApply equipApplyFragments = new FragmentEquipApply();
                return equipApplyFragments;
            case MainActivity.TAB_SEARCH:
                FragmentSearch searchFragment = new FragmentSearch();
                return searchFragment;
            case MainActivity.TAB_STATISIC:
                FragmentStatisic statisic = new FragmentStatisic();
                return statisic;
            case MainActivity.TAB_TRUCK:
                FragmentTruck truckFragment = new FragmentTruck();
                return truckFragment;
            case MainActivity.TAB_STATISIC_TASK:
                FragmentStatisicTask taskFragment = new FragmentStatisicTask();
                return taskFragment;
            case MainActivity.TAB_STATISIC_EQUIP:
                FragmentStatisicEquip equipFragment = new FragmentStatisicEquip();
                return equipFragment;
            case MainActivity.TAB_STATISIC_TRUCK:
                FragmentStatisicTruck truckStatisticFragment = new FragmentStatisicTruck();
                return truckStatisticFragment;
            case MainActivity.TAB_LOCATION:
                FragmentLocation buyFragment = new FragmentLocation();
                return buyFragment;
            case MainActivity.TAB_CONTINGENT:
                FragmentContingent contingentFragment = new FragmentContingent();
                return contingentFragment;
//            case MainActivity.TAB_SETUP:
//                FragmentSetup moreFragment = new FragmentSetup();
//                return moreFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
