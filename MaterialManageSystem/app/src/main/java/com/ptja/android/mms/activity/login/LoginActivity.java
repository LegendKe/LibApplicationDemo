package com.ptja.android.mms.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.MainActivity;
import com.ptja.android.mms.bean.UserBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.base.BaseActivity;
import hz.framework.android.utils.SharedPreferences;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.account)
    EditText input_username;
    @Bind(R.id.password)
    EditText input_password;
    @Bind(R.id.remember_password)
    CheckBox cb_rememberpassword;
    @Bind(R.id.forget_password)
    TextView tv_forgetpassword;
    @Bind(R.id.login_button)
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String username = SharedPreferences.getInstance().getString(getApplicationContext(),"username",null);
        String password = SharedPreferences.getInstance().getString(getApplicationContext(),"password",null);
        if(username!=null && password!=null){
            input_password.setText(password);
            input_username.setText(username);
        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                Toast.makeText(getApplicationContext(), (CharSequence) msg.obj,Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }

    private void login(){
        if(TextUtils.isEmpty(input_username.getText().toString())){
            Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(input_password.getText().toString())){
            Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (cb_rememberpassword.isChecked()){
            SharedPreferences.getInstance().putString(getApplicationContext(),"username",input_username.getText().toString());
            SharedPreferences.getInstance().putString(getApplicationContext(),"password",input_password.getText().toString());
        }else{
            SharedPreferences.getInstance().putString(getApplicationContext(),"username",null);
            SharedPreferences.getInstance().putString(getApplicationContext(),"password",null);
        }

        HashMap<String,String> params = new HashMap<>();
        params.put("username",input_username.getText().toString());
        params.put("password",input_password.getText().toString());
        sendPostRequest(params, "登录中", null, UrlConstants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code")==0){
                        UserBean user = com.alibaba.fastjson.JSONObject.parseObject(response.getJSONObject("response").toString(),UserBean.class);
                        GlobeVariable.UserInfos = user;

                        if (cb_rememberpassword.isChecked()){
                            SharedPreferences.getInstance().putString(getApplicationContext(),"username",input_username.getText().toString());
                            SharedPreferences.getInstance().putString(getApplicationContext(),"password",input_password.getText().toString());
                        }else{
                            SharedPreferences.getInstance().putString(getApplicationContext(),"username",null);
                            SharedPreferences.getInstance().putString(getApplicationContext(),"password",null);
                        }
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else{
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = response.getString("msg");
                        mHandler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    @OnClick({R.id.remember_password, R.id.login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.remember_password:
                break;
            case R.id.login_button:
                login();
                break;
        }
    }
}
