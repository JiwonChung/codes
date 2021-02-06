package com.company;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * LastChristmas
 */
public class LastChristmas extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;




    int width;
    int height;

    Image backgoundImage;
    JButton btn_Send;
    JTextArea et_id;

    int btn_SendWidth;
    int btn_SendHeight;
    int btn_Send_x, btn_Send_y;


    int et_id_x, et_id_y;
    int et_idWidth, et_idHeight;




    public LastChristmas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println("위에것");
        setTitle("Chat");
        setLocation(300, 100);
        setSize(403, 690);
        setResizable(false);
        setContentPane(new I_gave_you());
        setVisible(true);
    }

    public void init() {

        width = 403;
        height = 690;

        try {
            backgoundImage = ImageIO.read(new File("C:\\Users\\jwchu\\Documents\\codes\\20201121\\src\\com\\company\\background.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // btn_Send init
        btn_SendWidth = width / 5;
        btn_SendHeight = height / 20;
        btn_Send_x = width - btn_SendWidth - 30;
        btn_Send_y = height - btn_SendHeight * 4;

        // et_id init
        et_idWidth = width - btn_SendWidth * 2;
        et_idHeight = btn_SendHeight * 2;
        et_id_x = 25;
        et_id_y = height - et_idHeight * 2;



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep();
            btn_Send.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class I_gave_you extends JPanel {

        private static final long serialVersionUID = 1L;




        public I_gave_you() {
            System.out.println("아래것");
            setLayout(null);
            init();


            btn_Send = new JButton("Send");
            btn_Send.setBounds(btn_Send_x, btn_Send_y, btn_SendWidth, btn_SendHeight);

            btn_Send.addActionListener(e -> {

                // 여기에서 send 구현

            });


            et_id = new JTextArea();
            et_id.setBounds(et_id_x, et_id_y, et_idWidth, et_idHeight);
            et_id.setFont(new Font("궁서", Font.ITALIC, et_idHeight / 4));
            et_id.setLineWrap(true);

        }



        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(backgoundImage, 0, 0, this);

            add(et_id);
            add(btn_Send);


        }

    }



    
    public static void main(String[] args) {

        new LastChristmas();

    }
}