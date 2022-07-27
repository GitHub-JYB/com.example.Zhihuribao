package com.example.zhihuribao.mvp.view;


import androidx.fragment.app.Fragment;





public interface MainView {

    void closeDrawers();

    void replaceFragment(Fragment oldFragment, Fragment newFragment);

    void exit();
}
