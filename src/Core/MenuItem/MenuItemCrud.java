package Core.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.Conexion;

public class MenuItemCrud {
    private Connection connection;

    public MenuItemCrud() {
        // Initialize the database connection using Conexion class
        this.connection = new Conexion().conectarDB();
    }

    // Create a new MenuItem
    public void createMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO MenuItem (menuId, itemMenuId) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuItem.getMenuId());
            stmt.setInt(2, menuItem.getItemMenuId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read a MenuItem by ID
    public MenuItem getMenuItemById(int menuId, int itemMenuId) {
        String sql = "SELECT * FROM MenuItem WHERE menuId = ? AND itemMenuId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            stmt.setInt(2, itemMenuId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MenuItem(
                        rs.getInt("menuId"),
                        rs.getInt("itemMenuId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all MenuItems
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM MenuItem";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                menuItems.add(new MenuItem(
                        rs.getInt("menuId"),
                        rs.getInt("itemMenuId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // Update a MenuItem
    public void updateMenuItem(MenuItem menuItem) {
        String sql = "UPDATE MenuItem SET itemMenuId = ? WHERE menuId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuItem.getItemMenuId());
            stmt.setInt(2, menuItem.getMenuId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a MenuItem
    public void deleteMenuItem(int menuId, int itemMenuId) {
        String sql = "DELETE FROM MenuItem WHERE menuId = ? AND itemMenuId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            stmt.setInt(2, itemMenuId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}