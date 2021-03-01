package enjoyuse;

public class Washers {
    private String washer_id;
    private String washer_address;
    private int washer_imageId;
    private String washer_time;
    private boolean washer_usage;
    public Washers(String washer_id, String washer_address, int washer_imageId, boolean washer_usage, String washer_time) {
        this.washer_id = washer_id;
        this.washer_address = washer_address;
        this.washer_imageId = washer_imageId;
        this.washer_usage = washer_usage;
        this.washer_time = washer_time;

    }

    public String getWasher_id() {
        return washer_id;
    }

    public String getWasher_address() {
        return washer_address;
    }

    public int getWasher_imageId() {
        return washer_imageId;
    }

    public boolean isWasher_usage() {
        return washer_usage;
    }

    public String getWasher_time() {
        return washer_time;
    }

}