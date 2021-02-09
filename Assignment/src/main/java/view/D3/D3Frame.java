package view.D3;

import javax.swing.*;

public class D3Frame extends JFrame{
    public static int WIDTH = 1200;
    public static int HEIGHT = 850;
    public D3Frame(JPanel panel) {
        setTitle("3D 공튀기기");
        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
