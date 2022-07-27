package com.example.zhihuribao.util;

import com.example.zhihuribao.bean.Girl;
import com.example.zhihuribao.bean.StartImage;
import com.example.zhihuribao.bean.ZhiHu;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;



public class ApiClient {

    public static final String BASE_URL = "https://news-at.zhihu.com/api/4/";
    public static final String Gril_URL = "https://image.baidu.com/data/";

    private static Retrofit retrofit = null;

    public interface ApiService{

        @GET("imgs?col=美女&tag=美女&sort=0&pn=20&rn=20&p=channel&from=1")
        Observable<Girl> getGirl();

        @GET("start-image/1080*1776")
        Observable<StartImage> getStartImage();

        @GET("news/latest")
        Observable<ZhiHu> getZhiHu();

        @GET("news/before/{date}")
        Observable<ZhiHu> getBeforeZhiHu(@Path("date") String date);

        @GET("news/{id}")
        Observable<ZhiHu.Stories> getStories(@Path("id") int id);
    }

    private static Retrofit getClient(String url){
        if (url.equals(BASE_URL)){
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
        }else if (url.equals(Gril_URL)){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Gril_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getService(String url){
        return getClient(url).create(ApiService.class);
    }

}
