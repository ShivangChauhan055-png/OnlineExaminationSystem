package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamFrame extends JFrame implements ActionListener {

    // ===== UI Components =====
    JLabel titleLabel, questionLabel, qnoLabel, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup optionsGroup;
    JButton nextButton, submitButton;
    JToggleButton darkModeToggle;

    // ===== Exam Data =====

    // ===== Exam Data (TOP LEVEL JAVA + OOPS) =====
    String[] questions = {
            "Java is which type of language?",
            "Which keyword is used for inheritance?",
            "Which of these is NOT an OOP principle?",
            "Which concept allows method overriding?",
            "Which keyword is used to achieve abstraction?",
            "Which of the following supports multiple inheritance in Java?",
            "What is runtime polymorphism?",
            "Which keyword is used to prevent inheritance?",
            "Which method is called automatically when object is created?",
            "Which collection does NOT allow duplicate elements?"
    };

    String[][] options = {
            {"Procedural", "Object Oriented", "Functional", "Scripting"},
            {"this", "super", "extends", "implements"},
            {"Encapsulation", "Inheritance", "Compilation", "Polymorphism"},
            {"Encapsulation", "Abstraction", "Polymorphism", "Inheritance"},
            {"abstract", "final", "static", "private"},
            {"Class", "Abstract Class", "Interface", "Constructor"},
            {"Method Overloading", "Method Overriding", "Constructor Chaining", "Encapsulation"},
            {"static", "final", "private", "protected"},
            {"start()", "main()", "constructor", "run()"},
            {"List", "Set", "ArrayList", "Vector"}
    };

    // Correct option index (0-based)
    int[] answers = {
            1, // Object Oriented
            2, // extends
            2, // Compilation
            2, // Polymorphism
            0, // abstract
            2, // Interface
            1, // Method Overriding
            1, // final
            2, // constructor
            1  // Set
    };


    int index = 0;
    int score = 0;

    // ===== Timer =====
    Timer timer;
    int timeLeft = 60;

    // ===== Dark Mode =====
    boolean darkMode = false;

    public ExamFrame() {

        // ===== Frame =====
        setTitle("Online Examination System - Exam");
        setSize(820, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));

        // ===== Dark Mode Toggle =====
        darkModeToggle = new JToggleButton("🌙 Dark Mode");
        darkModeToggle.setBounds(640, 20, 150, 30);
        darkModeToggle.setFocusPainted(false);
        add(darkModeToggle);

        darkModeToggle.addActionListener(e -> toggleDarkMode());

        // ===== Title =====
        titleLabel = new JLabel("Online Examination");
        titleLabel.setBounds(260, 20, 350, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(titleLabel);

        // ===== Timer =====
        timerLabel = new JLabel("Time Left: 60 sec");
        timerLabel.setBounds(60, 70, 200, 25);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        // ===== Question No =====
        qnoLabel = new JLabel();
        qnoLabel.setBounds(60, 100, 100, 25);
        qnoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(qnoLabel);

        // ===== Question =====
        questionLabel = new JLabel();
        questionLabel.setBounds(60, 130, 700, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        // ===== Options =====
        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        JRadioButton[] opts = {opt1, opt2, opt3, opt4};
        int y = 180;

        for (JRadioButton opt : opts) {
            opt.setBounds(80, y, 650, 30);
            opt.setFont(new Font("Arial", Font.PLAIN, 15));
            opt.setBackground(getContentPane().getBackground());
            add(opt);
            y += 40;
        }

        optionsGroup = new ButtonGroup();
        optionsGroup.add(opt1);
        optionsGroup.add(opt2);
        optionsGroup.add(opt3);
        optionsGroup.add(opt4);

        // ===== Buttons =====
        nextButton = new JButton("Next");
        nextButton.setBounds(260, 380, 130, 42);
        styleButton(nextButton, new Color(46, 204, 113));
        nextButton.setEnabled(false);
        add(nextButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(420, 380, 130, 42);
        styleButton(submitButton, new Color(231, 76, 60));
        add(submitButton);

        nextButton.addActionListener(this);
        submitButton.addActionListener(this);

        // ===== Option Listener (SAFE) =====
        ActionListener optionListener = e -> {
            if (index < questions.length - 1) {
                nextButton.setEnabled(true);
            }
        };

        opt1.addActionListener(optionListener);
        opt2.addActionListener(optionListener);
        opt3.addActionListener(optionListener);
        opt4.addActionListener(optionListener);

        // ===== Load First Question =====
        loadQuestion();

        // ===== Timer Start =====
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " sec");

            if (timeLeft <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "⏰ Time Over!");
                new ResultFrame(score);
                dispose();
            }
        });
        timer.start();

        setVisible(true);
    }

    // ===== Load Question =====
    void loadQuestion() {

        qnoLabel.setText("Q" + (index + 1) + ".");
        questionLabel.setText(questions[index]);

        opt1.setText(options[index][0]);
        opt2.setText(options[index][1]);
        opt3.setText(options[index][2]);
        opt4.setText(options[index][3]);

        optionsGroup.clearSelection();

        // NEXT LOGIC
        if (index < questions.length - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(false); // last question
        }
    }

    // ===== Answer Check =====
    boolean checkAnswer() {
        int selected = -1;
        if (opt1.isSelected()) selected = 0;
        else if (opt2.isSelected()) selected = 1;
        else if (opt3.isSelected()) selected = 2;
        else if (opt4.isSelected()) selected = 3;

        return selected == answers[index];
    }

    // ===== Button Clicks =====
    @Override
    public void actionPerformed(ActionEvent e) {

        if (checkAnswer()) {
            score += 10;
        }

        if (e.getSource() == nextButton) {
            if (index < questions.length - 1) {
                index++;
                loadQuestion();
            }
            return;
        }

        if (e.getSource() == submitButton) {
            timer.stop();
            new ResultFrame(score);
            dispose();
        }
    }

    // ===== Dark Mode =====
    void toggleDarkMode() {
        darkMode = !darkMode;

        Color bg = darkMode ? new Color(34, 34, 34) : new Color(245, 247, 250);
        Color fg = darkMode ? Color.WHITE : Color.BLACK;

        getContentPane().setBackground(bg);

        titleLabel.setForeground(fg);
        questionLabel.setForeground(fg);
        qnoLabel.setForeground(fg);
        timerLabel.setForeground(darkMode ? Color.ORANGE : Color.RED);

        opt1.setBackground(bg);
        opt2.setBackground(bg);
        opt3.setBackground(bg);
        opt4.setBackground(bg);

        opt1.setForeground(fg);
        opt2.setForeground(fg);
        opt3.setForeground(fg);
        opt4.setForeground(fg);
    }

    // ===== Button Style =====
    void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
    }
}
