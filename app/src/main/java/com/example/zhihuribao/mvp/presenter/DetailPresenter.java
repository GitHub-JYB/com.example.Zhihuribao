package com.example.zhihuribao.mvp.presenter;

import com.example.zhihuribao.bean.ZhiHu;




public interface DetailPresenter {

    void getStoryFromModel(int storyId);

    void sendStoriesToView(ZhiHu.Stories stories);
}
