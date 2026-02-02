package gui;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {

    public ResultFrame(int score) {

        setTitle("Exam Result");
        setSize(420, 280);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel heading = new JLabel("Result");
        heading.setBounds(170, 20, 200, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading);

        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setBounds(140, 70, 200, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);

        JLabel message = new JLabel();
        message.setBounds(80, 120, 300, 40);
        message.setFont(new Font("Arial", Font.PLAIN, 16));

        //  Emoji + Message logic
        if (score >= 20) {
            message.setText("🎉 Excellent! Great Job 😎");
        } else if (score >= 10) {
            message.setText("🙂 Good Effort! Keep Practicing");
        } else {
            message.setText("😢 Try Again! You can do better");
        }

        add(message);

        setVisible(true);

    }
}
