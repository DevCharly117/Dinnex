package Core.MenuItem;

public class MenuItem {
    private int menuId;
    private int itemMenuId;

    // Constructor
    public MenuItem(int menuId, int itemMenuId) {
        this.menuId = menuId;
        this.itemMenuId = itemMenuId;
    }

    // Getters and Setters
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getItemMenuId() {
        return itemMenuId;
    }

    public void setItemMenuId(int itemMenuId) {
        this.itemMenuId = itemMenuId;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuId=" + menuId +
                ", itemMenuId=" + itemMenuId +
                '}';
    }
}