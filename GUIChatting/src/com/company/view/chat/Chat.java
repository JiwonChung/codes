package com.company.view.chat;

import com.company.domain.User;
import com.company.service.message.MessageService;
import com.company.service.message.SqlMessageService;
import com.company.service.user.UserService;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Chat extends JFrame {
    UserService userService;
    MessageService messageService;

    public Chat(UserService userService, User sender, User receiver) {
        this.userService = userService;
        messageService = new SqlMessageService();
        setBounds(800, 10, 500, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GUI a = new GUI(sender, receiver, userService, messageService);
        setContentPane(a);
        setResizable(false);
        setTitle(receiver.getName());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
                              @Override
                              public void windowClosed(WindowEvent e) {
                                  a.killReadAndUpdate();
                              }
                          }
        );
        setVisible(true);
    }
}
