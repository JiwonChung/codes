package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame {
// 클라이언트 입니다.

    public static JTextArea textArea;
    public int textArea_x, textArea_y;
    public int textAreaWidth, textAreaHeight;

    public static JScrollPane scrollPaneA;


    public static JTextArea textField;
    public int textField_x, textField_y;
    public int textFieldWidth, textFieldHeight;

    public static JScrollPane scrollPaneB;


    public GUI() {
        init();
        setVisible(true);
    }

    public void init() {
        textArea_x = 25;
        textArea_y = 25;
        textAreaWidth = 350;
        textAreaHeight = 350;
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBounds(textArea_x, textArea_y, textAreaWidth, textAreaHeight);
        scrollPaneA = new JScrollPane(textArea);

        textField_x = 25;
        textField_y = 400;
        textFieldWidth = 350;
        textFieldHeight = 100;
        textField = new JTextArea();
        textField.setLineWrap(true);
        textField.setBounds(textField_x, textField_y, textFieldWidth, textFieldHeight);
        scrollPaneB = new JScrollPane(textField);

        setResizable(false);
        setTitle("Chat");
        setBounds(1200, 100, 415, 600);
        setContentPane(new GUI2());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class Key implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().trim().equals("")) {
                textArea.append(textField.getText() + "\n");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().trim().equals("")) {
                textField.setText("");
            }
        }
    }
    static class GUI2 extends JPanel {
        public GUI2() {
            setLayout(null);
            add(textArea);
            add(textField);

            add(scrollPaneA);
            add(scrollPaneB);

            textField.addKeyListener(new Key());
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
