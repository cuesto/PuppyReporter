package infosocial.com.puppyreporter;


public class Report {

    private String photo;
    private String location;
    private String description;


    public Report() {
    }

    public Report(String photo, String location, String description) {
        this.photo = photo;
        this.location = location;
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
