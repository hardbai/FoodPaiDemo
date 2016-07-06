package app.bai.com.foodpai.uri;

/**
 * Created by green on 2016/7/2.
 */
public class Uri {

    /**
     *
     */
    public static final String URL_SHOW_IMAGE = "http://food.boohee.com/fb/v1/home/banners";
    //page = ?
    public static final String URL_SHOW_LIST = "http://food.boohee.com/fb/v1/feeds?page=";

    /**
     *
     */
    public static final String URL_WIKIS_I = "http://food.boohee.com/fb/v1/categories/list";

    //value = ?
    public static final String URL_WIKIS_II = "http://food.boohee.com/fb/v1/foods?kind=group&value=%d&order_by=%d&page=%d";

    //foods/?/mode_show
    public static final String URL_WIKIS_III = "http://food.boohee.com/fb/v1/foods/%s/mode_show";
}


