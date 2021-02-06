package com.company;

import javax.swing.*;
import java.awt.*;

public class GUILogin extends JFrame {

    String id;
    String password;
    public GUILogin() {
        setBounds(1920 / 2 - 150, 1080 / 2 - 100, 300, 200);
        setContentPane(new GUILogin2());
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class GUILogin2 extends JPanel {
        JLabel text_id;
        JTextField textField_id;

        JLabel text_pw;
        JPasswordField textField_pw;

        JButton button_GO;

        public void init() {
            setLayout(new FlowLayout(FlowLayout.RIGHT));
            text_id = new JLabel("ID: ");
            text_id.setFont(new Font("궁서", Font.BOLD, 30));

            textField_id = new JTextField();
            textField_id.setFont(new Font("궁서", Font.ITALIC, 25));
            textField_id.setColumns(8);

            text_pw = new JLabel("PW: ");
            text_pw.setFont(new Font("궁서", Font.BOLD, 30));

            textField_pw = new JPasswordField();
            textField_pw.setFont(new Font("궁서", Font.ITALIC, 25));
            textField_pw.setColumns(8);


            button_GO = new JButton("GO");
            button_GO.setFont(new Font("궁서", Font.BOLD, 25));
            button_GO.setLayout(new FlowLayout(FlowLayout.CENTER));

            button_GO.addActionListener(e -> {
                whenButton_GO_Pressed();
            });
        }

        public void whenButton_GO_Pressed() {

            id = textField_id.getText();
            password = String.valueOf(textField_pw.getPassword());




            // if the id or the password were blank
            if (id.trim().equals("") || password.trim().equals("")) {
                new LoginSub(1);
                System.out.println("id: " + id);
                System.out.println("password: " + password);
            }

//            else if () {
//    // 데이터 베이스에 있는지 확인
//            }
        }

        // 알림창을 띄움
        private class LoginSub extends JFrame {
            public LoginSub(int errorCode) {
                JPanel newPanel = new JPanel();
                JLabel jLabel;
                JButton button = new JButton("확인");
                setContentPane(newPanel);
                setBounds(1920 / 2, 1080 / 2, 200, 100);
                setResizable(false);

                if (errorCode == 1) {
                    // 아이디 혹은 패스워드가 입력되어지지 않음
                    jLabel = new JLabel("아이디 혹은 패스워드가 입력되지 않았습니다. ");
                }

                else {
                    jLabel = new JLabel("로그인 성공");
                }

                jLabel.setFont(new Font("궁서", Font.PLAIN, 10));

                newPanel.add(jLabel);
                newPanel.add(button);

                setVisible(true);

                button.addActionListener(e -> {
                    setVisible(false);
                });
            }
        }



        public GUILogin2() {
            init();
            add(text_id);
            add(textField_id);
            add(text_pw);
            add(textField_pw);


            add(button_GO);
        }
    }
}
