package com.company.view.signup;

import com.company.view.lib.JGradientButton;
import com.company.view.lib.TextHint;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    Create create;

    JTextField textField_id = new JTextField();
    JTextField textField_pw = new JTextField();
    JTextField textField_pw_oneMoreTime = new JTextField();
    JButton button_create = new JGradientButton("Create",
            new Color(0, 62, 100),
            new Color(93, 18, 29));
    JButton goBack = new JGradientButton("I Already have one",
            new Color(0, 62, 100),
            new Color(93, 18, 29));

    com.company.controller.signup.Listener listener = new com.company.controller.signup.Listener(
            create,
            textField_id,
            textField_pw,
            textField_pw_oneMoreTime,
            button_create,
            goBack
    );

    GUI(Create create) {
        this.create = create;
        setBackground(new Color(149, 165, 166));
        setLayout(null);

        /**
         * textField ID
         */
        new TextHint(textField_id, "ID");
        textField_id.setBounds(50, 50, 250, 35);
        textField_id.setFont(new Font("TimeRoman", Font.ITALIC, 20));
        textField_id.setFocusTraversalKeysEnabled(false);
        textField_id.addKeyListener(
                listener.new SignUpTextField_idKeyListener()
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
                listener.new SignUpTextField_pwKeyListener()
        );
        add(textField_pw);

        /**
         * TextField password one more time
         */
        new TextHint(textField_pw_oneMoreTime, "CONFIRM");
        textField_pw_oneMoreTime.setBounds(50, 150, 250, 35);
        textField_pw_oneMoreTime.setFont(new Font("TimeRoman", Font.ITALIC, 20));
        textField_pw_oneMoreTime.setFocusTraversalKeysEnabled(false);
        textField_pw_oneMoreTime.addKeyListener(
                listener.new SignUpTextField_pw_oneMoreTimeKeyListener()
        );
        add(textField_pw_oneMoreTime);


        /**
         * button create
         */
        button_create.setBounds(320, 50, 85, 85);
        button_create.setForeground(Color.WHITE);
        button_create.setFont(new Font("TimeRoman", Font.BOLD, 15));
        button_create.addActionListener(
                listener.new SignUpButton_createActionListener()
                );
        add(button_create);


        /**
         * go back
         */
        goBack.setBounds(50, 200, 250, 35);
        goBack.setFont(new Font("TimeRoman", Font.BOLD, 20));
        goBack.setForeground(Color.WHITE);
        goBack.addActionListener(
                listener.new SignUpGoBackActionListener()
        );
        add(goBack);
    }

}

