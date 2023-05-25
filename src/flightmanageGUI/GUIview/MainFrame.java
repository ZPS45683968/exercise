package flightmanageGUI.GUIview;

import flightmanageGUI.Dao.FlightDao;
import flightmanageGUI.Dao.FlightDaoImpl;
import flightmanageGUI.po.FlightInfo;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;

/**
 * @Author ps_zhao
 * @Description 主界面
 */
public class MainFrame extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    //    private JMenu addFlightMenu = new JMenu("添加航班信息");
    private JMenu showFlightMenu = new JMenu("列出航班信息");
    private JMenu searchFlightMenu = new JMenu("查询航班信息");
    private JMenuItem searchByDepartureItem = new JMenuItem("按起飞时间查询");
    private JMenuItem searchByDestinationItem = new JMenuItem("按目的地查询");
    private JMenu deleteFlightMenu = new JMenu("删除航班信息");
    private JMenu updateFlightMenu = new JMenu("修改航班信息");
    private JMenu exitMenu = new JMenu("退出系统");
    private JScrollPane scrollPane;
    private JTable table;

    private JPanel panel;

    private FlightDao flightDao = new FlightDaoImpl();

    public MainFrame() {
        this.setTitle("航班管理系统");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        // 列出所有航班信息
        showFlightMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                ResultSet resultSet = flightDao.showFlights();
                table = new JTable(flightDao.buildTableModel(resultSet));
                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 0, 800, 600);
                panel.removeAll();
                panel.add(scrollPane);
                panel.updateUI();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        // 按起飞时间查询
        searchByDepartureItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = JOptionPane.showInputDialog("请输入起飞时间(yyyy-MM-dd):");
                Date date1 = Date.valueOf(date);
                ResultSet resultSet = flightDao.queryFlightByDeparture(date1);
                table = new JTable(flightDao.buildTableModel(resultSet));
                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 0, 800, 600);
                panel.removeAll();
                panel.add(scrollPane);
                panel.updateUI();
            }
        });

        // 按目的地查询
        searchByDestinationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = JOptionPane.showInputDialog("请输入目的地:");
                ResultSet resultSet = flightDao.queryFlightByDestination(destination);
                table = new JTable(flightDao.buildTableModel(resultSet));
                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 0, 800, 600);
                panel.removeAll();
                panel.add(scrollPane);
                panel.updateUI();
            }
        });

        // 删除航班信息
        deleteFlightMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                try{
                    String number = JOptionPane.showInputDialog("请输入要删除的航班编号:");
                    int num = Integer.parseInt(number);
                    int result = JOptionPane.showConfirmDialog(null, "是否删除该航班信息？", "删除航班信息", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        boolean flag = flightDao.deleteFlightByNum(num);
                        if (flag) {
                            JOptionPane.showMessageDialog(null, "删除成功！");
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败！");
                        }
                    } else {
                        return;
                    }
                }catch (Exception e1) {

                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        // 修改航班信息
        updateFlightMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                // 可以多个输入框
                try{
                    String number = JOptionPane.showInputDialog("请输入要修改的航班编号:");
                    int num = Integer.parseInt(number);
                    String id = JOptionPane.showInputDialog("请输入航班号:");

                    String destination = JOptionPane.showInputDialog("请输入目的地:");

                    String date = JOptionPane.showInputDialog("请输入起飞时间(yyyy-MM-dd):");
                    Date departure = Date.valueOf(date);
                    FlightInfo flight = new FlightInfo(num, id, destination, departure);
                    int result = JOptionPane.showConfirmDialog(null, "是否修改该航班信息？", "修改航班信息", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        boolean flag = flightDao.updateFlightByNum(flight);
                        if (flag) {
                            JOptionPane.showMessageDialog(null, "修改成功！");
                        } else {
                            JOptionPane.showMessageDialog(null, "修改失败！");
                        }
                    } else {
                        return;
                    }
                }catch (Exception e1){

                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        // 退出系统
        exitMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否退出系统？", "退出系统", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    return;
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        searchFlightMenu.add(searchByDepartureItem);
        searchFlightMenu.add(searchByDestinationItem);
//        menuBar.add(addFlightMenu);
        menuBar.add(showFlightMenu);
        menuBar.add(searchFlightMenu);
        menuBar.add(deleteFlightMenu);
        menuBar.add(updateFlightMenu);
        menuBar.add(exitMenu);

        panel = new JPanel();
        panel.setBounds(0, 0, 800, 600);
        // 让panel布满整个窗口
        panel.setLayout(null);
        this.add(panel);
        this.setJMenuBar(menuBar);
    }
//
//    public static void main(String[] args) {
//        new MainFrame();
//    }
}
