package Core.EmployeeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.Conexion;

public class EmployeeTypeCrud {
    private Connection connection;

    public EmployeeTypeCrud() {
        // Initialize the database connection using Conexion class
        this.connection = new Conexion().conectarDB();
    }

    // Create
    public void createEmployeeType(EmployeeType employeeType) {
        String query = "INSERT INTO EmployeeType (position, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeType.getPosition());
            statement.setString(2, employeeType.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public EmployeeType getEmployeeTypeById(int id) {
        String query = "SELECT * FROM EmployeeType WHERE id = ?";
        EmployeeType employeeType = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employeeType = new EmployeeType(
                        resultSet.getInt("id"),
                        resultSet.getString("position"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeType;
    }

    // Read All
    public List<EmployeeType> getAllEmployeeTypes() {
        String query = "SELECT * FROM EmployeeType";
        List<EmployeeType> employeeTypeList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                EmployeeType employeeType = new EmployeeType(
                        resultSet.getInt("id"),
                        resultSet.getString("position"),
                        resultSet.getString("description"));
                employeeTypeList.add(employeeType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeTypeList;
    }

    // Update
    public void updateEmployeeType(EmployeeType employeeType) {
        String query = "UPDATE EmployeeType SET position = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeType.getPosition());
            statement.setString(2, employeeType.getDescription());
            statement.setInt(3, employeeType.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteEmployeeType(int id) {
        String query = "DELETE FROM EmployeeType WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}