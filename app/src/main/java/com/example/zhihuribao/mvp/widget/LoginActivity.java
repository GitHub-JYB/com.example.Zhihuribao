package com.example.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.zhihuribao.R;
import com.example.zhihuribao.util.SpUtil;
import com.example.zhihuribao.util.ToastUtil;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void login(View view){
        if (username.getText().toString().trim().isEmpty() ||
                password.getText().toString().trim().isEmpty()){
            ToastUtil.showShortToast("用户名密码不能为空");
        }else {
            BmobUser bmobUser = new BmobUser();
            bmobUser.setUsername(username.getText().toString().trim());
            bmobUser.setPassword(password.getText().toString().trim());
            bmobUser.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null){
                        ToastUtil.showShortToast("登录成功");
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        SpUtil.getIntance(getBaseContext()).setUsername(bmobUser.getUsername());
                        finish();
                    }else {
                        switch (e.getErrorCode()){
                            case 101:
                                ToastUtil.showShortToast("用户名或密码不正确");
                                break;
                            case 9016:
                                ToastUtil.showShortToast("无网络连接，请检查您的手机网络");
                                break;
                            default:
                                ToastUtil.showShortToast(e.getMessage());
                                break;
                        }

                    }
                }
            });
        }
    }

    public void question(View view){
        ToastUtil.showShortToast("请输入6位以上的密码");
    }
}
