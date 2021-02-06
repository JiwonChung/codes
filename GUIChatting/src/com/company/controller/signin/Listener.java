package com.company.controller.signin;

import com.company.domain.User;
import com.company.view.searchname.SearchName;
import com.company.view.signin.GUI;
import com.company.view.signin.SignIn;
import com.company.view.signup.Create;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Listener {
    SignIn signIn;
    JTextField textField_id;
    JTextField textField_pw;
    JButton button_SignIn;
    JButton button_create;

    public Listener(SignIn signIn, JTextField textField_id, JTextField textField_pw, JButton button_SignIn, JButton button_create) {
        this.signIn = signIn;
        this.textField_id = textField_id;
        this.textField_pw = textField_pw;
        this.button_SignIn = button_SignIn;
        this.button_create = button_create;
    }
    public class SignInTextField_idKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_TAB) {
                textField_pw.grabFocus();
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    public class SignInTextField_pwKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                button_SignIn.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    public class SignInButton_SignInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<User> users = signIn.userService.searchByName(textField_id.getText());
            users.stream()
                    .filter(
                            user -> user.getName().equals(textField_id.getText())
                                    && user.getPassword().equals(textField_pw.getText())
                    ).findAny()
                    .ifPresentOrElse(
                            user -> {
                                JOptionPane
                                        .showMessageDialog(
                                                null,
                                                "Sign in !!SUCCEED!!",
                                                "잘됐다는 창",
                                                JOptionPane.INFORMATION_MESSAGE);
                                signIn.dispose();
                                new SearchName(signIn.userService, user);
//                                        new Chat(this.signIn.userService);
                            },
                            () -> {
                                JOptionPane
                                        .showMessageDialog(
                                                null,
                                                "Can't find your ID or PASSWORD",
                                                "잘못됐다는 창",
                                                JOptionPane.WARNING_MESSAGE);
                                textField_id.setText("");
                                textField_pw.setText("");
                                textField_id.grabFocus();
                            }
                    );
        }
    }

    public class SignInButton_createActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new Create(signIn.userService);
        }
    }






}
