package Core.ItemMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.Conexion;

public class ItemMenuCrud {
    private Connection connection;

    public ItemMenuCrud() {
        // Initialize the database connection using Conexion class
        this.connection = new Conexion().conectarDB();
    }

    // Create
    public void createItemMenu(ItemMenu itemMenu) {
        String query = "INSERT INTO itemMenu (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, itemMenu.getName());
            statement.setString(2, itemMenu.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public ItemMenu getItemMenuById(int id) {
        String query = "SELECT * FROM itemMenu WHERE id = ?";
        ItemMenu itemMenu = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                itemMenu = new ItemMenu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemMenu;
    }

    // Read All
    public List<ItemMenu> getAllItemMenus() {
        String query = "SELECT * FROM itemMenu";
        List<ItemMenu> itemMenuList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                ItemMenu itemMenu = new ItemMenu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                itemMenuList.add(itemMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemMenuList;
    }

    // Update
    public void updateItemMenu(ItemMenu itemMenu) {
        String query = "UPDATE itemMenu SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, itemMenu.getName());
            statement.setString(2, itemMenu.getDescription());
            statement.setInt(3, itemMenu.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteItemMenu(int id) {
        String query = "DELETE FROM itemMenu WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}