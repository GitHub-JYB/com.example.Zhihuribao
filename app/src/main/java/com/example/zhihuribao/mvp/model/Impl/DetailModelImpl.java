package com.example.zhihuribao.mvp.model.Impl;

import com.example.zhihuribao.bean.ZhiHu;
import com.example.zhihuribao.mvp.model.DetailModel;
import com.example.zhihuribao.mvp.presenter.Impl.DetailPresenterImpl;
import com.example.zhihuribao.util.ApiClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DetailModelImpl implements DetailModel {
    private final DetailPresenterImpl presenter;

    public DetailModelImpl(DetailPresenterImpl detailPresenter) {
        this.presenter = detailPresenter;
    }

    @Override
    public void getStory(int storyId) {
        ApiClient.getService(ApiClient.BASE_URL)
                .getStories(storyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHu.Stories>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHu.Stories stories) {
                        presenter.sendStoriesToView(stories);
                    }
                });
    }
}
