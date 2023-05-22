package flightmanage.Dao;

import flightmanage.po.FlightInfo;
import flightmanage.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * @ author ps_zhao
 * @ description
 */
public class FlightDaoImpl implements FlightDao{
    @Override
    public boolean addFlight(FlightInfo flightInfo) {
        // 添加航班信息
        String sqlAdd = "insert into flightinfo values(?,?,?,?)";
        boolean flag = DBUtil.addFlight(sqlAdd, flightInfo);
        if(flag){
            System.out.println("添加成功！");
        }else{
            System.out.println("添加失败！");
        }
        return flag;
    }
    @Override
    public ResultSet showFlights(){
        // 查询所有航班信息
        String sqlQueryAll = "select * from flightinfo";
        ResultSet resultSet = DBUtil.showFlights(sqlQueryAll);
        return resultSet;
    }
    @Override
    public ResultSet queryFlightByDeparture(Date date) {
        String sqlQueryByDeparture = "select * from flightinfo where departure = ?";
        ResultSet resultSet = DBUtil.queryFlightByDeparture(sqlQueryByDeparture, date);
        return resultSet;
    }

    @Override
    public ResultSet queryFlightByDestination(String destination) {
        String sqlQueryByDestination = "select * from flightinfo where destination = ?";
        ResultSet resultSet = DBUtil.queryFlightByDestination(sqlQueryByDestination, destination);
        return resultSet;
    }
    @Override
    public boolean deleteFlight(int number) {
        String sqlDeleteByNum = "delete from flightinfo where number = ?";
        boolean flag = DBUtil.deleteFlightByNum(sqlDeleteByNum, number);
        return flag;
    }

    @Override
    public boolean updateFlight(FlightInfo flightInfo) {
        String sqlUpdate = "update flightinfo set id = ?, destination = ?, departure = ? where number = ?";
        boolean flag = DBUtil.executeUpdate(sqlUpdate, flightInfo);
        return flag;
    }


}
