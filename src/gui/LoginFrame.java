package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("Online Examination System");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Main Panel =====
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));
        setContentPane(panel);   //  IMPORTANT

        // ===== Heading =====
        JLabel heading = new JLabel("Student Login");
        heading.setBounds(170, 30, 200, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(new Color(44, 62, 80));
        panel.add(heading);

        // ===== Username =====
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(120, 100, 100, 25);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(220, 100, 160, 30);
        panel.add(usernameField);

        // ===== Password =====
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(120, 150, 100, 25);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(220, 150, 160, 30);
        panel.add(passwordField);

        // ===== Login Button =====
        loginButton = new JButton("Login");
        loginButton.setBounds(210, 215, 120, 38);

//  IMPORTANT FIXES FOR macOS
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);          //  MUST
        loginButton.setContentAreaFilled(true); //  MUST

        panel.add(loginButton);

        loginButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("tanuj") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            new ExamFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }
}
