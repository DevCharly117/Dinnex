package Core.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCrud {
    private Connection connection;

    public EventCrud(Connection connection) {
        this.connection = connection;
    }

    // Crear una nueva reserva
    private int createReservation(String date) {
        String sql = "INSERT INTO reservation (date) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(date));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Devuelve el ID de la reserva recién creada
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indica que no se pudo crear la reserva
    }

    // Crear un nuevo Evento
    public void createEvent(Event event) {
        int reservationId = createReservation(event.getDate());
        if (reservationId != -1) {
            String sql = "INSERT INTO Event (customerId, reservationId, menuId, eventDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, event.getCustomerId());
                stmt.setInt(2, reservationId);
                stmt.setInt(3, event.getMenuId());
                stmt.setDate(4, Date.valueOf(event.getDate()));
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error al crear la reserva.");
        }
    }

    // Leer un Evento por ID
    public Event getEventById(int id) {
        String sql = "SELECT * FROM Event WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Event(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getInt("reservationId"),
                        rs.getInt("menuId"),
                        rs.getDate("eventDate").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leer todos los Eventos
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getInt("reservationId"),
                        rs.getInt("menuId"),
                        rs.getDate("eventDate").toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Actualizar un Evento
    public void updateEvent(Event event) {
        String sql = "UPDATE Event SET customerId = ?, reservationId = ?, menuId = ?, eventDate = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, event.getCustomerId());
            stmt.setInt(2, event.getReservationId());
            stmt.setInt(3, event.getMenuId());
            stmt.setDate(4, Date.valueOf(event.getDate()));
            stmt.setInt(5, event.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar un Evento
    public void deleteEvent(int id) {
        String sql = "DELETE FROM Event WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Comprobar si una fecha está disponible
    public boolean isDateAvailable(String date) {
        String sql = "SELECT COUNT(*) FROM Event WHERE eventDate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtener eventos por ID de cliente
    public List<Event> getEventsByCustomerId(int customerId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getInt("reservationId"),
                        rs.getInt("menuId"),
                        rs.getDate("eventDate") != null ? rs.getDate("eventDate").toString() : "No date set"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}