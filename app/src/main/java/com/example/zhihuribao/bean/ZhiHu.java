package com.example.zhihuribao.bean;


import androidx.annotation.Nullable;

import java.util.List;


public class ZhiHu {


    private String date;

    private List<Stories> stories;

    @Nullable
    private List<TopStories> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStories> top_stories) {
        this.top_stories = top_stories;
    }

    public static class Stories {
        private String title;
        private int id;
        private String body;
        private String image;
        private String image_source;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage_source() {
            return image_source;
        }

        public void setImage_source(String image_source) {
            this.image_source = image_source;
        }

        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStories {
        private String image;
        private int id;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
