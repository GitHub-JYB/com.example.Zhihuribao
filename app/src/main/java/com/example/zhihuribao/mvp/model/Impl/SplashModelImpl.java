package com.example.zhihuribao.mvp.model.Impl;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.zhihuribao.bean.StartImage;
import com.example.zhihuribao.mvp.model.SplashModel;
import com.example.zhihuribao.mvp.presenter.Impl.SplashPresenterImpl;
import com.example.zhihuribao.util.ApiClient;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;


public class SplashModelImpl implements SplashModel {

    private final SplashPresenterImpl presenter;

    public SplashModelImpl(SplashPresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getStartImage() {
        ApiClient.getService(ApiClient.BASE_URL)
                .getStartImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartImage>() {
                    @Override
                    public void onComplete() {
                        presenter.showAnimation();
                    }

                    @Override
                    public void onError(Throwable e) {
                        StartImage startImage = new StartImage();
                        startImage.setText("知乎日报");
                        startImage.setImg("https://pic1.zhimg.com/v2-0bf26092e8bd38a59d08dc9326fe5ca8.jpg");
                        presenter.sendImageToView(startImage);
                        onComplete();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: aaaaaaa");
                    }

                    @Override
                    public void onNext(StartImage startImage) {
                        presenter.sendImageToView(startImage);
                    }
                });
    }
}
