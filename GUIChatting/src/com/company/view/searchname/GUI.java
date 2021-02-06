package com.company.view.searchname;

import com.company.controller.searchname.Listener;
import com.company.domain.User;
import com.company.service.user.UserService;
import com.company.view.chat.Chat;
import com.company.view.lib.JGradientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.List;

public class GUI extends JPanel {
    UserService userService;
    JTextField textField_search = new JTextField();
    JButton button_search = new JGradientButton("Search",
            new Color(0, 62, 100),
            new Color(93, 18, 29));
    JList<String> list_searchResult = new JList<>();
    JButton button_confirm = new JGradientButton("Let's go",
            new Color(0, 62, 100),
            new Color(93, 18, 29));
    User sender;
    User receiver = new User();

    public GUI(UserService userService, User sd) {
        this.sender = sd;
        this.userService = userService;
        Listener listener = new Listener(
                userService,
                textField_search,
                button_search,
                list_searchResult,
                button_confirm,
                sender,
                receiver
        );


        setBackground(new Color(149, 165, 166));
        setLayout(null);




        list_searchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // find result
        textField_search.setBounds(95, 10, 250, 35);
        textField_search.setFont(new Font("TimeRoman", Font.BOLD, 20));
        textField_search.addKeyListener(
                listener.new SearchNameTextField_searchKeyListener()
        );
        add(textField_search);

        button_search.setBounds(357, 10,  100, 35);
        button_search.setForeground(Color.WHITE);
        button_search.addActionListener(
                listener.new SearchNameButton_searchActionListener()
        );
        add(button_search);

        button_confirm.setBounds(200, 250, 150, 35);
        button_confirm.setForeground(Color.WHITE);
        button_confirm.setEnabled(!list_searchResult.isSelectionEmpty());
        button_confirm.addActionListener(
                listener.new SearchNameButton_conformActionListener()
        );
        add(button_confirm);


        list_searchResult.addListSelectionListener(
                listener.new SearchNameList_searchResultListSelectionListener()
        );

        list_searchResult.addKeyListener(
                listener.new SearchNameList_searchResultKeyListener()
        );



        JScrollPane pane = new JScrollPane(list_searchResult);
        pane.setBounds(95, 50, 362, 200);
        add(pane);
    }
}
