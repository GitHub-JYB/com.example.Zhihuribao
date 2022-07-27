package com.example.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import com.example.zhihuribao.bean.ZhiHu;
import com.example.zhihuribao.mvp.presenter.Impl.DetailPresenterImpl;
import com.example.zhihuribao.mvp.view.DetailView;
import com.example.zhihuribao.util.HtmlUtil;
import com.squareup.picasso.Picasso;

import com.example.zhihuribao.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.iv_story_header)
    ImageView ivStoryHeader;
    @BindView(R.id.tv_story_title)
    TextView tvStoryTitle;
    @BindView(R.id.tv_copyright)
    TextView tvCopyright;
    @BindView(R.id.wb_story)
    WebView wbStory;
    private DetailPresenterImpl presenter;
    private int storyId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        presenter = new DetailPresenterImpl(this);
        Intent intent = getIntent();
        storyId = intent.getIntExtra("storyId",0);
        if (storyId != 0) {
            presenter.getStoryFromModel(storyId);
        }
    }

    @Override
    public void showStories(ZhiHu.Stories stories) {
        String body = HtmlUtil.formatHtml(stories.getBody());
        wbStory.loadDataWithBaseURL("file:///android_assets/",body,"text/html","UTF-8","");
        tvStoryTitle.setText(stories.getTitle());
        tvCopyright.setText(stories.getImage_source());
        Picasso.get()
                .load(stories.getImage())
                .fit()
                .centerCrop()
                .into(ivStoryHeader);
    }
}
