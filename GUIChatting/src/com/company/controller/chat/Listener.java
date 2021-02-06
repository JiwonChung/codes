package com.company.controller.chat;

import com.company.domain.Message;
import com.company.domain.User;
import com.company.service.message.MessageService;
import com.company.service.user.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class Listener {
    User sender;
    User receiver;
    UserService userService;
    MessageService messageService;
    List<Message> messages;
    JScrollPane tmp, tmp2;
    JTextArea textArea_top;
    JTextArea textArea_bottom;
    JButton button_send;

    public Listener(User sender, User receiver, UserService userService, MessageService messageService, List<Message> messages, JScrollPane tmp, JScrollPane tmp2, JTextArea textArea_top, JTextArea textArea_bottom, JButton button_send) {
        this.sender = sender;
        this.receiver = receiver;
        this.userService = userService;
        this.messageService = messageService;
        this.messages = messages;
        this.tmp = tmp;
        this.tmp2 = tmp2;
        this.textArea_top = textArea_top;
        this.textArea_bottom = textArea_bottom;
        this.button_send = button_send;
    }

    public class ChatTextArea_bottomKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                button_send.doClick();
            }
        }
    }

    public class ChatButton_sendActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String text;
            if ((!(text = textArea_bottom.getText()).isEmpty())) {
                text = text.replace("'", "\'");
                text = text.replace("\\", "\\");
                text = text.replaceAll("\n", "");
                System.out.println(text);
                messageService.push(sender.getId(), receiver.getId(), text);
                textArea_bottom.setText("");
            }
        }
    }


}
