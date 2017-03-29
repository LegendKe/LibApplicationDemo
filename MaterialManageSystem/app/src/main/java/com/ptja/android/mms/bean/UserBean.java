package com.ptja.android.mms.bean;

/**
 * Created by zhenghou on 2016/6/8.
 */
public class UserBean {

    /**
     * user_id : 1
     * user_name : admin
     * real_name : 管理员
     * mobile_tel : 1234567890
     * user_type_id : 1
     * created_at : 2016-06-06 12:00:00
     * status : 1
     * access_token : NmRjZDIzNjItZTk4ZS00YzA2LTBhMWMtMGY2ZGU5NjRjNjkz
     * expire_time : 2016-06-07 04:38:30
     */

    private String user_id;
    private String user_name;
    private String real_name;
    private String mobile_tel;
    private String user_type_id;
    private String created_at;
    private String status;
    private String access_token;
    private String expire_time;

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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }
}
