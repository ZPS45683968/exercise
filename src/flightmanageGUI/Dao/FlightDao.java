package flightmanageGUI.Dao;

import flightmanageGUI.po.FlightInfo;

import javax.swing.table.TableModel;
import java.sql.Date;
import java.sql.ResultSet;

/**
 * @ author ps_zhao
 * @ description
 */
public interface FlightDao {
    public boolean addFlight(FlightInfo flightInfo);
    public ResultSet showFlights();
    public ResultSet queryFlightByDeparture(Date date);
    public ResultSet queryFlightByDestination(String destination);
    public boolean deleteFlightByNum(int number);
    public boolean updateFlightByNum(FlightInfo flightInfo);

    TableModel buildTableModel(ResultSet resultSet);
}
