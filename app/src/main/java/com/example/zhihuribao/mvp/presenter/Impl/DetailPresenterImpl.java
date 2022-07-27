package com.example.zhihuribao.mvp.presenter.Impl;

import com.example.zhihuribao.bean.ZhiHu;
import com.example.zhihuribao.mvp.model.Impl.DetailModelImpl;
import com.example.zhihuribao.mvp.presenter.DetailPresenter;
import com.example.zhihuribao.mvp.view.DetailView;


public class DetailPresenterImpl implements DetailPresenter {

    private final DetailView detailView;
    private final DetailModelImpl detailModel;

    public DetailPresenterImpl(DetailView detailView) {
        this.detailView = detailView;
        this.detailModel = new DetailModelImpl(this);
    }

    @Override
    public void getStoryFromModel(int storyId) {
        detailModel.getStory(storyId);
    }

    @Override
    public void sendStoriesToView(ZhiHu.Stories stories) {
        detailView.showStories(stories);
    }
}
