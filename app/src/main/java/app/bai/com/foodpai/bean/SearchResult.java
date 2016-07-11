package app.bai.com.foodpai.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/10.
 */
public class SearchResult {

    /**
     * items : [{"calory":"575","code":"qiaqiaxiaoerxiangxiguazi_naiyo","health_light":3,"id":44031,"is_liquid":false,"name":"洽洽小而香西瓜子（奶油味）","thumb_image_url":"http://s.boohee.cn/house/upload_food/2013/3/1/627491_1362116495mid.jpg","type":"food","weight":"100"},{"calory":"92","code":"bingliangxiguading","health_light":1,"id":13290,"is_liquid":false,"name":"冰凉西瓜丁","thumb_image_url":"http://s.boohee.cn/house/menu_mid/1166151322701_mid.jpg","type":"food","weight":"100"},{"calory":"109","code":"shanyaoxiguachaobaihe","health_light":2,"id":3900,"is_liquid":false,"name":"山药西瓜炒百合","type":"food","weight":"100"},{"calory":"536","code":"alinhicuiyouxianghixiguazi","health_light":3,"id":42481,"is_liquid":false,"name":"阿林 脆又香 西瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2013/2/2/452803_1359804175mid.jpg","type":"food","weight":"100"},{"calory":"375","code":"hongyinghigancaoguazi","health_light":1,"id":44590,"is_liquid":false,"name":"鸿鹰 甘草瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2013/3/9/452803_1362798638mid.jpg","type":"food","weight":"100"},{"calory":"326","code":"taoyuanjianminhixiguajiang","health_light":3,"id":79927,"is_liquid":false,"name":"桃园建民 西瓜酱","thumb_image_url":"http://s.boohee.cn/house/upload_food/2014/5/11/452803_1399818877mid.jpg","type":"food","weight":"100"},{"calory":"222","code":"fuzhongxianghixiguadoujiang_xi","health_light":2,"id":79946,"is_liquid":false,"name":"府中香 西瓜豆酱（香辣味）","thumb_image_url":"http://s.boohee.cn/house/upload_food/2014/5/11/452803_1399818843mid.jpg","type":"food","weight":"100"},{"calory":"553","code":"fd5f875f","health_light":3,"id":163801,"is_liquid":false,"name":"正林 瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/7/1/mid_photo_url_c3092fc7a39649208697f8806281c8cd.jpg","type":"food","weight":"100"},{"calory":"58","code":"fd80fb24","health_light":1,"id":104341,"is_liquid":false,"name":"自制西瓜奶昔","thumb_image_url":"http://s.boohee.cn/house/upload_food/2015/8/16/7464303_1439655367mid.jpg","type":"food","weight":"100"},{"calory":"33","code":"shuangkouxiguazhong","health_light":1,"id":14419,"is_liquid":false,"name":"爽口西瓜盅","type":"food","weight":"100"},{"calory":"109","code":"shijinxiguazhong","health_light":1,"id":11444,"is_liquid":false,"name":"什锦西瓜盅","type":"food","weight":"100"},{"calory":"405","code":"fd937f11","health_light":3,"id":140352,"is_liquid":false,"name":"西琪 西琪派西瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/4/27/mid_photo_url_8cae123de0f2440baae613b0dbffd6d2.jpg","type":"food","weight":"100"},{"calory":"24","code":"fd99652c","health_light":1,"id":135819,"is_liquid":true,"name":"UFC 西瓜椰子水","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/4/18/mid_photo_url_6E3EE8C06687.jpg","type":"food","weight":"100"},{"calory":"343","code":"zhenglinguazi_xiguazi","health_light":3,"id":21529,"is_liquid":false,"name":"正林瓜子（西瓜子）","thumb_image_url":"http://s.boohee.cn/house/upload_food/2010/3/6/307417_1267870850mid.jpg","type":"food","weight":"100"},{"calory":"275","code":"fd3dc0f8","health_light":1,"id":108065,"is_liquid":false,"name":"利强 西瓜糖","thumb_image_url":"http://s.boohee.cn/house/upload_food/2015/12/14/mid_photo_url_5F8E0A0615E4.jpg","type":"food","weight":"100"},{"calory":"533","code":"laiyifennaiyouweiguazi","health_light":3,"id":51253,"is_liquid":false,"name":"来伊份奶油味瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2013/6/26/1985126_1372255913mid.jpg","type":"food","weight":"100"},{"calory":"559","code":"fdf4b122","health_light":3,"id":109435,"is_liquid":false,"name":"华味享 小的西瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2015/12/18/mid_photo_url_e9df449f57b8c19b0670657aec03453b.png","type":"food","weight":"100"},{"calory":"557","code":"fdef77cc","health_light":3,"id":116327,"is_liquid":false,"name":"恰恰 大片西瓜子（话梅味）","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/2/17/mid_photo_url_252F7216E047.jpg","type":"food","weight":"100"},{"calory":"7","code":"fd71f33f","health_light":1,"id":122317,"is_liquid":false,"name":"雅乐多 V6（西瓜味）","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/3/16/mid_photo_url_7b16c64c1dd8e2577c36b9cb9262ae69.png","type":"food","weight":"100"},{"calory":"603","code":"fdb34bd1","health_light":3,"id":114458,"is_liquid":false,"name":"阿里山 手剥西瓜子","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/1/26/mid_photo_url_4F0F3DBFB77C.jpg","type":"food","weight":"100"}]
     * page : 2
     * tags : [{"name":"坚果","type":"tags"},{"name":"油脂型坚果","type":"tags"}]
     * total_pages : 6
     */

    private int page;
    private int total_pages;
    /**
     * calory : 575
     * code : qiaqiaxiaoerxiangxiguazi_naiyo
     * health_light : 3
     * id : 44031
     * is_liquid : false
     * name : 洽洽小而香西瓜子（奶油味）
     * thumb_image_url : http://s.boohee.cn/house/upload_food/2013/3/1/627491_1362116495mid.jpg
     * type : food
     * weight : 100
     */

    private List<ItemsBean> items;
    /**
     * name : 坚果
     * type : tags
     */

    private List<TagsBean> tags;

    public static SearchResult objectFromData(String str) {

        return new Gson().fromJson(str, SearchResult.class);
    }

    public static SearchResult objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SearchResult.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SearchResult> arraySearchResultFromData(String str) {

        Type listType = new TypeToken<ArrayList<SearchResult>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SearchResult> arraySearchResultFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SearchResult>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class ItemsBean {
        private String calory;
        private String code;
        private int health_light;
        private int id;
        private boolean is_liquid;
        private String name;
        private String thumb_image_url;
        private String type;
        private String weight;

        public static ItemsBean objectFromData(String str) {

            return new Gson().fromJson(str, ItemsBean.class);
        }

        public static ItemsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ItemsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ItemsBean> arrayItemsBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ItemsBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ItemsBean> arrayItemsBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ItemsBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getHealth_light() {
            return health_light;
        }

        public void setHealth_light(int health_light) {
            this.health_light = health_light;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIs_liquid() {
            return is_liquid;
        }

        public void setIs_liquid(boolean is_liquid) {
            this.is_liquid = is_liquid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb_image_url() {
            return thumb_image_url;
        }

        public void setThumb_image_url(String thumb_image_url) {
            this.thumb_image_url = thumb_image_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }

    public static class TagsBean {
        private String name;
        private String type;

        public static TagsBean objectFromData(String str) {

            return new Gson().fromJson(str, TagsBean.class);
        }

        public static TagsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), TagsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<TagsBean> arrayTagsBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<TagsBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<TagsBean> arrayTagsBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<TagsBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
