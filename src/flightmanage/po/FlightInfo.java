package flightmanage.po;

import java.sql.Date;

/**
 * @ author ps_zhao
 * @ description
 */
public class FlightInfo {
    private int number;
    private String id;
    private String destination;
    private Date departure;

    public FlightInfo(){

    }
    public FlightInfo(int number, String id, String destination, Date departure) {
        this.number = number;
        this.id = id;
        this.destination = destination;
        this.departure = departure;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }
}
