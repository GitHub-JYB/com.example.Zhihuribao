package com.example.zhihuribao.mvp.adapter;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.zhihuribao.bean.ZhiHu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.zhihuribao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;



public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isLoading = false;
    private int TYPE_TOP_STORY = 0;
    private int TYPE_STORY = 1;
    private Context context;
    private List<ZhiHu.Stories> stories = new ArrayList<>();
    private OnLoadMoreListener loadMoreListener;
    private TopStoryPagerAdapter topStoryPagerAdapter;
    private  OnClickListener onClickListener;
    private int mpointdistance;

    public StoryAdapter(RecyclerView recyclerView) {
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && lastVisibleItem >= layoutManager.getItemCount() - 1) {
                    if (loadMoreListener != null) {
                        loadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP_STORY;
        }
        return TYPE_STORY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == TYPE_TOP_STORY) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.vp_top_story, parent, false);
            return new ViewHolderTopStory(view);
        }
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_item_story, parent, false);
        return new ViewHolderStory(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderStory){
            ViewHolderStory holderStory = (ViewHolderStory) holder;
            position--;
            holderStory.tvStoryTitle.setText(stories.get(position).getTitle());
            Picasso.get()
                    .load(stories.get(position).getImages().get(0))
                    .into(holderStory.ivStoryImage);
        }else if (holder instanceof ViewHolderTopStory){
            final ViewHolderTopStory holderTopStory = (ViewHolderTopStory) holder;
            holderTopStory.vpTopStory.setAdapter(topStoryPagerAdapter);

            if (topStoryPagerAdapter != null && topStoryPagerAdapter.getCount() > 0) {
                holderTopStory.point_ll.removeAllViews();
                for (int i = 0; i < topStoryPagerAdapter.getCount(); i++) {
                    ImageView gray_point = new ImageView(context);
                    gray_point.setBackgroundResource(R.drawable.shape_gray_point);
                    holderTopStory.point_ll.addView(gray_point);
                    if (i > 0) {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                                (ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.leftMargin = 10;
                        gray_point.setLayoutParams(layoutParams);
                    }
                }

                holderTopStory.point_ll.getViewTreeObserver().addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener() {



                    @Override
                    public void onGlobalLayout() {
                        int xpoint2 = holderTopStory.point_ll.getChildAt(1).getLeft();
                        int xpoint1 = holderTopStory.point_ll.getChildAt(0).getLeft();
                        mpointdistance = xpoint2 - xpoint1;
                        holderTopStory.point_ll.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                    }
                });
            }


            holderTopStory.vpTopStory.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams)holderTopStory.white_point
                                    .getLayoutParams();
                    layoutParams.leftMargin = (int) ((position + positionOffset) * mpointdistance);
                    holderTopStory.white_point.setLayoutParams(layoutParams);
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return stories == null?1:stories.size()+1;
    }

    public void setData(List<ZhiHu.Stories> stories, final List<ZhiHu.TopStories> top_stories) {
        this.stories = stories;
        notifyDataSetChanged();
        topStoryPagerAdapter = new TopStoryPagerAdapter(context,top_stories);
        topStoryPagerAdapter.notifyDataSetChanged();
        topStoryPagerAdapter.setOnClickListener(new TopStoryPagerAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                onClickListener.onClick(top_stories.get(position).getId());
            }
        });

    }

    public void addStories(List<ZhiHu.Stories> stories) {
        this.stories.addAll(stories);
        isLoading = false;
        notifyDataSetChanged();
    }


    class ViewHolderTopStory extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top_story)
        ViewPager vpTopStory;
        @BindView(R.id.point_ll)
        LinearLayout point_ll;
        @BindView(R.id.white_point)
        ImageView white_point;


        ViewHolderTopStory(View view) {
            super(view);
            ButterKnife.bind(this, view);

            Observable.interval(2,TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            int currentItem = vpTopStory.getCurrentItem();
                            if (currentItem + 1 > topStoryPagerAdapter.getCount() - 1){
                                vpTopStory.setCurrentItem(0);
                            }else {
                                vpTopStory.setCurrentItem(currentItem + 1);
                            }
                        }
                    });
        }
    }

    class ViewHolderStory extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_story_title)
        TextView tvStoryTitle;
        @BindView(R.id.iv_story_image)
        ImageView ivStoryImage;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolderStory(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.onClick(stories.get(getAdapterPosition()-1).getId());
                    }
                }
            });

        }
    }

    public interface OnClickListener {
        void onClick(int storyId);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }
}
