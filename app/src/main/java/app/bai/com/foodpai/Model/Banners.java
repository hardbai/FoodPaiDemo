package app.bai.com.foodpai.Model;

import java.util.List;

/**
 * Created by JasonLi on 2016/6/8.
 */
public class Banners
{
    /**
     * name : 饮食分析
     * image_key : http://up.boohee.cn/house/u/food_library/home_banner/banner1.jpg
     * link_to : foodlibrary://diet_analysis
     * position : 1
     */

    private List<BannersBean> banners;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean {
        private String name;
        private String image_key;
        private String link_to;
        private int position;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_key() {
            return image_key;
        }

        public void setImage_key(String image_key) {
            this.image_key = image_key;
        }

        public String getLink_to() {
            return link_to;
        }

        public void setLink_to(String link_to) {
            this.link_to = link_to;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
