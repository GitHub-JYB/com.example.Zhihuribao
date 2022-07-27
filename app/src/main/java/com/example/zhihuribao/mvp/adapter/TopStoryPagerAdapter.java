package com.example.zhihuribao.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.zhihuribao.bean.ZhiHu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.example.zhihuribao.R;
import butterknife.BindView;
import butterknife.ButterKnife;


 public  class TopStoryPagerAdapter extends PagerAdapter {
    @BindView(R.id.iv_top_story)
    ImageView ivTopStory;
    @BindView(R.id.tv_top_story)
    TextView tvTopStory;
    private Context context;
    private List<ZhiHu.TopStories> top_stories = new ArrayList<>();
    private OnClickListener onClickListener;

    public TopStoryPagerAdapter(Context context, List<ZhiHu.TopStories> top_stories) {
        this.context = context;
        this.top_stories = top_stories;
    }

    public void setData(List<ZhiHu.TopStories> top_stories){
        this.top_stories = top_stories;
    }

    @Override
    public int getCount() {
        return top_stories == null ? 0 : top_stories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.vp_item_top_story, container, false);
        ButterKnife.bind(this,view);
        Picasso.get()
                .load(top_stories.get(position).getImage())
                .fit()
                .centerCrop()
                .into(ivTopStory);
        tvTopStory.setText(top_stories.get(position).getTitle());
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.onClick(position);
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    interface OnClickListener{
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
