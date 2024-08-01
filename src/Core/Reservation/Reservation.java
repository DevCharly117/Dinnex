package Core.Reservation;

import java.sql.Date;

public class Reservation {
    private int id;
    private Date date;

    // Constructor
    public Reservation(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}