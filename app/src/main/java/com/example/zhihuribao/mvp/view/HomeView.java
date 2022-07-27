package com.example.zhihuribao.mvp.view;

import com.example.zhihuribao.bean.ZhiHu;

import java.util.List;




public interface HomeView {

    void setPresenter();

    void showStories(List<ZhiHu.Stories> stories, List<ZhiHu.TopStories> top_stories);

    void cancelRefresh();

    void showBeforeStories(List<ZhiHu.Stories> stories);
}
