package enjoyuse;

public class Printers {
    private String printer_id;
    private String printer_address;
    private int printer_imageId;
    private boolean printer_usage;
    public Printers(String printer_id, String printer_address, int printer_imageId, boolean printer_usage) {
        this.printer_id = printer_id;
        this.printer_address = printer_address;
        this.printer_imageId = printer_imageId;
        this.printer_usage = printer_usage;
    }

    public String getPrinter_id() {
        return printer_id;
    }

    public String getPrinter_address() {
        return printer_address;
    }

    public int getPrinter_imageId() {
        return printer_imageId;
    }

    public boolean isPrinter_usage() {
        return printer_usage;
    }
}