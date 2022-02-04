package project.model;

public abstract class ActivityComponent {
    String data;
    String description;
    String location;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

  /*  public void printActivityInfo(){
        throw new UnsupportedOperationException();
    }*/
}
