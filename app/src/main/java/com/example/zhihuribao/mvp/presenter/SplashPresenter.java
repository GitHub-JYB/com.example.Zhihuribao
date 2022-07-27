package com.example.zhihuribao.mvp.presenter;

import com.example.zhihuribao.bean.StartImage;




public interface SplashPresenter {

    void getStartImage();

    void sendImageToView(StartImage startImage);

    void showAnimation();
}
