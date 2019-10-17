import java.time.LocalDateTime;

public class Vacancy {
    private static final String LN = System.lineSeparator();

    private String name;
    private String desc;
    private LocalDateTime date;
    private String link;

    @Override
    public String toString() {
        return String.format(" Vacancy: %s Description: %s Link: %s Date: %s", name, desc, link, date);
    }

    public Vacancy(String name, String desc, LocalDateTime date, String link) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public String getDescription() {
        return desc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime actualDate) {
        this.date = actualDate;
    }
}



