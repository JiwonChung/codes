package com.company.view.signup;

import com.company.service.user.SqlUserService;
import com.company.service.user.UserService;
import com.company.view.lib.JGradientButton;
import com.company.view.signin.SignIn;

import javax.swing.*;
import java.awt.*;

public class Create extends JFrame {

    public UserService userService;
    public Create(UserService userService) {
        this.userService = userService;
        setBounds(SignIn.frame_x - 100, SignIn.frame_y - 100, SignIn.frame_width,SignIn.frame_height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Create new account");
        setContentPane(new GUI(this));
        setVisible(true);
    }

}
