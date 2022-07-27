package com.example.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.NonNull;
import com.example.zhihuribao.mvp.presenter.Impl.MainPresenterImpl;
import com.example.zhihuribao.mvp.view.MainView;
import androidx.annotation.Nullable;
import com.example.zhihuribao.R;
import com.example.zhihuribao.util.ToastUtil;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainPresenterImpl presenter;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        initToolbar();
        initToggle();
        initDrawerContent();
        replaceFragment(null,HomeFragment.getInstance());
    }

    private void initDrawerContent() {
        navigation.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                presenter.switchNavition(item);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)
        ||super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)){
            closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void closeDrawers() {
        drawerlayout.closeDrawers();
    }

    @Override
    public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!newFragment.isAdded()){
            if (oldFragment != null){
                transaction.hide(oldFragment).add(R.id.content,newFragment).commit();
            }else {
                transaction.add(R.id.content,newFragment).commit();
            }
        }else {
            if (oldFragment != null){
                transaction.hide(oldFragment).show(newFragment).commit();
            }else {
                transaction.show(newFragment).commit();
            }
        }
    }

    @Override
    public void exit() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     *  关联drawerlayout和toolbar
     */
    private void initToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerlayout,toolbar, R.string.open, R.string.close);
        drawerlayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                ToastUtil.showShortToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
