package com.ptja.android.mms.bean;

import java.util.List;

/**
 * Created by zhenghou on 2016/7/6.
 */
public class EquipDeptBean {

    /**
     * code : 0
     * response : {"total":9937,"year":2013,"month":2,"statistics":[{"dept_id":"1","dept_name":"行政部","parent_id":"0","amount":1315},{"dept_id":"2","dept_name":"研发部","parent_id":"0","amount":2073},{"dept_id":"3","dept_name":"市场部","parent_id":"0","amount":1577},{"dept_id":"4","dept_name":"财务部","parent_id":"0","amount":1876},{"dept_id":"5","dept_name":"运维部","parent_id":"0","amount":1228},{"dept_id":"6","dept_name":"人力资源部","parent_id":"0","amount":1867}]}
     * msg : 成功
     */

    private int code;
    /**
     * total : 9937
     * year : 2013
     * month : 2
     * statistics : [{"dept_id":"1","dept_name":"行政部","parent_id":"0","amount":1315},{"dept_id":"2","dept_name":"研发部","parent_id":"0","amount":2073},{"dept_id":"3","dept_name":"市场部","parent_id":"0","amount":1577},{"dept_id":"4","dept_name":"财务部","parent_id":"0","amount":1876},{"dept_id":"5","dept_name":"运维部","parent_id":"0","amount":1228},{"dept_id":"6","dept_name":"人力资源部","parent_id":"0","amount":1867}]
     */

    private ResponseBean response;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ResponseBean {
        private int total;
        private int year;
        private int month;
        /**
         * dept_id : 1
         * dept_name : 行政部
         * parent_id : 0
         * amount : 1315
         */

        private List<StatisticsBean> statistics;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

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

        public List<StatisticsBean> getStatistics() {
            return statistics;
        }

        public void setStatistics(List<StatisticsBean> statistics) {
            this.statistics = statistics;
        }

        public static class StatisticsBean {
            private String dept_id;
            private String dept_name;
            private String parent_id;
            private int amount;

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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }
        }
    }
}
