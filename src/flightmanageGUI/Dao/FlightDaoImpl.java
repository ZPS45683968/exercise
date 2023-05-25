package flightmanageGUI.Dao;

import flightmanageGUI.po.FlightInfo;
import flightmanageGUI.util.DBUtil;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @ author ps_zhao
 * @ description
 */
public class FlightDaoImpl implements FlightDao {
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
    public boolean deleteFlightByNum(int number) {
        String sqlDeleteByNum = "delete from flightinfo where number = ?";
        boolean flag = DBUtil.deleteFlightByNum(sqlDeleteByNum, number);
        return flag;
    }

    @Override
    public boolean updateFlightByNum(FlightInfo flightInfo) {
        String sqlUpdate = "update flightinfo set id = ?, destination = ?, departure = ? where number = ?";
        boolean flag = DBUtil.updateFlightByNum(sqlUpdate, flightInfo);
        return flag;
    }

    @Override
    public TableModel buildTableModel(ResultSet resultSet) {
        // 获取列名
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            String[] columnNames = new String[columnCount];
            for(int i = 0; i < columnCount; i++){
                columnNames[i] = metaData.getColumnName(i + 1);
            }
            // 获取行数
            int rowCount = 0;
            while(resultSet.next()){
                rowCount++;
            }

            // 获取数据
            Object[][] data = new Object[rowCount][columnCount];
            int row = 0;
            resultSet.beforeFirst();
            while(resultSet.next()){
                for(int i = 0; i < columnCount; i++){
                    data[row][i] = resultSet.getObject(i+1);
                    System.out.print(data[row][i] + " ");
                }
                System.out.println();
                row++;
            }
            return new DefaultTableModel(data, columnNames);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
