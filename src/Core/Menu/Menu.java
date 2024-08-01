package Core.Menu;

public class Menu {
    private int id;
    private String name;
    private int itemMenuId;

    // Constructor
    public Menu(int id, String name, int itemMenuId) {
        this.id = id;
        this.name = name;
        this.itemMenuId = itemMenuId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemMenuId() {
        return itemMenuId;
    }

    public void setItemMenuId(int itemMenuId) {
        this.itemMenuId = itemMenuId;
    }
}
