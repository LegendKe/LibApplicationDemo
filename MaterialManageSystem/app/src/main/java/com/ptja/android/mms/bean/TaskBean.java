package com.ptja.android.mms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenghou on 2016/6/14.
 */
public class TaskBean implements Serializable {


    /**
     * task_id : 2
     * task_content : 维保2015年1月份装备
     * task_type_id : 2
     * from_user_id : 2
     * to_user_id : 1
     * status : 0
     * created_at : 2016-06-15 10:55:11
     * finished_time : 0000-00-00 00:00:00
     * start_time : 2015-01-01 00:00:00
     * end_time : 2015-01-02 00:00:00
     * depot_id : 2
     * task_type : {"task_type_id":"2","task_type_name":"装备维保"}
     * task_record : [{"task_record_id":"1","task_id":"2","equipment_id":"1","created_at":"2016-06-15 15:36:11","result":"1","gallery_id":"27","result_text":"维保完成","equipment":{"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"}},{"task_record_id":"2","task_id":"2","equipment_id":"1","created_at":"2016-06-15 15:37:31","result":"1","gallery_id":"28","result_text":"维保完成","equipment":{"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"}},{"task_record_id":"3","task_id":"2","equipment_id":"1","created_at":"2016-06-15 15:37:40","result":"1","gallery_id":"29","result_text":"维保完成","equipment":{"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"}},{"task_record_id":"4","task_id":"2","equipment_id":"1","created_at":"2016-06-15 15:37:47","result":"1","gallery_id":"30","result_text":"维保完成","equipment":{"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"}}]
     * from_user : {"user_name":"sysAdmin","real_name":"系统管理员"}
     * to_user : {"user_name":"admin","real_name":"仓库管理员"}
     * depot : {"depot_id":"2","depot_name":"第二仓库"}
     * status_text : 未处理
     */

    private String task_id;
    private String task_content;
    private String task_type_id;
    private String from_user_id;
    private String to_user_id;
    private String status;
    private String created_at;
    private String finished_time;
    private String start_time;
    private String end_time;
    private String depot_id;
    /**
     * task_type_id : 2
     * task_type_name : 装备维保
     */

    private TaskTypeBean task_type;
    /**
     * user_name : sysAdmin
     * real_name : 系统管理员
     */

    private FromUserBean from_user;
    /**
     * user_name : admin
     * real_name : 仓库管理员
     */

    private ToUserBean to_user;
    /**
     * depot_id : 2
     * depot_name : 第二仓库
     */

    private DepotBean depot;
    private String status_text;
    /**
     * task_record_id : 1
     * task_id : 2
     * equipment_id : 1
     * created_at : 2016-06-15 15:36:11
     * result : 1
     * gallery_id : 27
     * result_text : 维保完成
     * equipment : {"equipment_id":"1","equipment_name":"灭火器","model":"XXXXXX","standard":"xxxxx","code":"x1x1","unit":"台","total_number":"1","current_number":"1","equipment_type_id":"1","depot_id":"1","created_at":"2016-06-07 17:38:56","status":"1","pro_date":"2015-01-01","shelf_life":"365","life_span":"365","use_count":"435","maintenance_count":"4","gallery_id":"17"}
     */

    private List<TaskRecordBean> task_record;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_content() {
        return task_content;
    }

    public void setTask_content(String task_content) {
        this.task_content = task_content;
    }

    public String getTask_type_id() {
        return task_type_id;
    }

    public void setTask_type_id(String task_type_id) {
        this.task_type_id = task_type_id;
    }

    public String getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(String from_user_id) {
        this.from_user_id = from_user_id;
    }

    public String getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(String to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFinished_time() {
        return finished_time;
    }

    public void setFinished_time(String finished_time) {
        this.finished_time = finished_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDepot_id() {
        return depot_id;
    }

    public void setDepot_id(String depot_id) {
        this.depot_id = depot_id;
    }

    public TaskTypeBean getTask_type() {
        return task_type;
    }

    public void setTask_type(TaskTypeBean task_type) {
        this.task_type = task_type;
    }

    public FromUserBean getFrom_user() {
        return from_user;
    }

    public void setFrom_user(FromUserBean from_user) {
        this.from_user = from_user;
    }

    public ToUserBean getTo_user() {
        return to_user;
    }

    public void setTo_user(ToUserBean to_user) {
        this.to_user = to_user;
    }

    public DepotBean getDepot() {
        return depot;
    }

    public void setDepot(DepotBean depot) {
        this.depot = depot;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public List<TaskRecordBean> getTask_record() {
        return task_record;
    }

    public void setTask_record(List<TaskRecordBean> task_record) {
        this.task_record = task_record;
    }

    public static class TaskTypeBean implements Serializable {
        private String task_type_id;
        private String task_type_name;

        public String getTask_type_id() {
            return task_type_id;
        }

        public void setTask_type_id(String task_type_id) {
            this.task_type_id = task_type_id;
        }

        public String getTask_type_name() {
            return task_type_name;
        }

        public void setTask_type_name(String task_type_name) {
            this.task_type_name = task_type_name;
        }
    }

    public static class FromUserBean implements Serializable {
        private String user_name;
        private String real_name;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }

    public static class ToUserBean  implements Serializable{
        private String user_name;
        private String real_name;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }

    public static class DepotBean  implements Serializable{
        private String depot_id;
        private String depot_name;

        public String getDepot_id() {
            return depot_id;
        }

        public void setDepot_id(String depot_id) {
            this.depot_id = depot_id;
        }

        public String getDepot_name() {
            return depot_name;
        }

        public void setDepot_name(String depot_name) {
            this.depot_name = depot_name;
        }
    }

    public static class TaskRecordBean implements Serializable {
        private String task_record_id;
        private String task_id;
        private String equipment_id;
        private String created_at;
        private String result;
        private String gallery_id;
        private String result_text;
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

        public String getTask_record_id() {
            return task_record_id;
        }

        public void setTask_record_id(String task_record_id) {
            this.task_record_id = task_record_id;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getEquipment_id() {
            return equipment_id;
        }

        public void setEquipment_id(String equipment_id) {
            this.equipment_id = equipment_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getGallery_id() {
            return gallery_id;
        }

        public void setGallery_id(String gallery_id) {
            this.gallery_id = gallery_id;
        }

        public String getResult_text() {
            return result_text;
        }

        public void setResult_text(String result_text) {
            this.result_text = result_text;
        }

        public EquipmentBean getEquipment() {
            return equipment;
        }

        public void setEquipment(EquipmentBean equipment) {
            this.equipment = equipment;
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
    }
}
