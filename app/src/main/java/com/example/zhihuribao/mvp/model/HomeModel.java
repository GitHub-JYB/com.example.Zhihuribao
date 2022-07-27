package com.example.zhihuribao.mvp.model;



public interface HomeModel {
    void getStories();

    void getBeforeStories(String previousDay);
}
