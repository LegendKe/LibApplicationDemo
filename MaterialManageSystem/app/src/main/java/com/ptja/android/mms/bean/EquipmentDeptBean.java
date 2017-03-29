package com.ptja.android.mms.bean;

/**
 * Created by zhenghou on 2016/6/12.
 */
public class EquipmentDeptBean {

    /**
     * dept_id : 1
     * dept_name : 行政部
     * parent_id : 0
     */

    private String dept_id;
    private String dept_name;
    private String parent_id;

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
