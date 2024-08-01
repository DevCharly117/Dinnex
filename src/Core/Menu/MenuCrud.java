package Core.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuCrud {
    private Connection connection;

    public MenuCrud(Connection connection) {
        this.connection = connection;
    }

    // Crear un nuevo Menu
    public void createMenu(Menu menu) {
        String query = "INSERT INTO Menu (name, itemMenuId) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, menu.getName());
            statement.setInt(2, menu.getItemMenuId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer un Menu por ID
    public Menu getMenuById(int id) {
        String query = "SELECT * FROM Menu WHERE id = ?";
        Menu menu = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                menu = new Menu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("itemMenuId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }

    // Leer todos los Menús
    public List<Menu> getAllMenus() {
        String query = "SELECT * FROM Menu";
        List<Menu> menuList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Menu menu = new Menu(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("itemMenuId"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }

    // Actualizar un Menu
    public void updateMenu(Menu menu) {
        String query = "UPDATE Menu SET name = ?, itemMenuId = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, menu.getName());
            statement.setInt(2, menu.getItemMenuId());
            statement.setInt(3, menu.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar un Menu
    public void deleteMenu(int id) {
        String query = "DELETE FROM Menu WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}