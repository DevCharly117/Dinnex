package DB;

import java.sql.*;

public class Conexion {
    public String hostDB = "localhost"; // ip al usar LAN
    public String db = "dinnexdb";
    public String urlDB = "jdbc:mysql://" + hostDB + ":3306/" + db;
    public String usuarioDB = "root"; // Crear un usuario en la base de datos
    public String contraseñaDB = ""; // contraseña

    public Conexion() {
    }

    public Connection conectarDB() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(urlDB, usuarioDB, contraseñaDB);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (Exception e) {
            System.out.println("Fallo en la conexion\n" + e);
        }
        return conexion;
    }
}
