package com.example.zhihuribao.mvp.presenter.Impl;

import com.example.zhihuribao.bean.StartImage;
import com.example.zhihuribao.mvp.model.Impl.SplashModelImpl;
import com.example.zhihuribao.mvp.presenter.SplashPresenter;
import com.example.zhihuribao.mvp.view.SplashView;





public class SplashPresenterImpl implements SplashPresenter {

    private final SplashView splashView;
    private final SplashModelImpl splashModelImpl;

    public SplashPresenterImpl(SplashView splashView) {
        this.splashView = splashView;
        this.splashModelImpl = new SplashModelImpl(this);
    }


    @Override
    public void getStartImage() {
        splashModelImpl.getStartImage();
    }

    @Override
    public void sendImageToView(StartImage startImage) {
        splashView.showImageAndText(startImage.getImg(),startImage.getText());
    }

    @Override
    public void showAnimation() {
        splashView.showAnimation();
    }
}
