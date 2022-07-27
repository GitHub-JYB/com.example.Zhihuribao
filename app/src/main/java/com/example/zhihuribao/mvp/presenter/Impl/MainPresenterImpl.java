package com.example.zhihuribao.mvp.presenter.Impl;

import android.view.MenuItem;

import com.example.zhihuribao.Application.MyApplication;
import com.example.zhihuribao.mvp.presenter.MainPresenter;
import com.example.zhihuribao.mvp.view.MainView;

import com.example.zhihuribao.R;
import com.example.zhihuribao.mvp.widget.GirlFragment;
import com.example.zhihuribao.mvp.widget.HomeFragment;
import com.example.zhihuribao.util.SpUtil;


public class MainPresenterImpl implements MainPresenter {

    private final MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void switchNavition(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                if (!isItemChecked(item)){
                    mainView.replaceFragment(GirlFragment.getInstance(), HomeFragment.getInstance());
                }
                break;
            case R.id.nav_girl:
                if (!isItemChecked(item)){
                    mainView.replaceFragment(HomeFragment.getInstance(), GirlFragment.getInstance());
                }
                break;
            case R.id.nav_exit:
                SpUtil.getIntance(MyApplication.getInstance()).setUsername("");
                mainView.exit();
                break;
        }
        item.setChecked(true);
        mainView.closeDrawers();
    }

    private boolean isItemChecked(MenuItem item) {
        return item.isChecked();
    }
}
