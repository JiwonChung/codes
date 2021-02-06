package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExJButton extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = -4265950146252517013L;

    JButton button1, button2, button3;

    int count = 0;

    public ExJButton() {
        ImageIcon imageIcon = new ImageIcon("background.png");

        button1 = new JButton("Button 1", imageIcon);
        button1.setVerticalTextPosition(AbstractButton.CENTER);
        button1.setHorizontalTextPosition(AbstractButton.LEFT);
        button1.setMnemonic('a');

        final JLabel label = new JLabel("Counter: ");

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText("Counter: " + count);

            }
            
        });

        button2 = new JButton("Button 2");
        button2.setMnemonic('b');
        button2.setActionCommand("disable");

        button3 = new JButton("Button3");
        button3.setMnemonic('c');
        button3.setActionCommand("enable");
        button3.setEnabled(false);

        button2.addActionListener(this);
        button3.addActionListener(this);


        add(button1);
        add(button2);
        add(button3);
        add(label);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("disable")) {
            button2.setEnabled(false);
            button3.setEnabled(true);

        } else {
            button2.setEnabled(true);
            button3.setEnabled(false);;
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("JFrame Example");
        

        jFrame.setDefaultCloseOperation(3);

        jFrame.getContentPane().add(new ExJButton());
        jFrame.pack();
        jFrame.setSize(300, 200);
        jFrame.setVisible(true);
    }
    
}
