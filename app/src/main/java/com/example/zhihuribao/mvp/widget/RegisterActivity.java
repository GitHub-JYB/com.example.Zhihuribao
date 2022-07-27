package com.example.zhihuribao.mvp.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhihuribao.R;
import com.example.zhihuribao.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public void register(View view){
        if (username.getText().toString().trim().isEmpty()){
            ToastUtil.showShortToast("用户名不能为空");
        }else if (password.getText().toString().trim().length()<6 || confirmPassword.getText().toString().trim().length()<6){
            ToastUtil.showShortToast("请输入6位以上的密码");
            }else if (!password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
                    ToastUtil.showShortToast("前后密码不一致");
                }else {
                    BmobUser bmobUser = new BmobUser();
                    bmobUser.setUsername(username.getText().toString().trim());
                    bmobUser.setPassword(password.getText().toString().trim());
                    bmobUser.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null){
                            ToastUtil.showShortToast("注册成功");
                            finish();
                        }else {
                            switch (e.getErrorCode()){
                                case 9016:
                                    ToastUtil.showShortToast("无网络连接，请检查您的手机网络");
                                    break;
                                case 301:
                                    ToastUtil.showShortToast("邮箱格式不正确");
                                    break;
                                case 202:
                                    ToastUtil.showShortToast("用户名已经存在");
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
}
