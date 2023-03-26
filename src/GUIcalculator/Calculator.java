package GUIcalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

        public Calculator() {
            super("计算器");               //设置窗口标题
            setLayout(new BorderLayout());      //设置窗口布局
            setSize(500, 500);            //设置窗口大小
            setLocation(300, 300);      //设置窗口位置

            setContentPane(new CalculatorPanel());  //设置窗口内容面板
            setVisible(true);                   //设置窗口可见
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗口关闭方式
        }

        public static void main(String[] arguments) {
            Calculator calc = new Calculator();  //创建计算器对象
        }
}
class CalculatorPanel extends JPanel {      // 定义计算器面板类

    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;

    public CalculatorPanel() {
        setLayout(new BorderLayout());      //设置面板布局
        setSize(500, 500);      //设置面板大小
        setLocation(300, 300);          //设置面板位置

        result = 0;                     //初始化结果
        lastCommand = "=";              //初始化上一次命令
        start = true;                   //初始化开始标志

        // add the display

        display = new JButton("0.");    //创建显示按钮
        display.setFont(new Font("宋体", Font.BOLD, 30));  //设置显示按钮字体
        display.setEnabled(false);          //设置显示按钮不可用
        add(display, "North");      //将显示按钮添加到面板北部

        ActionListener insert = new InsertAction(); //创建插入动作监听器对象
        ActionListener command = new CommandAction();   //创建命令动作监听器对象

        // add the buttons in a 4 x 4 grid

        panel = new JPanel();                       //创建面板对象
        panel.setSize(300, 300);            //设置面板大小
        panel.setFont(new Font("Arial", Font.BOLD, 30));    //设置面板字体
        panel.setLayout(new GridLayout(4, 4));    //设置面板布局
        Button [] button = new Button[16];          //创建按钮数组对象

        for (int i = 0; i < 10; i++) {              //循环创建数字按钮
            button[i] = new Button(i + "");
            button[i].setFont(new Font("Arial", Font.BOLD, 30));    //设置按钮字体
            button[i].addActionListener(insert);    //为按钮添加插入动作监听器
        }

        button[10] = new Button(".");        //创建小数点按钮
        button[10].setFont(new Font("Arial", Font.BOLD, 30));   //设置按钮字体
        button[10].addActionListener(insert);
        char [] commandChar = {'+', '-', '*', '/', '='};    //创建命令字符数组

        for (int i = 11; i < 16; i++) {             //循环创建命令按钮
            button[i] = new Button(commandChar[i - 11] + "");
            button[i].setFont(new Font("Arial", Font.BOLD, 30));
            button[i].addActionListener(command);
        }

        panel.add(button[7]);       //将按钮添加到面板
        panel.add(button[8]);
        panel.add(button[9]);
        panel.add(button[11]);
        panel.add(button[4]);
        panel.add(button[5]);
        panel.add(button[6]);
        panel.add(button[12]);
        panel.add(button[1]);
        panel.add(button[2]);
        panel.add(button[3]);
        panel.add(button[13]);
        panel.add(button[0]);
        panel.add(button[10]);
        panel.add(button[14]);
        panel.add(button[15]);
        add(panel, "Center");    //将面板添加到窗口中央
    }

    /**
     * This action inserts the button action string to the end of the display text.
     */
    private class InsertAction implements ActionListener {  //插入动作监听器类
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {                //如果开始标志为真
                display.setText("");    //清空显示按钮文本
                start = false;          //设置开始标志为假
            }
            display.setText(display.getText() + input); //将按钮文本添加到显示按钮文本
        }
    }

    /**
     * This action executes the command that the button action string denotes.
     */
    private class CommandAction implements ActionListener { //命令动作监听器类
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();      //获取按钮动作命令

            if (start) {    //如果开始标志为真
                if (command.equals("-")) {    //如果命令为减号
                    display.setText(command);   //设置显示按钮文本为减号
                    start = false;
                } else lastCommand = command;
            } else {
                calculate(Double.parseDouble(display.getText()));   //计算结果
                lastCommand = command;
                start = true;
            }
        }

        private void calculate(double parseDouble) {    //计算结果方法
            if (lastCommand.equals("+")) result += parseDouble;
            else if (lastCommand.equals("-")) result -= parseDouble;
            else if (lastCommand.equals("*")) result *= parseDouble;
            else if (lastCommand.equals("/")) result /= parseDouble;
            else if (lastCommand.equals("=")) result = parseDouble;
            display.setText("" + result);
        }
    }
}