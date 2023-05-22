package flightmanage.view;

import flightmanage.Dao.FlightDao;
import flightmanage.Dao.FlightDaoImpl;
import flightmanage.po.FlightInfo;

import java.sql.ResultSet;
import java.sql.Date;
import java.util.Scanner;

/**
 * @ author ps_zhao
 * @ description
 */
public class FlightView {
    FlightDao flightDao = new FlightDaoImpl();
    Scanner scanner = new Scanner(System.in);
    public void menu(){
        System.out.println("******************欢迎使用航班信息管理系统******************");
        System.out.print("请选择操作");
        System.out.print("(1.列出所有航班,2.按起飞时间查询,3.按目的地查询,4.删除航班,5.更新航班,6.离开系统)：");
    }

    public void printFlightInfo(ResultSet resultSet){
        System.out.println("航班号\t航班编号\t目的地\t\t起飞时间");
        try{
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("number") + "\t\t" + resultSet.getString("id") + "\t\t" + resultSet.getString("destination") + "\t\t" + resultSet.getDate("departure"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showFlights(){
        ResultSet resultSet = flightDao.showFlights();
        printFlightInfo(resultSet);
    }

    public void queryFlightByDeparture(){
        System.out.print("请输入日期：");
        String departure = scanner.next();
        // 将字符串转换为java.sql.Date
        Date sqlDate = Date.valueOf(departure);
        ResultSet resultSet = flightDao.queryFlightByDeparture(sqlDate);
        printFlightInfo(resultSet);
    }
    public void queryFlightByDestination(){
        System.out.print("请输入目的地：");
        String destination = scanner.next();
        ResultSet resultSet = flightDao.queryFlightByDestination(destination);
        printFlightInfo(resultSet);
    }
    public void deleteFlight(){
        System.out.print("请输入要删除的航班编号：");
        int number = scanner.nextInt();
        boolean flag = flightDao.deleteFlight(number);
        if(flag){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }
    public void updateFlight(){
        FlightInfo flightInfo = new FlightInfo();
        System.out.print("请输入需要更新的航班编号：");
        flightInfo.setNumber(scanner.nextInt());
        System.out.print("请输入新的航班号：");
        flightInfo.setId(scanner.next());
        System.out.print("请输入新的目的地：");
        flightInfo.setDestination(scanner.next());
        System.out.print("请输入新的起飞日期：");
        String departure = scanner.next();
        // 将字符串转换为java.sql.Date
        Date sqlDate = Date.valueOf(departure);
        flightInfo.setDeparture(sqlDate);
        boolean flag = flightDao.updateFlight(flightInfo);
        if(flag){
            System.out.println("更新成功！");
        }else{
            System.out.println("更新失败！");
        }
    }

}
