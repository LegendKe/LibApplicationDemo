package com.ptja.android.mms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenghou on 2016/7/6.
 */
public class EquipApproveBean implements Serializable{


    /**
     * code : 0
     * response : {"total":3,"per_page":20,"current_page":1,"last_page":1,"from":1,"to":3,"data":[{"application_id":"4","equipment_type_id":"1","from_user_id":"10","from_troop_id":"4","equipment_name":"装备名称","equipment_count":"10","equipment_budget":"1000000","equipment_remark":"申请原因","created_at":"2016-07-19 14:10:54","current_workflow_id":"9","result":"0","updated_at":"2016-07-19 14:10:54","current_workflow_info":"等待合肥市消防大队队长审核","target_troop_id":"3","target_user_type_id":"3","is_revoke":"1","from_user_info":{"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"},"from_troop_info":{"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"},"equipment_type_info":{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}},{"application_id":"2","equipment_type_id":"1","from_user_id":"10","from_troop_id":"4","equipment_name":"装备名称","equipment_count":"10","equipment_budget":"1000000","equipment_remark":"申请原因","created_at":"2016-07-13 17:06:14","current_workflow_id":"6","result":"2","updated_at":"2016-07-13 17:06:41","current_workflow_info":"审批流程结束，拒绝","target_troop_id":"0","target_user_type_id":"0","is_revoke":"1","from_user_info":{"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"},"from_troop_info":{"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"},"equipment_type_info":{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}},{"application_id":"1","equipment_type_id":"1","from_user_id":"10","from_troop_id":"4","equipment_name":"装备名称","equipment_count":"10","equipment_budget":"1000000","equipment_remark":"申请原因","created_at":"2016-07-13 16:18:20","current_workflow_id":"3","result":"1","updated_at":"2016-07-13 17:02:11","current_workflow_info":"审批流程结束，同意","target_troop_id":"0","target_user_type_id":"0","is_revoke":"0","from_user_info":{"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"},"from_troop_info":{"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"},"equipment_type_info":{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}}]}
     * msg : 成功
     */

    private int code;
    /**
     * total : 3
     * per_page : 20
     * current_page : 1
     * last_page : 1
     * from : 1
     * to : 3
     * data : [{"application_id":"4","equipment_type_id":"1","from_user_id":"10","from_troop_id":"4","equipment_name":"装备名称","equipment_count":"10","equipment_budget":"1000000","equipment_remark":"申请原因","created_at":"2016-07-19 14:10:54","current_workflow_id":"9","result":"0","updated_at":"2016-07-19 14:10:54","current_workflow_info":"等待合肥市消防大队队长审核","target_troop_id":"3","target_user_type_id":"3","is_revoke":"1","from_user_info":{"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"},"from_troop_info":{"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"},"equipment_type_info":{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}},{"application_id":"2","equipment_type_id":"1","from_user_id":"10","from_troop_id":"4","equipment_name":"装备名称","equipment_count":"10","equipment_budget":"1000000","equipment_remark":"申请原因","created_at":"2016-07-13 17:06:14","current_workflow_id":"6","result":"2","updated_at":"2016-07-13 17:06:41","current_workflow_info":"审批流程结束，拒绝","target_troop_id":"0","target_user_type_id":"0","is_revoke":"1","from_user_info":{"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"},"from_troop_info":{"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"},"equipment_type_info":{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}},{"application_id":"1","equipment_type_id":"1","from_user_id":"10","from_troop_id":"4","equipment_name":"装备名称","equipment_count":"10","equipment_budget":"1000000","equipment_remark":"申请原因","created_at":"2016-07-13 16:18:20","current_workflow_id":"3","result":"1","updated_at":"2016-07-13 17:02:11","current_workflow_info":"审批流程结束，同意","target_troop_id":"0","target_user_type_id":"0","is_revoke":"0","from_user_info":{"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"},"from_troop_info":{"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"},"equipment_type_info":{"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}}]
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
        private int per_page;
        private int current_page;
        private int last_page;
        private int from;
        private int to;
        /**
         * application_id : 4
         * equipment_type_id : 1
         * from_user_id : 10
         * from_troop_id : 4
         * equipment_name : 装备名称
         * equipment_count : 10
         * equipment_budget : 1000000
         * equipment_remark : 申请原因
         * created_at : 2016-07-19 14:10:54
         * current_workflow_id : 9
         * result : 0
         * updated_at : 2016-07-19 14:10:54
         * current_workflow_info : 等待合肥市消防大队队长审核
         * target_troop_id : 3
         * target_user_type_id : 3
         * is_revoke : 1
         * from_user_info : {"user_id":"10","user_name":"zd_cangguan","password":"123456","real_name":"中队仓管","mobile_tel":"454545","user_type_id":"1","dept_id":"1","created_at":"2016-06-06 12:00:00","status":"1","troop_id":"4","troop_type_id":"4"}
         * from_troop_info : {"troop_id":"4","troop_name":"合肥市消防中队","troop_type_id":"4","parent_id":"3","const_type_v":"4","troop_head":"负责人"}
         * equipment_type_info : {"equipment_type_id":"1","equipment_type_name":"基本防护装备","parent_id":"0"}
         */

        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String application_id;
            private String equipment_type_id;
            private String from_user_id;
            private String from_troop_id;
            private String equipment_name;
            private String equipment_count;
            private String equipment_budget;
            private String equipment_remark;
            private String created_at;
            private String current_workflow_id;
            private String result;
            private String updated_at;
            private String current_workflow_info;
            private String target_troop_id;
            private String target_user_type_id;
            private String is_revoke;
            /**
             * user_id : 10
             * user_name : zd_cangguan
             * password : 123456
             * real_name : 中队仓管
             * mobile_tel : 454545
             * user_type_id : 1
             * dept_id : 1
             * created_at : 2016-06-06 12:00:00
             * status : 1
             * troop_id : 4
             * troop_type_id : 4
             */

            private FromUserInfoBean from_user_info;
            /**
             * troop_id : 4
             * troop_name : 合肥市消防中队
             * troop_type_id : 4
             * parent_id : 3
             * const_type_v : 4
             * troop_head : 负责人
             */

            private FromTroopInfoBean from_troop_info;
            /**
             * equipment_type_id : 1
             * equipment_type_name : 基本防护装备
             * parent_id : 0
             */

            private EquipmentTypeInfoBean equipment_type_info;

            public String getApplication_id() {
                return application_id;
            }

            public void setApplication_id(String application_id) {
                this.application_id = application_id;
            }

            public String getEquipment_type_id() {
                return equipment_type_id;
            }

            public void setEquipment_type_id(String equipment_type_id) {
                this.equipment_type_id = equipment_type_id;
            }

            public String getFrom_user_id() {
                return from_user_id;
            }

            public void setFrom_user_id(String from_user_id) {
                this.from_user_id = from_user_id;
            }

            public String getFrom_troop_id() {
                return from_troop_id;
            }

            public void setFrom_troop_id(String from_troop_id) {
                this.from_troop_id = from_troop_id;
            }

            public String getEquipment_name() {
                return equipment_name;
            }

            public void setEquipment_name(String equipment_name) {
                this.equipment_name = equipment_name;
            }

            public String getEquipment_count() {
                return equipment_count;
            }

            public void setEquipment_count(String equipment_count) {
                this.equipment_count = equipment_count;
            }

            public String getEquipment_budget() {
                return equipment_budget;
            }

            public void setEquipment_budget(String equipment_budget) {
                this.equipment_budget = equipment_budget;
            }

            public String getEquipment_remark() {
                return equipment_remark;
            }

            public void setEquipment_remark(String equipment_remark) {
                this.equipment_remark = equipment_remark;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getCurrent_workflow_id() {
                return current_workflow_id;
            }

            public void setCurrent_workflow_id(String current_workflow_id) {
                this.current_workflow_id = current_workflow_id;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCurrent_workflow_info() {
                return current_workflow_info;
            }

            public void setCurrent_workflow_info(String current_workflow_info) {
                this.current_workflow_info = current_workflow_info;
            }

            public String getTarget_troop_id() {
                return target_troop_id;
            }

            public void setTarget_troop_id(String target_troop_id) {
                this.target_troop_id = target_troop_id;
            }

            public String getTarget_user_type_id() {
                return target_user_type_id;
            }

            public void setTarget_user_type_id(String target_user_type_id) {
                this.target_user_type_id = target_user_type_id;
            }

            public String getIs_revoke() {
                return is_revoke;
            }

            public void setIs_revoke(String is_revoke) {
                this.is_revoke = is_revoke;
            }

            public FromUserInfoBean getFrom_user_info() {
                return from_user_info;
            }

            public void setFrom_user_info(FromUserInfoBean from_user_info) {
                this.from_user_info = from_user_info;
            }

            public FromTroopInfoBean getFrom_troop_info() {
                return from_troop_info;
            }

            public void setFrom_troop_info(FromTroopInfoBean from_troop_info) {
                this.from_troop_info = from_troop_info;
            }

            public EquipmentTypeInfoBean getEquipment_type_info() {
                return equipment_type_info;
            }

            public void setEquipment_type_info(EquipmentTypeInfoBean equipment_type_info) {
                this.equipment_type_info = equipment_type_info;
            }

            public static class FromUserInfoBean {
                private String user_id;
                private String user_name;
                private String password;
                private String real_name;
                private String mobile_tel;
                private String user_type_id;
                private String dept_id;
                private String created_at;
                private String status;
                private String troop_id;
                private String troop_type_id;

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

                public String getTroop_id() {
                    return troop_id;
                }

                public void setTroop_id(String troop_id) {
                    this.troop_id = troop_id;
                }

                public String getTroop_type_id() {
                    return troop_type_id;
                }

                public void setTroop_type_id(String troop_type_id) {
                    this.troop_type_id = troop_type_id;
                }
            }

            public static class FromTroopInfoBean {
                private String troop_id;
                private String troop_name;
                private String troop_type_id;
                private String parent_id;
                private String const_type_v;
                private String troop_head;

                public String getTroop_id() {
                    return troop_id;
                }

                public void setTroop_id(String troop_id) {
                    this.troop_id = troop_id;
                }

                public String getTroop_name() {
                    return troop_name;
                }

                public void setTroop_name(String troop_name) {
                    this.troop_name = troop_name;
                }

                public String getTroop_type_id() {
                    return troop_type_id;
                }

                public void setTroop_type_id(String troop_type_id) {
                    this.troop_type_id = troop_type_id;
                }

                public String getParent_id() {
                    return parent_id;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }

                public String getConst_type_v() {
                    return const_type_v;
                }

                public void setConst_type_v(String const_type_v) {
                    this.const_type_v = const_type_v;
                }

                public String getTroop_head() {
                    return troop_head;
                }

                public void setTroop_head(String troop_head) {
                    this.troop_head = troop_head;
                }
            }

            public static class EquipmentTypeInfoBean {
                private String equipment_type_id;
                private String equipment_type_name;
                private String parent_id;

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
            }
        }
    }
}
