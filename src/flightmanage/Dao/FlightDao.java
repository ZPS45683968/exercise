package flightmanage.Dao;

import flightmanage.po.FlightInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * @ author ps_zhao
 * @ description
 */
public interface FlightDao {
    public boolean addFlight(FlightInfo flightInfo);
    public ResultSet showFlights();
    public ResultSet queryFlightByDeparture(Date date);
    public ResultSet queryFlightByDestination(String destination);
    public boolean deleteFlight(int number);
    public boolean updateFlight(FlightInfo flightInfo);

}
