package com.ptja.android.mms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenghou on 2016/6/14.
 */
public class EquipApplyBean implements Serializable {

    private String title;

    private String equipName;

    private String number;

    private String date;

    private int status;//1-已审批，2-已驳回，3-已领取 4-申请中

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
