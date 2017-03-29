package com.ptja.android.mms.bean;

import java.util.List;

/**
 * Created by zhenghou on 2016/7/5.
 */
public class EquipTotalBean {

    /**
     * code : 0
     * response : [{"year":"2013","month":1,"count":2097},{"year":"2013","month":2,"count":3327},{"year":"2013","month":3,"count":2613},{"year":"2013","month":4,"count":7698},{"year":"2013","month":5,"count":2465},{"year":"2013","month":6,"count":5802},{"year":"2013","month":7,"count":4501},{"year":"2013","month":8,"count":3039},{"year":"2013","month":9,"count":5779},{"year":"2013","month":10,"count":1253},{"year":"2013","month":11,"count":2529},{"year":"2013","month":12,"count":4622}]
     * msg : 成功
     */

    private int code;
    private String msg;
    /**
     * year : 2013
     * month : 1
     * count : 2097
     */

    private List<ResponseBean> response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        private String year;
        private int month;
        private int count;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
