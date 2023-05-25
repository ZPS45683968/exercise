package flightmanageGUI.GUIview;

import org.junit.runners.BlockJUnit4ClassRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @Author ps_zhao
 * @Description 登录界面
 */
public class Login extends JFrame {
    private JLabel usernameLabel = new JLabel("用户名:");
    private JLabel passwordLabel = new JLabel("密  码:");
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("登录");
    private JButton CancelButton = new JButton("取消");

    public Login() {
        this.setTitle("登录");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        usernameLabel.setBounds(50, 30, 50, 20);
        passwordLabel.setBounds(50, 70, 50, 20);
        usernameField.setBounds(110, 30, 120, 20);
        passwordField.setBounds(110, 70, 120, 20);
        loginButton.setBounds(50, 110, 80, 20);
        CancelButton.setBounds(150, 110, 80, 20);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (username.equals("admin") && password.equals("123456")) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    new MainFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误");
                }
            }
        });

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(usernameField);
        this.add(passwordField);
        this.add(loginButton);
        this.add(CancelButton);
    }

//
//    public static void main(String[] args) {
//        new Login();
//    }
}
