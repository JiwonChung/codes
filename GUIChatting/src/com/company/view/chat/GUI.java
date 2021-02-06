package com.company.view.chat;

import com.company.controller.chat.Listener;
import com.company.domain.Message;
import com.company.domain.User;
import com.company.service.message.MessageService;
import com.company.service.user.UserService;
import com.company.view.lib.JGradientButton;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class GUI extends JPanel {
    User sender;
    User receiver;
    UserService userService;
    MessageService messageService;

    ReadAndUpdate readAndUpdate = new ReadAndUpdate();

    List<Message> messages = new Stack<>();

    JScrollPane tmp, tmp2;

    JTextArea textArea_top = new JTextArea();
    JTextArea textArea_bottom = new JTextArea();
    JButton button_send = new JGradientButton(
            "Send",
            new Color(0, 62, 100),
            new Color(93, 18, 29)
    );

    public GUI(User sender, User receiver, UserService userService, MessageService messageService) {
        Listener listener = new Listener(
                sender,
                receiver,
                userService,
                messageService,
                messages,
                tmp,
                tmp2,
                textArea_top,
                textArea_bottom,
                button_send
        );


        this.sender = sender;
        this.receiver = receiver;
        this.userService = userService;
        this.messageService = messageService;
        setBackground(new Color(149, 165, 166));
        setLayout(null);

        readAndUpdate.start();


        // 여기　고치기
        textArea_top.setFont(new Font("TimeRoman", Font.ITALIC, 18));
        textArea_top.setEditable(false);
        textArea_top.setLineWrap(true);
        tmp = new JScrollPane(textArea_top);
        tmp.setBounds(50, 50, 400, 500);
        add(tmp);


        textArea_bottom.setFont(new Font("TimeRoman", Font.ITALIC, 18));
        textArea_bottom.setEditable(true);
        textArea_bottom.setLineWrap(true);
        textArea_bottom.addKeyListener(
                listener.new ChatTextArea_bottomKeyListener()
        );

        tmp2 = new JScrollPane(textArea_bottom);
        tmp2.setBounds(50, 600, 300, 90);
        add(tmp2);


        button_send.setBounds(380, 600, 70, 90);
        button_send.setForeground(Color.WHITE);
        button_send.setFont(new Font("TimeRoman", Font.BOLD, 13));
        button_send.addActionListener(
                listener.new ChatButton_sendActionListener()
        );
        add(button_send);

    }


    private void update(List<Message> data) {
        List<Message> senderSent = new Stack<>();
        List<Message> receiverSent = new Stack<>();
        textArea_top.setText("");

        for (Message m: data) {
            if (m.getSender_id() == sender.getId()) {
                senderSent.add(m);
            } else {
                receiverSent.add(m);
            }
        }

        messages.addAll(senderSent);

        // 여기서　어레이인댁스　에러　뜨는데　처음이라　일단　넘기겠음
        if (receiverSent.get(0).getIndex() <= senderSent.get(0).getIndex()) {
            messages.add(0, receiverSent.get(0));
        }
        for (Message receiverSentMessage : receiverSent) {
            for (int j = 1; j < messages.size(); j++) {
                if (receiverSentMessage.getIndex() > messages.get(j - 1).getIndex()
                        && receiverSentMessage.getIndex() < messages.get(j).getIndex()) {
                    messages.add(j, receiverSentMessage);
                }
            }
            if (receiverSentMessage.getIndex() > messages.get(messages.size() - 1).getIndex()) {
                messages.add(receiverSentMessage);
            }
        }




        for (Message m : messages) {
            if (m.getSender_id() == sender.getId()) {
                if (m.isReadOrNot()) {
                    textArea_top.append("me: " + m.getText() + "   READ" + "\n");
                } else {
                    textArea_top.append("me: " + m.getText() + "   NOT" + "\n");
                }
            } else {
                textArea_top.append(receiver.getName() + " : " + m.getText() + "\n");
            }
        }


        tmp.getVerticalScrollBar().addAdjustmentListener(
                adjustmentEvent -> adjustmentEvent
                        .getAdjustable()
                        .setValue(adjustmentEvent.getAdjustable().getMaximum())
        );
//        JScrollBar sb = tmp.getVerticalScrollBar();
//        sb.setValue(sb.getMaximum());
//
//        System.out.println(sb.getValue() + " " +  sb.getMaximum());


        messages.clear();

    }

    private class ReadAndUpdate extends Thread {
        @Override
        public void run() {
            Long sender_id = sender.getId();
            Long receiver_id = receiver.getId();
            List<Message> old_data = messageService.lookup(sender_id, receiver_id);

            update(old_data);
            while (true) {
                // １００개씩만　가져오는　것은　어떨까？
                try {
                    List<Message> new_data = messageService.lookup(sender_id, receiver_id);
                    if (old_data.size() != new_data.size()) {
                        old_data = new_data;
                        update(new_data);
                    }
                    try {
                        ReadAndUpdate.sleep(100);
                    } catch (InterruptedException e) {
                        break;
                    }
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }

            }

        }
    }

    public void killReadAndUpdate() {
        readAndUpdate.interrupt();
    }
}
