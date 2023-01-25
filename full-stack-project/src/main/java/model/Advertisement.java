package model;

public class Advertisement {

    private long id;
    private long userId;
    private String title;

    public Advertisement(long id, long userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public Advertisement(long userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
