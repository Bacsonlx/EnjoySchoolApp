package hznu.linxin.banner;

public class Goods {
    private String name;
    private String price;
    private int imageId;
    public Goods(String name, String price, int imageId) {
        this.name = name;
        this.price = price;
        this.imageId = imageId;
    }
    String getGoodsName() {
        return this.name;
    }
    String getGoodsPrice() {
        return this.price;
    }
    int getImageId() { return this.imageId; }
}
