package com.company.service.message;

import com.company.domain.Message;
import com.company.domain.User;

import java.util.List;

public interface MessageService {

    /**
     * The input object message had to have sender_id, receiver_id, and text
     * @param sender_id
     * @param receiver_id
     * @param text
     * @return Message object
     */
    Message push(long sender_id, long receiver_id, String text);

    /**
     * Bidirectional return
     * @param sender_id
     * @param receiver_id
     * @return List＜message＞
     */
    List<Message> lookup(long sender_id, long receiver_id);

    /**
     * 양쪽　다에게서　검색
     * @param user1 conversation participant1　
     * @param user2 conversation participant2
     * @param text text you wanna search
     * @return List＜message＞
     */
    List<Message> search(User user1, User user2, String text);
}
