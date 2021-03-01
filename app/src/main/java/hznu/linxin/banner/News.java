package hznu.linxin.banner;

public class News {
    private String newsTitle;
    private String newsContent;
    private String newsLink;
    private int imageId;
    public News(String newsTitle, String newsContent, int imageId, String newsLink) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.imageId = imageId;
        this.newsLink = newsLink;
    }
    String getNewsTitle() {
        return this.newsTitle;
    }
    String getNewsContent() {
        return this.newsContent;
    }
    String getnewsLink() { return this.newsLink; }
    int getImageId() { return this.imageId; }
}
