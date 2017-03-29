package com.ptja.android.mms.bean;

import java.util.List;

/**
 * Created by zhenghou on 2016/7/5.
 */
public class EquipStatusBean {


    /**
     * code : 0
     * response : [{"year":2013,"month":2,"day":1,"using":638,"idle":3571},{"year":2013,"month":2,"day":2,"using":519,"idle":2957},{"year":2013,"month":2,"day":3,"using":911,"idle":4515},{"year":2013,"month":2,"day":4,"using":739,"idle":2784},{"year":2013,"month":2,"day":5,"using":992,"idle":2885},{"year":2013,"month":2,"day":6,"using":626,"idle":4950},{"year":2013,"month":2,"day":7,"using":834,"idle":2678},{"year":2013,"month":2,"day":8,"using":754,"idle":3553},{"year":2013,"month":2,"day":9,"using":119,"idle":1315},{"year":2013,"month":2,"day":10,"using":332,"idle":2741},{"year":2013,"month":2,"day":11,"using":524,"idle":3479},{"year":2013,"month":2,"day":12,"using":718,"idle":2923},{"year":2013,"month":2,"day":13,"using":597,"idle":1677},{"year":2013,"month":2,"day":14,"using":989,"idle":2434},{"year":2013,"month":2,"day":15,"using":286,"idle":1283},{"year":2013,"month":2,"day":16,"using":133,"idle":3517},{"year":2013,"month":2,"day":17,"using":150,"idle":3569},{"year":2013,"month":2,"day":18,"using":141,"idle":4793},{"year":2013,"month":2,"day":19,"using":596,"idle":2011},{"year":2013,"month":2,"day":20,"using":233,"idle":585},{"year":2013,"month":2,"day":21,"using":591,"idle":4369},{"year":2013,"month":2,"day":22,"using":829,"idle":756},{"year":2013,"month":2,"day":23,"using":282,"idle":800},{"year":2013,"month":2,"day":24,"using":283,"idle":1433},{"year":2013,"month":2,"day":25,"using":663,"idle":3054},{"year":2013,"month":2,"day":26,"using":292,"idle":1273},{"year":2013,"month":2,"day":27,"using":233,"idle":2970},{"year":2013,"month":2,"day":28,"using":300,"idle":2655}]
     * msg : 成功
     */

    private int code;
    private String msg;
    /**
     * year : 2013
     * month : 2
     * day : 1
     * using : 638
     * idle : 3571
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
        private int year;
        private int month;
        private int day;
        private int using;
        private int idle;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getUsing() {
            return using;
        }

        public void setUsing(int using) {
            this.using = using;
        }

        public int getIdle() {
            return idle;
        }

        public void setIdle(int idle) {
            this.idle = idle;
        }
    }
}
