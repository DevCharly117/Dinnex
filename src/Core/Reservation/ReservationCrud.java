package Core.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.Conexion;

public class ReservationCrud {
    private Connection connection;

    public ReservationCrud() {
        // Initialize the database connection using Conexion class
        this.connection = new Conexion().conectarDB();
    }

    // Create a new Reservation
    public void createReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (date) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, reservation.getDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read a Reservation by ID
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM Reservation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("id"),
                        rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all Reservations
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("id"),
                        rs.getDate("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Update a Reservation
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE Reservation SET date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, reservation.getDate());
            stmt.setInt(2, reservation.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a Reservation
    public void deleteReservation(int id) {
        String sql = "DELETE FROM Reservation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}