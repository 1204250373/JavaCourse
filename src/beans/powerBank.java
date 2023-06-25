package beans;

public class powerBank {
    private String id;
    private int electricity;
    private int available;

    public powerBank() {
        super();
    }

    public powerBank(String id, int electricity, int available) {
        this.id = id;
        this.electricity = electricity;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getelectricity() {
        return electricity;
    }

    public void setelectricity(int electricity) {
        this.electricity = electricity;
    }

    public int getavailable() {
        return available;
    }

    public void setavailable(int available) {
        this.available = available;
    }
}
