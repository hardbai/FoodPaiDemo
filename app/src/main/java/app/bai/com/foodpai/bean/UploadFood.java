package app.bai.com.foodpai.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 16-7-9.
 */
@Table(name = "uploadfood")
public class UploadFood {
    @Id(column = "id")
    private int id;
    @Column(column = "brand")
    private String brand;
    @Column(column = "name")
    private String name;
    @Column(column = "otherName")
    private String otherName;

    public UploadFood() {
    }

    public UploadFood(String brand, String name, String otherName) {
        this.brand = brand;
        this.name = name;
        this.otherName = otherName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
}
