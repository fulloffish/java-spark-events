package model;

public class Event {
    private Integer id;
    private String name;
    private String description;
    private String date;
    private String category;
    private String link;

    public Event(String name, String date, String description, String category, String link) {
        this.setName(name);
        this.setDate(date);
        this.setDescription(description);
        this.setCategory(category);
        this.setLink(link);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
