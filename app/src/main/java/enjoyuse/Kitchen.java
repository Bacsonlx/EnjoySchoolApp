package enjoyuse;

public class Kitchen {
    private String kitchen_id;
    private String kitchen_address;
    private int kitchen_imageId;
    private String kitchen_wait;
    private String kitchen_time;
    public Kitchen(String kitchen_id, String kitchen_address, int kitchen_imageId, String kitchen_wait, String kitchen_time){
        this.kitchen_id = kitchen_id;
        this.kitchen_address = kitchen_address;
        this.kitchen_imageId = kitchen_imageId;
        this.kitchen_wait = kitchen_wait;
        this.kitchen_time = kitchen_time;
    }

    public String getKitchen_time() {
        return kitchen_time;
    }

    public String getKitchen_wait() {
        return kitchen_wait;
    }

    public int getKitchen_imageId() {
        return kitchen_imageId;
    }

    public String getKitchen_address() {
        return kitchen_address;
    }

    public String getKitchen_id() {
        return kitchen_id;
    }
}
