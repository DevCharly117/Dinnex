package Core.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCrud {
    private Connection connection; // Conexión a la base de datos

    // Constructor que inicializa la conexión a la base de datos
    public CustomerCrud(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo cliente en la base de datos
    public void addCustomer(Customer customer) throws SQLException {
        // Consulta SQL para insertar un nuevo cliente
        String query = "INSERT INTO customer (email, password, phoneNumber, firstName, middleName, lastName, neighborhood, street, streetNumber) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Uso de PreparedStatement para evitar inyecciones SQL
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Estableciendo los valores de los parámetros en la consulta
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getFirstName());
            ps.setString(5, customer.getMiddleName());
            ps.setString(6, customer.getLastName());
            ps.setString(7, customer.getNeighborhood());
            ps.setString(8, customer.getStreet());
            ps.setInt(9, customer.getStreetNumber());
            // Ejecutando la consulta
            ps.executeUpdate();
        }
    }

    // Método para verificar si el correo electrónico y la contraseña existen en la
    // base de datos
    public boolean emailAndPasswordExists(String email, String password) throws SQLException {
        // Consulta SQL para verificar el correo electrónico y la contraseña
        String query = "SELECT COUNT(*) FROM customer WHERE email = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Estableciendo los valores de los parámetros en la consulta
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                // Si el resultado tiene al menos una fila, el correo y la contraseña existen
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Método para verificar si el correo electrónico o el número de teléfono
    // existen en la base de datos
    public boolean emailOrPhoneExists(String email, String phoneNumber) throws SQLException {
        // Consulta SQL para verificar el correo electrónico o el número de teléfono
        String query = "SELECT COUNT(*) FROM customer WHERE email = ? OR phoneNumber = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Estableciendo los valores de los parámetros en la consulta
            ps.setString(1, email);
            ps.setString(2, phoneNumber);
            try (ResultSet rs = ps.executeQuery()) {
                // Si el resultado tiene al menos una fila, el correo o el teléfono existen
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Método para obtener todos los clientes de la base de datos
    public List<Customer> getAllCustomers() throws SQLException {
        // Consulta SQL para seleccionar todos los clientes
        String query = "SELECT * FROM customer";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            // Lista para almacenar los clientes obtenidos
            List<Customer> customers = new ArrayList<>();
            // Iterando sobre los resultados de la consulta
            while (rs.next()) {
                // Creando un nuevo objeto Customer con los datos obtenidos
                Customer customer = new Customer(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phoneNumber"),
                        rs.getString("firstName"),
                        rs.getString("middleName"),
                        rs.getString("lastName"),
                        rs.getString("neighborhood"),
                        rs.getString("street"),
                        rs.getInt("streetNumber"));
                // Estableciendo el ID del cliente
                customer.setId(rs.getInt("id"));
                // Añadiendo el cliente a la lista
                customers.add(customer);
            }
            // Retornando la lista de clientes
            return customers;
        }
    }
}