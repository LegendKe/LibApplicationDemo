package com.ptja.android.mms.bean;

import java.util.List;

/**
 * Created by zhenghou on 2016/7/5.
 */
public class EquipTypeBean {

    /**
     * code : 0
     * response : [{"year":2013,"month":1,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":3344},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":2092},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":1741},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":2210}]},{"year":2013,"month":2,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4519},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":2616},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":1095},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":1385}]},{"year":2013,"month":3,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":552},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":4524},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":582},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":2769}]},{"year":2013,"month":4,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4338},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":833},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":2031},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":1885}]},{"year":2013,"month":5,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4235},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":1227},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":645},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":1608}]},{"year":2013,"month":6,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4100},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":3239},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":3466},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":3951}]},{"year":2013,"month":7,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4445},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":2836},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":3126},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":3654}]},{"year":2013,"month":8,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":2671},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":1686},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":769},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":896}]},{"year":2013,"month":9,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4564},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":227},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":2949},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":3862}]},{"year":2013,"month":10,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":2493},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":2428},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":3068},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":4179}]},{"year":2013,"month":11,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":716},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":1753},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":4482},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":3778}]},{"year":2013,"month":12,"statistics":[{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":4005},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":3629},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":2719},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":2679}]}]
     * msg : 成功
     */

    private int code;
    private String msg;
    /**
     * year : 2013
     * month : 1
     * statistics : [{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0","count":3344},{"equipment_type_id":"2","equipment_type_name":"特种防护装备","parent_id":"0","count":2092},{"equipment_type_id":"3","equipment_type_name":"办公用品","parent_id":"0","count":1741},{"equipment_type_id":"4","equipment_type_name":"其他","parent_id":"0","count":2210}]
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
        /**
         * equipment_type_id : 1
         * equipment_type_name : 基本防护装备
         * parent_id : 0
         * count : 3344
         */

        private List<StatisticsBean> statistics;

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
            private String equipment_type_id;
            private String equipment_type_name;
            private String parent_id;
            private int count;

            public String getEquipment_type_id() {
                return equipment_type_id;
            }

            public void setEquipment_type_id(String equipment_type_id) {
                this.equipment_type_id = equipment_type_id;
            }

            public String getEquipment_type_name() {
                return equipment_type_name;
            }

            public void setEquipment_type_name(String equipment_type_name) {
                this.equipment_type_name = equipment_type_name;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
