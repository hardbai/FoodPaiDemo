package app.bai.com.foodpai.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Food  {

    /**
     * feeds : [{"background":"","content":"每天一把坚果，吃什么好？","id":388,"item_id":468,"link":"http://mp.weixin.qq.com/s?__biz=MzI4MzEwOTMzMw==&mid=2649417839&idx=1&sn=fe1681bb709e41d96cb6f0a84c41abdd#rd","share_link":"http://food.boohee.com/fb/v1/food_news/468/share.html","source":"食物派","tail":"1694人阅读","title":"每天一把坚果，吃什么好？","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_photo_temp/2016/7/6/314bf7845ff0106e3424307ebc9d2179","content":"刷爆朋友圈的早餐，竟然出自90后之手！","id":387,"item_id":464,"link":"http://mp.weixin.qq.com/s?__biz=MzI2NzEzMjg1Mg==&mid=2652062649&idx=2&sn=4b06f34268f4fb467d990518fb81dbc5&scene=4#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/464/share.html","source":"吃不饱的顾里亚","tail":"1795人阅读","title":"刷爆朋友圈的早餐，竟然出自90后之手！","type":"food_news"},{"background":"","content":"下雨天，吃个没负担的薯片吧","id":386,"item_id":454,"link":"http://mp.weixin.qq.com/s?__biz=MzAxODQwMjM4MQ==&mid=2657779318&idx=1&sn=8d49c7c15a73e5f103bb796f849732ea&scene=4#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/454/share.html","source":"Whisky微醺","tail":"1442人阅读","title":"下雨天，吃个没负担的薯片吧","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/5/7414562843a3c6ae5cca8a729cccdf66","content":"坚果有益健康，但不要吃加味坚果","id":385,"item_id":448,"link":"http://apps.weibo.com/5076516542/8s0ZkcjX?m=psp_common_dr&n=5768a3830cf2f4916147153b&channel=1508142810","share_link":"http://food.boohee.com/fb/v1/food_news/448/share.html","source":"营养医师王兴国","tail":"2081人阅读","title":"坚果有益健康，但不要吃加味坚果","type":"food_news"},{"background":"","content":"吐司玩出新花样","id":384,"item_id":441,"link":"http://mp.weixin.qq.com/s?__biz=MjM5MzAxMjYwMA==&mid=2650013612&idx=4&sn=e04d3ea7cd707668681804c7d3a7e09c&scene=0#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/441/share.html","source":"柒零叁吃喝玩乐","tail":"9581人阅读","title":"吐司玩出新花样","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/5/b54aeec28b412c767c3433038052f23d","content":"柠檬冰舒芙蕾 | 让女神带你度过清凉一夏 ","id":383,"item_id":455,"link":"http://mp.weixin.qq.com/s?__biz=MjM5NjEyMjc1Mg==&mid=2649973899&idx=1&sn=d0a3b6945b71bed68368caabd67257da&scene=4#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/455/share.html","source":"烘焙地球村","tail":"3784人阅读","title":"柠檬冰舒芙蕾 | 让女神带你度过清凉一夏 ","type":"food_news"},{"background":"","content":"秒杀一切运动饮料的自制酸梅汤，6种做法马上Get！","id":382,"item_id":452,"link":"http://mp.weixin.qq.com/s?__biz=MjM5ODk1NDE0MQ==&mid=2650629674&idx=1&sn=36160e31c56dd973753d5e9ca4ba3270&scene=4#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/452/share.html","source":"大姐姐","tail":"2507人阅读","title":"秒杀一切运动饮料的自制酸梅汤，6种做法马上Get！","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/5/47e2f3df7398f1922a93a5a5327b4759","content":"燕麦片比大米饭热量更高？减肥能吃么？","id":380,"item_id":457,"link":"http://weibo.com/ttarticle/p/show?id=2309403971888266218213&mod=zwenzhang","share_link":"http://food.boohee.com/fb/v1/food_news/457/share.html","source":"范志红","tail":"10809人阅读","title":"燕麦片比大米饭热量更高？减肥能吃么？","type":"food_news"},{"background":"","content":"营养师笔记 | 身体不补水，还想皮肤水润？ ","id":381,"item_id":456,"link":"http://mp.weixin.qq.com/s?__biz=MzA3MjcyODcyNw==&mid=2651469030&idx=1&sn=8952799c1d1171fe1a9cc9f4760b134f&scene=4#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/456/share.html","source":"刘俊清","tail":"4281人阅读","title":"营养师笔记 | 身体不补水，还想皮肤水润？ ","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/5/80b7a3b196e70d3c39a247e366c5a096","content":"世卫组织：饮用过热饮品会致癌？","id":379,"item_id":449,"link":"http://weibo.com/ttarticle/p/show?id=2309403986737339701855#_0","share_link":"http://food.boohee.com/fb/v1/food_news/449/share.html","source":"世界卫生组织","tail":"3054人阅读","title":"世卫组织：饮用过热饮品会致癌？","type":"food_news"},{"background":"","content":"各品牌果蔬脆片热量排行榜","id":378,"item_id":447,"link":"http://mp.weixin.qq.com/s?__biz=MzI4MzEwOTMzMw==&mid=2649417807&idx=1&sn=cd80f433d93956dbe6948cf0d796d731#rd","share_link":"http://food.boohee.com/fb/v1/food_news/447/share.html","source":"食物派","tail":"7691人阅读","title":"各品牌果蔬脆片热量排行榜","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/4/f9a407e3844eb249ce0e9228f101b55c","content":"为什么抹茶拿铁里不含咖啡","id":377,"item_id":439,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNTEyMzkwMg==&mid=2653782336&idx=1&sn=669939b27749056c12552c1f9131a164&scene=0#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/439/share.html","source":"技术型吃货","tail":"6646人阅读","title":"为什么抹茶拿铁里不含咖啡","type":"food_news"},{"background":"","content":"欧美网红最爱的高颜值Detox Water","id":376,"item_id":437,"link":"http://mp.weixin.qq.com/s?__biz=MzA3MjcyODcyNw==&mid=2651469060&idx=1&sn=5ca214dbac28faf8d6af9fd52ed488b9&scene=0#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/437/share.html","source":"玲珑沙龙","tail":"6566人阅读","title":"欧美网红最爱的高颜值Detox Water","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/6/30/c87afce6668893ddd7d33ba26ee55d92","content":"日式面包和欧式面包的主要区别在哪里？","id":375,"item_id":436,"link":"https://www.zhihu.com/question/29714437/answer/45796354","share_link":"http://food.boohee.com/fb/v1/food_news/436/share.html","source":"SansanL","tail":"8993人阅读","title":"日式面包和欧式面包的主要区别在哪里？","type":"food_news"},{"background":"","content":"喝酸奶有助于预防女性患高血压","id":374,"item_id":445,"link":"http://weibo.com/p/23071757403f3e0cf21c05a463346f?from=page_100505_profile&wvr=6&mod=wenzhangmod","share_link":"http://food.boohee.com/fb/v1/food_news/445/share.html","source":"营养师娟子","tail":"3903人阅读","title":"喝酸奶有助于预防女性患高血压","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/1/f88735ff3c09ffd5f50e13b8443222a7","content":"夏天就应该多喝这些，何况还能增肌减脂！","id":373,"item_id":446,"link":"http://weibo.com/ttarticle/p/show?id=2309403990939180878089","share_link":"http://food.boohee.com/fb/v1/food_news/446/share.html","source":"女王马甲线","tail":"23845人阅读","title":"夏天就应该多喝这些，何况还能增肌减脂！","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/1/ef24fc9e3a46f9cd1411277d29ebd436","content":"先放下！水果上的农药得洗干净","id":372,"item_id":438,"link":"http://mp.weixin.qq.com/s?__biz=MjA1ODMxMDQwMQ==&mid=2657178668&idx=1&sn=1b4aad50e3262db03014afc794dfea66&scene=0#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/438/share.html","source":"云无心","tail":"8376人阅读","title":"先放下！水果上的农药得洗干净","type":"food_news"},{"background":"","content":"不放油也能做出的健康满分菜","id":368,"item_id":421,"link":"http://mp.weixin.qq.com/s?__biz=MzA4NDc2Njc1NA==&mid=2653934658&idx=1&sn=eb226fd1df185568fafd047fe079a216&scene=23&srcid=0701IF15Lwot4cOG2ivtSD5X#rd","share_link":"http://food.boohee.com/fb/v1/food_news/421/share.html","source":"詹姆士的厨房","tail":"13796人阅读","title":"不放油也能做出的健康满分菜","type":"food_news"},{"background":"http://one.boohee.cn/food/image/food_news/2016/7/1/e44fb3edd5972244b763c6e15678cba7","content":"周末不出门，泡面也能做高级","id":371,"item_id":442,"link":"http://mp.weixin.qq.com/s?__biz=MzA4NzU5MjQxMA==&mid=2653962742&idx=3&sn=ef8595c610e0b57da870428913806f5a&scene=0#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/442/share.html","source":"时尚旅游","tail":"8748人阅读","title":"周末不出门，泡面也能做高级","type":"food_news"},{"background":"","content":"你喝的不是奶，是复原乳？","id":367,"item_id":417,"link":"http://mp.weixin.qq.com/s?__biz=MzA5MTEzMDgwOQ==&mid=2651729482&idx=2&sn=3b7e51b54ccd98252f8c5caf3be7dd31&scene=0#wechat_redirect","share_link":"http://food.boohee.com/fb/v1/food_news/417/share.html","source":"Foodaily","tail":"9216人阅读","title":"你喝的不是奶，是复原乳？","type":"food_news"}]
     * page : 1
     * total_pages : 18
     */

    private String page;
    private int total_pages;
    /**
     * background :
     * content : 每天一把坚果，吃什么好？
     * id : 388
     * item_id : 468
     * link : http://mp.weixin.qq.com/s?__biz=MzI4MzEwOTMzMw==&mid=2649417839&idx=1&sn=fe1681bb709e41d96cb6f0a84c41abdd#rd
     * share_link : http://food.boohee.com/fb/v1/food_news/468/share.html
     * source : 食物派
     * tail : 1694人阅读
     * title : 每天一把坚果，吃什么好？
     * type : food_news
     */

    private List<FeedsBean> feeds;

    public static Food objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, Food.class);
    }

    public static Food objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new com.google.gson.Gson().fromJson(jsonObject.getString(str), Food.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Food> arrayFoodFromData(String str) {

        Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Food>>() {
        }.getType();

        return new com.google.gson.Gson().fromJson(str, listType);
    }

    public static List<Food> arrayFoodFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Food>>() {
            }.getType();

            return new com.google.gson.Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<FeedsBean> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedsBean> feeds) {
        this.feeds = feeds;
    }

    public static class FeedsBean {
        private String background;
        private String content;
        private int id;
        private int item_id;
        private String link;
        private String share_link;
        private String source;
        private String tail;
        private String title;
        private String type;

        public static FeedsBean objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, FeedsBean.class);
        }

        public static FeedsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new com.google.gson.Gson().fromJson(jsonObject.getString(str), FeedsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<FeedsBean> arrayFeedsBeanFromData(String str) {

            Type listType = new com.google.gson.reflect.TypeToken<ArrayList<FeedsBean>>() {
            }.getType();

            return new com.google.gson.Gson().fromJson(str, listType);
        }

        public static List<FeedsBean> arrayFeedsBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new com.google.gson.reflect.TypeToken<ArrayList<FeedsBean>>() {
                }.getType();

                return new com.google.gson.Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getShare_link() {
            return share_link;
        }

        public void setShare_link(String share_link) {
            this.share_link = share_link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTail() {
            return tail;
        }

        public void setTail(String tail) {
            this.tail = tail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
