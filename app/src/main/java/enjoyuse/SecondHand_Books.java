package enjoyuse;

public class SecondHand_Books {
    private String book_id;
    private String book_name;
    private String book_price;
    private int book_imageId;
    public SecondHand_Books(String book_id, String book_name, int book_imageId, String book_price) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_imageId = book_imageId;
        this.book_price = book_price;
    }


    public String getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_price() {
        return book_price;
    }

    public int getBook_imageId() {
        return book_imageId;
    }
}