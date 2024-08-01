package Core.EmployeeType;

public class EmployeeType {
    private int id;
    private String position;
    private String description;

    // Constructor
    public EmployeeType(int id, String position, String description) {
        this.id = id;
        this.position = position;
        this.description = description;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}