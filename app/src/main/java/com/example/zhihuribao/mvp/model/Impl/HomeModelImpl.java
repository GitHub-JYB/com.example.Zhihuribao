package com.example.zhihuribao.mvp.model.Impl;

import android.util.Log;

import com.example.zhihuribao.bean.ZhiHu;
import com.example.zhihuribao.mvp.model.HomeModel;
import com.example.zhihuribao.mvp.presenter.Impl.HomePresenterImpl;
import com.example.zhihuribao.util.ApiClient;

import org.reactivestreams.Subscriber;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeModelImpl implements HomeModel {
    private final HomePresenterImpl presenter;

    public HomeModelImpl(HomePresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getStories() {
        ApiClient.getService(ApiClient.BASE_URL)
                .getZhiHu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHu>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHu zhiHu) {
                        presenter.sendStoriesToView(zhiHu);
                    }
                });
    }

    @Override
    public void getBeforeStories(String previousDay) {

        ApiClient.getService(ApiClient.BASE_URL)
                .getBeforeZhiHu(previousDay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHu>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHu zhiHu) {
                        presenter.sendBeforeStoriesToView(zhiHu);
                    }
                });
    }
}
