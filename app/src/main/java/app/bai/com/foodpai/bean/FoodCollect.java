package app.bai.com.foodpai.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 16-7-10.
 */
@Table(name = "foodCollect")
public class FoodCollect {
    @Id(column = "code")
    private String code;
    @Column(column = "name")
    private String name;
    @Column(column = "calory")
    private String calory;
    @Column(column = "imgUrl")
    private String imgUrl;

    public FoodCollect() {
    }

    public FoodCollect(String code, String name, String calory, String imgUrl) {
        this.code = code;
        this.name = name;
        this.calory = calory;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

}
