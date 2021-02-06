package view;

import javax.swing.*;


public class Frame extends JFrame {
    private JPanel panel;
    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    public Frame(JPanel panel) {
        this.panel = panel;
        setTitle("정류장");
        setBounds(100, 100, WIDTH, HEIGHT);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
