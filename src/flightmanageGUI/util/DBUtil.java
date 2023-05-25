package flightmanageGUI.util;

import flightmanageGUI.po.FlightInfo;

import java.sql.*;

/**
 * @ author ps_zhao
 * @ description
 */
public class DBUtil {

//    private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=FlightManage;encrypt=false";
//    private static final String USER = "sa";
//    private static final String PASSWORD = "123";
//    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static final String URL = "jdbc:mysql://localhost:3306/flightmanage?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    //静态代码块负责加载驱动
    static {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            System.out.println("数据库连接失败！");
        }
    }

    //返回数据库连接对象
    public static Connection getConnection() {
        return conn;
    }

    /*
    * @description 添加航班信息
    * @param sqlAdd 添加航班信息的sql语句，flightInfo 航班信息
    * @return flag 添加成功返回true，否则返回false
     */
    public static boolean addFlight(String sqlAdd, FlightInfo flightInfo) {
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            preparedStatement = conn.prepareStatement(sqlAdd);
            preparedStatement.setInt(1, flightInfo.getNumber());
            preparedStatement.setString(2, flightInfo.getId());
            preparedStatement.setString(3, flightInfo.getDestination());
            preparedStatement.setDate(4, flightInfo.getDeparture());
            flag = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /*
    * 重载executeQuery方法，用于查询所有航班信息
     */
    public static ResultSet showFlights(String sqlQuery) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /*
    * 重载executeQuery方法，用于查询指定日期的航班信息
     */
    public static ResultSet queryFlightByDeparture(String sqlQuery, Date date) {
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setDate(1, date);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /*
    * 重载executeQuery方法，用于查询指定目的地的航班信息
     */
    public static ResultSet queryFlightByDestination(String sqlQuery, String destination){
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, destination);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean deleteFlightByNum(String sqlDeleteByNum, int number) {
        // 根据航班号删除航班信息
        PreparedStatement preparedStatement;
        boolean flag = false;
        try{
            preparedStatement = conn.prepareStatement(sqlDeleteByNum);
            preparedStatement.setInt(1, number);
            int i = preparedStatement.executeUpdate();
            if(i != 0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean updateFlightByNum(String sqlUpdate, FlightInfo flightInfo) {
        // 根据航班号更新航班信息
        PreparedStatement preparedStatement;
        boolean flag = false;
        try{
            preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, flightInfo.getId());
            preparedStatement.setString(2, flightInfo.getDestination());
            preparedStatement.setDate(3, flightInfo.getDeparture());
            preparedStatement.setInt(4, flightInfo.getNumber());
            int i = preparedStatement.executeUpdate();
            if(i != 0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
