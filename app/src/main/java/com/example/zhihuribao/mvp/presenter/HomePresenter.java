package com.example.zhihuribao.mvp.presenter;


import com.example.zhihuribao.bean.ZhiHu;

public interface HomePresenter {

    void getStoriesFromModel();

    void sendStoriesToView(ZhiHu zhiHu);

    void getBeforeStory(String previousDay);


    void sendBeforeStoriesToView(ZhiHu zhiHu);
}
