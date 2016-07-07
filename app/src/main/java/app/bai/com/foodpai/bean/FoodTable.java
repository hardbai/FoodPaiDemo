package app.bai.com.foodpai.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by 86724 on 2016/7/7 0007.
 */
@Table(name = "food")
public class FoodTable {
    @Id(column = "id")
    private int id;
    @Column(column = "foodName")
    private String foodName;
    @Column(column = "foodIcon")
    private String foodIcon;
    @Column(column = "foodInfo")
    private String foodInfo;
    @Column(column = "foodUrl")
    private String foodUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodUrl() {
        return foodUrl;
    }

    public void setFoodUrl(String foodUrl) {
        this.foodUrl = foodUrl;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    public String getFoodIcon() {
        return foodIcon;
    }

    public void setFoodIcon(String foodIcon) {
        this.foodIcon = foodIcon;
    }
}
