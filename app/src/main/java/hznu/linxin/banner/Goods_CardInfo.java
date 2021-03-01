package hznu.linxin.banner;

import android.content.res.Resources;

public class Goods_CardInfo {
    private String name;
    private String price;
    private int imageId;
    private int num;
    public Goods_CardInfo(String name, String price, int imageId, int num) {
        this.name = name;
        this.price = price;
        this.imageId = imageId;
        this.num = num;
    }
    String getGoodsName() {
        return this.name;
    }
    String getGoodsPrice() {
        return this.price;
    }
    int getImageId() { return this.imageId; }
    int getNum() { return this.num; }
    void setNum(int num) { this.num = num; }
}
