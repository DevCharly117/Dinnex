package Core.Event;

public class Event {
    private int id;
    private int customerId;
    private int reservationId;
    private int menuId;
    private String date;

    public Event(int id, int customerId, int reservationId, int menuId, String date) {
        this.id = id;
        this.customerId = customerId;
        this.reservationId = reservationId;
        this.menuId = menuId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}