package ATM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
class User{
    private final String name;      //用户姓名
    private String password;        //用户密码

    User(String name,String password) {     //构造方法
        this.name = name;
        this.password = password;
    }
    public String getName() {               //获取用户姓名
        return name;
    }
    public String getPassword() {           //获取用户密码
        return password;
    }
    public void setPassword(String password) {      //设置用户密码
        //密码不能小于6位且不能6位相同
        if(password.length() < 6 || password.equals("111111") || password.equals("222222") || password.equals("333333") || password.equals("444444") || password.equals("555555") || password.equals("666666") || password.equals("777777") || password.equals("888888") || password.equals("999999") || password.equals("000000")) {
            System.out.println("密码设置失败，密码不能小于6位且不能6位相同!");
        }else {
            this.password = password;
            System.out.println("密码设置成功!");
        }
    }
}
class People extends User{                       //定义一个People类
    private int money;              //用户余额
    private int attempt=0;          //用户密码输入错误次数
    private final String card;      //用户卡号
    //单日取款总数限制
    List<String> history = new ArrayList<String>();     //用户交易历史记录
    People(String name,String password,int money,String card) {     //构造方法
        super(name,password);
        this.money = money;
        this.card = card;
    }
    public int getMoney() {                 //获取用户余额
        return money;
    }
    public void setMoney(int money) {       //设置用户余额
        this.money = money;
    }
    public int getAttempt() {               //获取用户密码输入错误次数
        return attempt;
    }
    public void setAttempt(int attempt) {       //设置用户密码输入错误次数
        this.attempt = attempt;
    }
    public String getCard() {               //获取用户卡号
        return card;
    }
    public List<String> getHistory() {      //获取用户交易历史记录
        return history;
    }
    public void addHistory(String history) {    //添加用户交易历史记录
        this.history.add(history);
    }
}
class Admin extends User{
    private String ID;
    Admin(String name,String ID,String password) {
        super(name,password);
        this.ID = ID;

    }
    public String getID() {
        return ID;
    }
}
class ATM {
    List<People> peopleList = new ArrayList<People>();      //存储用户信息

    List<Admin> adminList = new ArrayList<Admin>();         //存储管理员信息
    public ATM(){
        //初始化用户信息
        peopleList.add(new People("张三","123456",100000,"123456789"));
        peopleList.add(new People("李四","123456",100000,"234567891"));
        peopleList.add(new People("王五","123456",100000,"345678921"));
        peopleList.add(new People("赵六","123456",100000,"456789321"));
        //初始化管理员信息
        adminList.add(new Admin("管理员1号","admin","admin"));
    }
    static ATM atm = new ATM();         //创建ATM对象
    public static ATM getInstance() {       //获取ATM对象，因为ATM只有一个，所以使用单例模式，
                                            // 只能通过getInstance()方法获取ATM对象
        return atm;
    }

    public void addPeople(People people) {    //添加用户，没有用到
        peopleList.add(people);
    }
    public void addAdmin(Admin admin) {         //添加管理员，没有用到
        adminList.add(admin);
    }
    public ArrayList<People> getPeopleList() {      //获取全部用户信息
        return (ArrayList<People>) peopleList;
    }
    public People login(String card,String password) {      //用户登录
        for(People people:peopleList) {
            if(people.getCard().equals(card)) {             //判断卡号是否正确
                if(people.getAttempt()>=3){                 //判断密码输入错误次数是否大于等于3
                    System.out.println("您的账户已被锁定，请联系管理员!");
                    return null;
                }
                if(people.getPassword().equals(password)) {    //判断密码是否正确
                    System.out.println("登录成功!");
                    people.setAttempt(0);
                    return people;
                }else {                                     //密码错误
                    people.setAttempt(people.getAttempt()+1);   //密码错误次数加1
                    if(people.getAttempt() == 3) {
                        System.out.println("密码错误次数过多，卡已被锁定,请联系管理员!");
                        return null;
                    }else {
                        System.out.println("密码错误，还有"+(3-people.getAttempt())+"次机会!");
                        return null;
                    }
                }
            }
        }
        System.out.println("卡号不存在!");
        return null;
    }
    public Admin adminLogin(String ID,String password) {        //管理员登录
        for(Admin admin:adminList) {
            if(admin.getID().equals(ID)) {                      //判断ID是否正确
                if(admin.getPassword().equals(password)) {      //判断密码是否正确
                    System.out.println("登录成功!");
                    return admin;
                }else {
                    System.out.println("密码错误!");
                    return null;
                }
            }
        }
        System.out.println("账号不存在!");
        return null;
    }
    public void changePassword(People people,String password) {    //修改密码
        people.setPassword(password);
    }
    public void changePassword(Admin admin,String password) {   //修改密码
        admin.setPassword(password);
    }
    public void saveMoney(People people,int money) {        //存钱
        if(money <= 0) {                                //判断存钱金额是否小于等于0
            System.out.println("存款金额必须大于0!");
            return;
        }
        if(money%100 != 0) {                            //判断存钱金额是否是100的倍数
            System.out.println("存款金额必须是100的整数倍!");
            return;
        }
        people.setMoney(people.getMoney()+money);       //存钱
        Date date = new Date();                        //获取当前时间
        //将时间信息格式化为yyyy-MM-dd HH:mm:ss的形式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将存钱信息写入历史记录
        people.addHistory(simpleDateFormat.format(date)+"存款"+money+"元.");
        System.out.println("存款成功!");
    }
    public void drawMoney(People people,int money) {
        if(money <= 0) {                            //判断取钱金额是否小于等于0
            System.out.println("取款金额必须大于0!");
            return;
        }
        if(money%100 != 0) {                        //判断取钱金额是否是100的倍数
            System.out.println("取款金额必须是100的整数倍!");
            return;
        }
        if(money > 5000){                               //判断取钱金额是否大于5000
            System.out.println("单笔取款金额不能超过5000!");
            return;
        }
        if(people.getMoney() < money) {                 //判断取钱金额是否大于余额
            System.out.println("余额不足!");
            return;
        }
        people.setMoney(people.getMoney()-money);       //取钱
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        people.addHistory(simpleDateFormat.format(date)+"取款"+money+"元.");
        System.out.println("取款成功!");
    }
    public void transferMoney(People people,String card,int money) {
        if(money <= 0) {
            System.out.println("转账金额必须大于0!");
            return;
        }
        if(money%100 != 0) {
            System.out.println("转账金额必须是100的整数倍!");
            return;
        }
        if(dayTransfer(people) + money > 50000) {       //判断当天转账金额是否大于50000
            System.out.println("单日转账金额不能超过50000,您今日已转账"+dayTransfer(people)+"元!");
            return;
        }
        if(people.getMoney() < money) {
            System.out.println("余额不足!");
            return;
        }
        for(People people1:peopleList) {
            if(people1.getCard().equals(card)) {            //判断输入的转账卡号是否存在
                people.setMoney(people.getMoney()-money);   //转出
                people1.setMoney(people1.getMoney()+money); //转入
                //将转账信息写入历史记录
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //转出记录
                people.addHistory(simpleDateFormat.format(date)+"转账"+money+"元给"+people1.getName());
                //转入记录
                people1.addHistory(simpleDateFormat.format(date)+people.getName()+"转账"+money+"元.");
                System.out.println("转账成功!");
                return;
            }
        }
        System.out.println("转账失败，卡号不存在!");
    }

    private int dayTransfer(People people) {
        if (people.getHistory().size() == 0) {          //判断历史记录是否为空
            return 0;
        }
        int dayMoney = 0;                               //统计当天转账金额
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = simpleDateFormat.format(date);
        //遍历历史记录
        for(String history:people.getHistory()) {
            if(history.contains(day) && history.contains("转账")) {   //判断是否是当天的转账记录
                //获取转账金额
                //因为转账记录的格式为:yyyy-MM-dd HH:mm:ss转账xxx元给YYY
                //所以转账金额的位置为:yyyy-MM-dd HH:mm:ss转账xxx元给YYY中的xxx元
                //通过split方法将字符串以“转账”为分隔符分割成两个字符串“yyyy-MM-dd HH:mm:ss”和“xxx元给YYY”
                //再通过split方法将字符串以“元”为分隔符分割成两个字符串“xxx”和“给YYY”
                //最后将字符串xxx转换为int类型
                dayMoney += Integer.parseInt(history.split("转账")[1].split("元")[0]);
            }
        }
        return dayMoney;                            //返回当天转账金额
    }
}
public class ATMSystem {

    public void start() {                    //启动ATM系统
        System.out.println("欢迎使用ATM系统");
        System.out.println("  1.用户登录");
        System.out.println("  2.管理员登录");
        System.out.println("  3.退出");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的选择:");
        int choice = scanner.nextInt();     //获取用户输入的选项
        switch (choice) {
            case 1:                         //用户登录
                System.out.println("请输入卡号:");
                String card = scanner.next();
                System.out.println("请输入密码:");
                String password = scanner.next();
                People people = ATM.getInstance().login(card, password);    //调用ATM类的login方法
                //如果登录成功会返回一个People的用户对象，如果登录失败会返回null
                if (people != null) {       //如果登录成功，进入用户操作界面
                    userMenu(people);
                } else {                    //如果登录失败，重新选择
                    start();
                }
                break;
            case 2:
                System.out.println("请输入账号:");
                String ID = scanner.next();
                System.out.println("请输入密码:");
                String password1 = scanner.next();
                Admin admin = ATM.getInstance().adminLogin(ID, password1);
                if (admin != null) {        //如果登录成功，进入管理员操作界面
                    adminMenu(admin);
                } else {
                    start();
                }
                break;
            case 3:                         //退出系统
                System.out.println("感谢使用ATM系统!");
                System.exit(0);
                break;
            default:
                System.out.println("输入错误，请重新输入!");
                start();
                break;
        }
    }

    private void adminMenu(Admin admin) {               //管理员操作界面
        System.out.println("欢迎管理员" + admin.getName() + "登录!");
        System.out.println("    1.修改密码");
        System.out.println("    2.查询用户历史记录");
        System.out.println("    3.查询全部用户历史记录");
        System.out.println("    4.解锁用户");
        System.out.println("    5.退出");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的选择:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("请输入原始密码:");
                String oldPassword = scanner.next();
                if (admin.getPassword().equals(oldPassword)) {      //判断原始密码是否正确
                    System.out.println("请输入新密码:");
                    String newPassword = scanner.next();
                    System.out.println("请再次输入新密码:");
                    String newPassword1 = scanner.next();
                    if (newPassword.equals(newPassword1)) {         //判断两次输入的新密码是否一致
                        ATM.getInstance().changePassword(admin, newPassword);   //调用ATM类的changePassword方法
                        adminMenu(admin);
                    } else {
                        System.out.println("两次输入的密码不一致!");
                        adminMenu(admin);
                    }
                } else {
                    System.out.println("密码错误!");
                    adminMenu(admin);
                }
                break;
            case 2:
                System.out.println("请输入卡号:");                       //查询输入的卡号的用户历史记录
                String card = scanner.next();
                for (People people : ATM.getInstance().getPeopleList()) {       //遍历ATM类的peopleList集合
                    if (people.getCard().equals(card)) {
                        System.out.println("姓名：" + people.getName());
                        System.out.println("卡号：" + people.getCard());
                        System.out.println("历史记录：");
                        if (people.getHistory().size() == 0) {          //判断历史记录是否为空
                            System.out.println("无历史记录!");
                        } else {
                            for (String history : people.getHistory()) {    //遍历历史记录集合
                                System.out.println(history);
                            }
                        }
                        adminMenu(admin);                   //返回管理员操作界面
                        return;
                    }
                }
                System.out.println("卡号不存在!");
                adminMenu(admin);
                break;
            case 3:
                for (People people : ATM.getInstance().getPeopleList()) {
                    System.out.println("姓名：" + people.getName());
                    System.out.println("卡号：" + people.getCard());
                    System.out.println("历史记录：");
                    if (people.getHistory().size() == 0) {
                        System.out.println("无历史记录!");
                    } else {
                        for (String history : people.getHistory()) {
                            System.out.println(history);
                        }
                    }
                }
                adminMenu(admin);
                break;
            case 4:
                System.out.println("请输入卡号:");
                String card1 = scanner.next();
                for (People people : ATM.getInstance().getPeopleList()) {       //遍历ATM类的peopleList集合
                    if (people.getCard().equals(card1)) {
                        people.setAttempt(0);                   //将用户的尝试次数设置为0
                        System.out.println("解锁成功!");
                        adminMenu(admin);
                        return;
                    }
                }
                System.out.println("卡号不存在!");
                adminMenu(admin);
                break;
            case 5:
                System.out.println("感谢使用ATM系统!");
                start();
                break;
            default:
                System.out.println("输入错误，请重新输入!");
                adminMenu(admin);
                break;
        }
    }


    public void userMenu(People people) {
        System.out.println("欢迎" + people.getName() + "使用ATM系统!");   //用户操作界面
        System.out.println("    1.查询余额");
        System.out.println("    2.存款");
        System.out.println("    3.取款");
        System.out.println("    4.转账");
        System.out.println("    5.修改密码");
        System.out.println("    6.查询历史记录");
        System.out.println("    7.退出");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的选择:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("您的余额为:" + people.getMoney());   //查询余额
                userMenu(people);
                break;
            case 2:
                System.out.println("请输入存款金额:");
                int money = scanner.nextInt();
                ATM.getInstance().saveMoney(people, money);         //调用ATM类的saveMoney方法
                userMenu(people);
                break;
            case 3:
                System.out.println("请输入取款金额:");
                int money1 = scanner.nextInt();
                ATM.getInstance().drawMoney(people, money1);        //调用ATM类的drawMoney方法
                userMenu(people);
                break;
            case 4:
                System.out.println("请输入转账卡号:");
                String card = scanner.next();
                System.out.println("请输入转账金额:");
                int money2 = scanner.nextInt();
                ATM.getInstance().transferMoney(people, card, money2);  //调用ATM类的transferMoney方法
                userMenu(people);
                break;
            case 5:
                System.out.println("请输入原密码:");
                String password = scanner.next();
                if (password.equals(people.getPassword())) {
                    System.out.println("请输入新密码:");
                    String newPassword = scanner.next();
                    System.out.println("请再次输入新密码:");
                    String newPassword1 = scanner.next();
                    if (newPassword.equals(newPassword1)) {
                        ATM.getInstance().changePassword(people, newPassword);  //调用ATM类的changePassword方法
                        userMenu(people);
                    } else {
                        System.out.println("两次密码不一致!");
                        userMenu(people);
                    }
                } else {
                    System.out.println("密码错误!");
                    userMenu(people);
                }
                break;

            case 6:
                System.out.println("历史记录：");
                if (people.getHistory().size() == 0) {
                    System.out.println("无历史记录!");
                } else {
                    for (String history : people.getHistory()) {        //遍历历史记录集合
                        System.out.println(history);
                    }
                }
                userMenu(people);
                break;
            case 7:
                System.out.println("感谢使用ATM系统!");
                start();
                break;
            default:
                System.out.println("输入错误，请重新输入!");
                userMenu(people);
                break;
        }
    }

    public static void main(String[] args) {        //主方法
        new ATMSystem().start();                //调用start方法，开始运行程序
    }
}

