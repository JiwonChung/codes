package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        makeAChatObject("08:28:11 발신자 20221 수신자 석준 고(DM) : https://ko.omatomeloanhikaku.com/how-to-create-polls-in-zoom-meetings-4376\n" +
                "08:29:00 발신자 20221 수신자 석준 고(DM) : 참고 : (줌에 투표 툴 있어요~!!)\n" +
                "08:29:10 발신자 20221 수신자 석준 고(DM) : 알겠습니다.\n" +
                "08:49:20 발신자 20221 수신자 석준 고(DM) : 2\n");
    }


    static List<AChat> makeAChatObject(String message) {


        List<AChat> returnValue = new ArrayList<>();

        String[] split = message.split("\n");

        for (String s : split) {
//            System.out.println(s);
            AChat aChat = new AChat();



            /**
             * text 뽑기
             */
            String text = s.substring(s.lastIndexOf("수신자 석준 고(DM) : ") + 15); // 15 : str.length
            System.out.println("text : " + text);
            // text 객체에 넣어주기
            aChat.setText(text);

            /**
             * 발신자 뽑기
             */
            String sender = s.substring(s.lastIndexOf("발신자") + 4).split(" ")[0];
            System.out.println("sender : " + sender);
            // sender 객체에 넣어주기
            aChat.setText(sender);


            System.out.println("-------------------------");
            returnValue.add(aChat);
        }

        return returnValue;

    }

    static class AChat {
        private String caller;
        private String receiver;
        private String text;

        public String getCaller() {
            return caller;
        }

        public void setCaller(String caller) {
            this.caller = caller;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
