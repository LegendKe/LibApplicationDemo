package com.ptja.android.mms.bean;

import java.io.Serializable;

/**
 * Created by zhenghou on 2016/7/6.
 */
public class DiscardSubmitResultBean implements Serializable{

    /**
     * code : 0
     * response : {"scrap_log_id":"3","equipment_id":"1","scrap_content":"报废内容","is_finished":"1","finished_time":"2016-07-06 17:13:08","finished_user":"1","result":"1","equipment":{"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"},"user":{"user_id":"1","user_name":"admin","password":"123456","real_name":"仓库管理员","mobile_tel":"1234567890","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1"}}
     * msg : 成功
     */

    private int code;
    /**
     * scrap_log_id : 3
     * equipment_id : 1
     * scrap_content : 报废内容
     * is_finished : 1
     * finished_time : 2016-07-06 17:13:08
     * finished_user : 1
     * result : 1
     * equipment : {"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"}
     * user : {"user_id":"1","user_name":"admin","password":"123456","real_name":"仓库管理员","mobile_tel":"1234567890","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1"}
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

    public static class ResponseBean implements Serializable {
        private String scrap_log_id;
        private String equipment_id;
        private String scrap_content;
        private String is_finished;
        private String finished_time;
        private String finished_user;
        private String result;
        /**
         * equipment_id : 1
         * equipment_name : 灭火器
         * model : XXXXXX
         * standard : xxxxx
         * code : x1x1
         * unit : 台
         * total_number : 1
         * current_number : 1
         * equipment_type_id : 1
         * depot_id : 1
         * created_at : 2016-06-07 17:38:56
         * status : 1
         * pro_date : 2015-01-01
         * shelf_life : 365
         * life_span : 365
         * use_count : 435
         * maintenance_count : 4
         * gallery_id : 17
         */

        private EquipmentBean equipment;
        /**
         * user_id : 1
         * user_name : admin
         * password : 123456
         * real_name : 仓库管理员
         * mobile_tel : 1234567890
         * user_type_id : 1
         * dept_id : 1
         * created_at : 2016-06-06 12:00:00
         * status : 1
         */

        private UserBean user;

        public String getScrap_log_id() {
            return scrap_log_id;
        }

        public void setScrap_log_id(String scrap_log_id) {
            this.scrap_log_id = scrap_log_id;
        }

        public String getEquipment_id() {
            return equipment_id;
        }

        public void setEquipment_id(String equipment_id) {
            this.equipment_id = equipment_id;
        }

        public String getScrap_content() {
            return scrap_content;
        }

        public void setScrap_content(String scrap_content) {
            this.scrap_content = scrap_content;
        }

        public String getIs_finished() {
            return is_finished;
        }

        public void setIs_finished(String is_finished) {
            this.is_finished = is_finished;
        }

        public String getFinished_time() {
            return finished_time;
        }

        public void setFinished_time(String finished_time) {
            this.finished_time = finished_time;
        }

        public String getFinished_user() {
            return finished_user;
        }

        public void setFinished_user(String finished_user) {
            this.finished_user = finished_user;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public EquipmentBean getEquipment() {
            return equipment;
        }

        public void setEquipment(EquipmentBean equipment) {
            this.equipment = equipment;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class EquipmentBean implements Serializable {
            private String equipment_id;
            private String equipment_name;
            private String model;
            private String standard;
            private String code;
            private String unit;
            private String total_number;
            private String current_number;
            private String equipment_type_id;
            private String depot_id;
            private String created_at;
            private String status;
            private String pro_date;
            private String shelf_life;
            private String life_span;
            private String use_count;
            private String maintenance_count;
            private String gallery_id;

            public String getEquipment_id() {
                return equipment_id;
            }

            public void setEquipment_id(String equipment_id) {
                this.equipment_id = equipment_id;
            }

            public String getEquipment_name() {
                return equipment_name;
            }

            public void setEquipment_name(String equipment_name) {
                this.equipment_name = equipment_name;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getStandard() {
                return standard;
            }

            public void setStandard(String standard) {
                this.standard = standard;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getTotal_number() {
                return total_number;
            }

            public void setTotal_number(String total_number) {
                this.total_number = total_number;
            }

            public String getCurrent_number() {
                return current_number;
            }

            public void setCurrent_number(String current_number) {
                this.current_number = current_number;
            }

            public String getEquipment_type_id() {
                return equipment_type_id;
            }

            public void setEquipment_type_id(String equipment_type_id) {
                this.equipment_type_id = equipment_type_id;
            }

            public String getDepot_id() {
                return depot_id;
            }

            public void setDepot_id(String depot_id) {
                this.depot_id = depot_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPro_date() {
                return pro_date;
            }

            public void setPro_date(String pro_date) {
                this.pro_date = pro_date;
            }

            public String getShelf_life() {
                return shelf_life;
            }

            public void setShelf_life(String shelf_life) {
                this.shelf_life = shelf_life;
            }

            public String getLife_span() {
                return life_span;
            }

            public void setLife_span(String life_span) {
                this.life_span = life_span;
            }

            public String getUse_count() {
                return use_count;
            }

            public void setUse_count(String use_count) {
                this.use_count = use_count;
            }

            public String getMaintenance_count() {
                return maintenance_count;
            }

            public void setMaintenance_count(String maintenance_count) {
                this.maintenance_count = maintenance_count;
            }

            public String getGallery_id() {
                return gallery_id;
            }

            public void setGallery_id(String gallery_id) {
                this.gallery_id = gallery_id;
            }
        }

        public static class UserBean implements Serializable {
            private String user_id;
            private String user_name;
            private String password;
            private String real_name;
            private String mobile_tel;
            private String user_type_id;
            private String dept_id;
            private String created_at;
            private String status;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getMobile_tel() {
                return mobile_tel;
            }

            public void setMobile_tel(String mobile_tel) {
                this.mobile_tel = mobile_tel;
            }

            public String getUser_type_id() {
                return user_type_id;
            }

            public void setUser_type_id(String user_type_id) {
                this.user_type_id = user_type_id;
            }

            public String getDept_id() {
                return dept_id;
            }

            public void setDept_id(String dept_id) {
                this.dept_id = dept_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
