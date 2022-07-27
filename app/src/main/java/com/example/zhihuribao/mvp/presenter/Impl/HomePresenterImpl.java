package com.example.zhihuribao.mvp.presenter.Impl;

import com.example.zhihuribao.bean.ZhiHu;
import com.example.zhihuribao.mvp.model.Impl.HomeModelImpl;
import com.example.zhihuribao.mvp.presenter.HomePresenter;
import com.example.zhihuribao.mvp.view.HomeView;

import java.util.List;





public class HomePresenterImpl implements HomePresenter {

    private final HomeView homeView;
    private final HomeModelImpl homeModelImpl;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        this.homeModelImpl = new HomeModelImpl(this);
    }

    @Override
    public void getStoriesFromModel() {
        homeModelImpl.getStories();
    }

    @Override
    public void sendStoriesToView(ZhiHu zhiHu) {
        homeView.showStories(zhiHu.getStories(),zhiHu.getTop_stories());
    }

    @Override
    public void getBeforeStory(String previousDay) {
        homeModelImpl.getBeforeStories(previousDay);
    }

    @Override
    public void sendBeforeStoriesToView(ZhiHu zhiHu) {
        homeView.showBeforeStories(zhiHu.getStories());
    }
}
