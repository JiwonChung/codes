package com.company.view.signin;

import com.company.view.lib.JGradientButton;
import com.company.view.lib.TextHint;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    SignIn signIn;
    JTextField textField_id = new JTextField();
    JTextField textField_pw = new JTextField();
    JButton button_signIn = new JGradientButton("Sign in",
            new Color(0, 62, 100),
            new Color(93, 18, 29));
    JButton button_create = new JGradientButton("Create",
            new Color(0, 62, 100),
            new Color(93, 18, 29));

    GUI(SignIn signIn) {
        this.signIn = signIn;
        setBackground(new Color(149, 165, 166));
        setLayout(null);
        com.company.controller.signin.Listener listener = new com.company.controller.signin.Listener(
                signIn,
                textField_id,
                textField_pw,
                button_signIn,
                button_create
        );

        /**
         * textField ID
         */
        new TextHint(textField_id, "ID");
        textField_id.setBounds(50, 50, 250, 35);
        textField_id.setFont(new Font("TimeRoman", Font.ITALIC, 20));
        textField_id.setFocusTraversalKeysEnabled(false);
        textField_id.addKeyListener(
                listener.new SignInTextField_idKeyListener()
        );
        add(textField_id);


        /**
         * TextField password
         */
        new TextHint(textField_pw, "PASSWORD");
        textField_pw.setBounds(50, 100, 250, 35);
        textField_pw.setFont(new Font("TimeRoman", Font.ITALIC, 20));
        textField_pw.setFocusTraversalKeysEnabled(false);
        textField_pw.addKeyListener(
                listener.new SignInTextField_pwKeyListener()
        );
        add(textField_pw);


        /**
         * button sign in
         */
        button_signIn.setBounds(320, 50, 85, 85);
        button_signIn.setForeground(Color.WHITE);
        button_signIn.setFont(new Font("TimeRoman", Font.BOLD, 15));
        button_signIn.addActionListener(
                listener.new SignInButton_SignInActionListener()
        );
        add(button_signIn);


        /**
         * button create
         */
        button_create.setBounds(320, 140, 85, 35);
        button_create.setFont(new Font("TimeRoman", Font.BOLD, 15));
        button_create.setForeground(Color.WHITE);
        button_create.addActionListener(listener.new SignInButton_createActionListener());
        add(button_create);
    }
}
