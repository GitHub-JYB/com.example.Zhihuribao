package com.example.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.zhihuribao.R;
import com.example.zhihuribao.bean.Girl;
import com.example.zhihuribao.mvp.adapter.GirlAdapter;
import com.example.zhihuribao.util.ApiClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class GirlFragment extends Fragment {

    @BindView(R.id.girl_list)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private GirlAdapter adapter;

    public GirlFragment() {
    }

    private static GirlFragment instance = new GirlFragment();

    public static GirlFragment getInstance() {
        if (instance == null){
            synchronized (GirlFragment.class){
                if (instance == null){
                    instance = new GirlFragment();
                }
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_girl, container, false);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        initRecyclerView();
        return view;
    }

    private void getData() {
        ApiClient.getService(ApiClient.Gril_URL)
                .getGirl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Girl>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Girl girl) {
                        adapter.setData(girl.getImgs().subList(0, girl.getImgs().size() - 1));
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
    }

    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GirlAdapter();
        adapter.setOnClickListener(new GirlAdapter.OnClickListener() {
            @Override
            public void onClick(Girl.imgs imas) {
                Intent intent = new Intent(getContext(), GirlDetailActivity.class);
                intent.putExtra("url",imas.getImageUrl());
                intent.putExtra("desc",imas.getDesc());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
