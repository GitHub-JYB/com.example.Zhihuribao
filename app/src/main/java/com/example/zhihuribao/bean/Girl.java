package com.example.zhihuribao.bean;

import java.util.List;



public class Girl {

    private List<imgs> imgs;

    public List<imgs> getImgs() {
        return imgs;
    }

    public void setImgs(List<imgs> imgs) {
        this.imgs = imgs;
    }

    public static class imgs{

        private String imageUrl;
        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
