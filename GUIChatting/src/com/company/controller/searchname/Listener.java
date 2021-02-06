package com.company.controller.searchname;

import com.company.domain.User;
import com.company.service.user.UserService;
import com.company.view.chat.Chat;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Listener {
    UserService userService;
    JTextField textField_search;
    JButton button_search;
    JList<String> list_searchResult;
    JButton button_confirm;
    User sender;
    User receiver;

    public Listener(UserService userService, JTextField textField_search, JButton button_search, JList<String> list_searchResult, JButton button_confirm, User sender, User receiver) {
        this.userService = userService;
        this.textField_search = textField_search;
        this.button_search = button_search;
        this.list_searchResult = list_searchResult;
        this.button_confirm = button_confirm;
        this.sender = sender;
        this.receiver = receiver;
    }

    public class SearchNameTextField_searchKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                button_search.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    public class SearchNameButton_searchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<User> users = userService.searchByName(textField_search.getText());
            DefaultListModel<String> newModel = new DefaultListModel<>();
            for (User u : users) {
                newModel.addElement(u.getName());
            }
            list_searchResult.setModel(newModel);
        }
    }

    public class SearchNameButton_conformActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String selectedValue = list_searchResult.getSelectedValue();
            if (selectedValue.equals(sender.getName())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Can't send to your self",
                        "잘못됐다는　창",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                receiver.setName(selectedValue);
                List<User> users = userService.searchByName(selectedValue);
                for (User user: users) {
                    if (user.getName().equals(selectedValue)) {
                        receiver.setId(user.getId());
                        new Chat(userService, sender, receiver);
                    }
                }
            }
        }
    }

    public class SearchNameList_searchResultListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            button_confirm.setEnabled(true);
        }
    }

    public class SearchNameList_searchResultKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (!list_searchResult.isSelectionEmpty())
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    button_confirm.doClick();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }




}
