package com.company.controller.signup;

import com.company.domain.User;
import com.company.view.signup.Create;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;

public class Listener {
    Create create;
    JTextField textField_id;
    JTextField textField_pw;
    JTextField textField_pw_oneMoreTime;
    JButton button_create;
    JButton goBack;

    public Listener(Create create, JTextField textField_id, JTextField textField_pw, JTextField textField_pw_oneMoreTime,JButton button_create, JButton goBack) {
        this.create = create;
        this.textField_id = textField_id;
        this.textField_pw = textField_pw;
        this.textField_pw_oneMoreTime = textField_pw_oneMoreTime;
        this.button_create = button_create;
        this.goBack = goBack;
    }

    public class SignUpTextField_idKeyListener implements KeyListener {

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

    public class SignUpTextField_pwKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_TAB) {
                textField_pw_oneMoreTime.grabFocus();
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    public class SignUpTextField_pw_oneMoreTimeKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_TAB) {
                button_create.grabFocus();
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    public class SignUpButton_createActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boolean isExist = textField_id.getText() != null
                    && textField_pw.getText() != null
                    && textField_pw_oneMoreTime.getText() != null;
            boolean equals = Objects.equals(textField_pw.getText(), textField_pw_oneMoreTime.getText());
            if (isExist) {
                if (equals) {
                    List<User> users = create.userService.searchByName(textField_id.getText());

                    users.stream()
                            .filter(
                                    user -> user.getName().equals(textField_id.getText())
                            ).findAny()
                            .ifPresentOrElse(
                                    user -> {
                                        // 이미　있으면
                                        JOptionPane
                                                .showMessageDialog(
                                                        null,
                                                        "The ID already exist",
                                                        "Something wrong",
                                                        JOptionPane.WARNING_MESSAGE);
                                    },
                                    () -> {
                                        // 없으면　
                                        User user = new User();
                                        user.setName(textField_id.getText());
                                        user.setPassword(textField_pw.getText());
                                        create.userService.join(user);
                                        JOptionPane
                                                .showMessageDialog(
                                                        null,
                                                        "Sign Up !!SUCCEED!!",
                                                        "잘됐다는 창",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                        //　회원가입창을　이제　꺼
                                        create.dispose();
                                    }
                            );
                } else {
                    JOptionPane
                            .showMessageDialog(
                                    null,
                                    "The password not correct",
                                    "Something wrong",
                                    JOptionPane.WARNING_MESSAGE
                            );
                }
            } else {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Please fill the form",
                                "Something wrong",
                                JOptionPane.WARNING_MESSAGE
                        );
            }
        }
    }

    public class SignUpGoBackActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            create.dispose();
        }
    }
}
