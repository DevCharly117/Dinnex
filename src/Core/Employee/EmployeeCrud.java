package Core.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.Conexion;

public class EmployeeCrud {
    private Connection connection; // Conexión a la base de datos

    // Constructor que inicializa la conexión a la base de datos usando la clase
    // Conexion
    public EmployeeCrud() {
        this.connection = new Conexion().conectarDB(); // Establece la conexión a la base de datos
    }

    // Método para crear un nuevo empleado en la base de datos
    public void createEmployee(Employee employee) {
        String query = "INSERT INTO Employee (name, positionId) VALUES (?, ?)"; // Consulta SQL para insertar un nuevo
                                                                                // empleado
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName()); // Establece el nombre del empleado en la consulta
            statement.setInt(2, employee.getPositionId()); // Establece el ID del puesto del empleado en la consulta
            statement.executeUpdate(); // Ejecuta la consulta
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el seguimiento de la pila en caso de una excepción SQL
        }
    }

    // Método para obtener un empleado por su ID
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM Employee WHERE id = ?"; // Consulta SQL para obtener un empleado por su ID
        Employee employee = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); // Establece el ID del empleado en la consulta
            ResultSet resultSet = statement.executeQuery(); // Ejecuta la consulta y obtiene los resultados
            if (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("positionId")); // Crea un nuevo objeto Employee con los datos obtenidos
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el seguimiento de la pila en caso de una excepción SQL
        }
        return employee; // Retorna el empleado encontrado, o null si no se encontró
    }

    // Método para obtener todos los empleados
    public List<Employee> getAllEmployees() {
        String query = "SELECT * FROM Employee"; // Consulta SQL para obtener todos los empleados
        List<Employee> employeeList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("positionId")); // Crea un nuevo objeto Employee con los datos obtenidos
                employeeList.add(employee); // Añade el empleado a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el seguimiento de la pila en caso de una excepción SQL
        }
        return employeeList; // Retorna la lista de empleados
    }

    // Método para actualizar un empleado
    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET name = ?, positionId = ? WHERE id = ?"; // Consulta SQL para actualizar un
                                                                                    // empleado
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName()); // Establece el nombre del empleado en la consulta
            statement.setInt(2, employee.getPositionId()); // Establece el ID del puesto del empleado en la consulta
            statement.setInt(3, employee.getId()); // Establece el ID del empleado en la consulta
            statement.executeUpdate(); // Ejecuta la consulta
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el seguimiento de la pila en caso de una excepción SQL
        }
    }

    // Método para eliminar un empleado por su ID
    public void deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE id = ?"; // Consulta SQL para eliminar un empleado por su ID
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); // Establece el ID del empleado en la consulta
            statement.executeUpdate(); // Ejecuta la consulta
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el seguimiento de la pila en caso de una excepción SQL
        }
    }
}
