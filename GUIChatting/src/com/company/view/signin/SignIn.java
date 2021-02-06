package com.company.view.signin;

import com.company.service.user.UserService;

import javax.swing.*;

public class SignIn extends JFrame {
    public UserService userService;
    /**
     * frame bounds setup
     */

    public static final int frame_x = 1200;
    public static final int frame_y = 100;
    public static final int frame_width = 450;
    public static final int frame_height = 300;


    public SignIn(UserService userService) {
        this.userService = userService;
        setResizable(false);
        setTitle("SignIn");
        setBounds(frame_x, frame_y, frame_width, frame_height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new GUI(this));
        setVisible(true);
    }

}


