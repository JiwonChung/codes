package com.company.view.searchname;

import com.company.domain.User;
import com.company.service.user.UserService;
import com.company.view.signin.SignIn;

import javax.swing.*;

public class SearchName extends JFrame {
    UserService userService;

    public SearchName(UserService userService, User sender) {
        this.userService = userService;
        setBounds(SignIn.frame_x - 200, SignIn.frame_y + 100, SignIn.frame_width + 100, SignIn.frame_height + 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Search ID");
        setContentPane(new GUI(userService, sender));
        setResizable(false);
        setVisible(true);

    }
}
