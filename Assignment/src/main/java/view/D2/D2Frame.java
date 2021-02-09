package view.D2;

import javax.swing.*;


public class D2Frame extends JFrame {
    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    public D2Frame(JPanel panel) {
        setTitle("2D 공튀기기");
        setBounds(100, 100, WIDTH, HEIGHT);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
