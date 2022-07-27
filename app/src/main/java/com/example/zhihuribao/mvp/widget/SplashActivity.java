package com.example.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhihuribao.bean.StartImage;
import com.example.zhihuribao.mvp.presenter.Impl.SplashPresenterImpl;
import com.example.zhihuribao.mvp.view.SplashView;
import com.example.zhihuribao.util.SpUtil;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import com.example.zhihuribao.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity implements SplashView {

    @BindView(R.id.iv_logo)
    ImageView iv_logo;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.text)
    TextView text;
    private SplashPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setPresenter();
        presenter.getStartImage();
    }

    private void finishSplash() {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (SpUtil.getIntance(getBaseContext()).getUsername() == ""){
                            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                            finish();
                        }else {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public void setPresenter() {
        presenter = new SplashPresenterImpl(this);
    }

    @Override
    public void showImageAndText(String img, String text) {
        Picasso.get().load(img).fit().into(iv);
        this.text.setText(text);
    }

    @Override
    public void showAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_splash_logo);
        iv.startAnimation(animation);
        iv_logo.startAnimation(animation1);
        text.startAnimation(animation1);
        finishSplash();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
