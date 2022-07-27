package com.example.zhihuribao.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zhihuribao.Application.MyApplication;
import com.example.zhihuribao.bean.Girl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.example.zhihuribao.R;



public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Girl.imgs> grillist = new ArrayList<>();
    private List<Integer> mHeight = new ArrayList<>();
    private Context context;
    private OnClickListener onClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_girl, parent, false);
        return new ViewHolderGirl(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mHeight.size() == position){
            mHeight.add((int)(MyApplication.heightPixels * 0.4 + Math.random()*200));
        }
        Picasso.get()
                .load(grillist.get(position).getImageUrl())
                .placeholder(R.drawable.login_wallpaper2)
                .error(R.drawable.login_wallpaper2)
                .resize(MyApplication.widthPixels/2 - MyApplication.dip2px(16),mHeight.get(position))
                .into(((ViewHolderGirl)holder).imageView);
        ((ViewHolderGirl)holder).textView.setText(grillist.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return grillist == null ? 0 : grillist.size();
    }

    public void setData(List<Girl.imgs> grillist){
        this.grillist = grillist;
    }

    class ViewHolderGirl extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        ViewHolderGirl(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.girl_image);
            textView = (TextView) itemView.findViewById(R.id.girl_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.onClick(grillist.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(Girl.imgs imgs);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
