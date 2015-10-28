package com.example.ruolan.lovego.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.utils.UserDataManager;

public class LoginActivity extends Activity implements View.OnClickListener {


    private EditText userName,userPassword;
    private Button btn_login,btn_cancel,btn_register;
    private UserDataManager mUserDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName = (EditText) findViewById(R.id.edit_username);
        userPassword = (EditText) findViewById(R.id.edit_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        if (mUserDataManager == null){
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDatabase();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_cancel:
                cancel();
                break;
            case R.id.btn_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }

    private void cancel() {
        finish();
    }

    private void login() {
        if (isUserNameAndPwdVaild()){
            String username = userName.getText().toString().trim();
            String userpwd = userPassword.getText().toString().trim();
            int result = mUserDataManager.findUserNameAndPwd(username,userpwd);
            if (result == 1){
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        }

    }

    public boolean isUserNameAndPwdVaild(){
        if (userName.getText().toString().trim().equals("")){
            Toast.makeText(this, "账户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userPassword.getText().toString().trim().equals("")){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
