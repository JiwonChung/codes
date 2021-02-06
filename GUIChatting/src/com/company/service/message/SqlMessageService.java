package com.company.service.message;

import com.company.domain.Message;
import com.company.domain.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlMessageService implements MessageService {

    SqlMessageRepository repository = new SqlMessageRepository();

    @Override
    public Message push(long sender_id, long receiver_id, String text) {
        Message message = new Message();
        message.setSender_id(sender_id);
        message.setReceiver_id(receiver_id);
        message.setText(text);
        return repository.save(message);
    }

    @Override
    public List<Message> lookup(long sender_id, long receiver_id) {
        repository.read(receiver_id, sender_id);

        List<Message> a = repository.findBySenderAndReceiver(sender_id, receiver_id);
        a.addAll(repository.findBySenderAndReceiver(receiver_id, sender_id));
        return a;

    }

    @Override
    public List<Message> search(User user1, User user2, String text) {
        return repository
                .findByTextAndSenderAndReceiver(text, user1.getId(), user2.getId());
    }

    private static class SqlMessageRepository {
        String url = "jdbc:mysql://1.235.203.128:3306/chatting?serverTimezone=Asia/Seoul";
        private final String id = "forChatting";
        private final String password = "!`tPZ(K{`$gr*9@x";
        Connection connection;
        Statement statement;

        private SqlMessageRepository() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, id, password);
                statement = connection.createStatement();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * please set sender_id, receiver_id, and text before call save()
         */

        private Message save(Message message) {

            try {

                statement.execute("insert into messages (sender_id, receiver_id, transmitted_time, text)" +
                        "values ('" + message.getSender_id() + "', '" + message.getReceiver_id()
                        + "', current_timestamp() , '" + message.getText() + "');");
                ResultSet resultSet = statement.executeQuery(
                        "select * from messages " +
                                "where messages.index = " +
                                "(select max(messages.index) from messages);"
                );

                if (resultSet.next()) {
                    message.setIndex(resultSet.getLong("index"));
                    message.setTransmitted_time(resultSet.getTimestamp("transmitted_time"));
                }

                message.setReadOrNot(false);
                return message;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return null;

        }


        private Optional<Message> findByIndex(long index) {
            try {
                ResultSet resultSet = statement.executeQuery(
                        "select * from messages " +
                                "where messages.index = '" + index + "';"
                );
                Message message = new Message();
                if (resultSet.next()) {
                    setMessageObject(message, resultSet);
                }
                return Optional.of(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return Optional.empty();
        }




        private List<Message> findBySenderAndReceiver(long sender_id, long receiver_id) {
            try {
                ResultSet resultSet = statement.executeQuery(
                        "select * from messages "
                                + "where sender_id = '" + sender_id
                                + "' and receiver_id = '" + receiver_id + "';"
                );

                List<Message> messages = new ArrayList<>();
                while (resultSet.next()) {
                    Message message = new Message();
                    setMessageObject(message, resultSet);
                    messages.add(message);
                }
                return messages;
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        /**
         *
         * @param text
         * @param sender_id
         * @param receiver_id
         * @return
         */

        private List<Message> findByTextAndSenderAndReceiver(String text, long sender_id, long receiver_id) {
            try {
                ResultSet resultSet = statement.executeQuery(
                        "select * from messages "
                                + "where sender_id = '" + sender_id
                                + "' and receiver_id = '" + receiver_id
                                + "' and text like '%" + text + "%';"
                );

                List<Message> messages = new ArrayList<>();
                while (resultSet.next()) {
                    Message message = new Message();
                    setMessageObject(message, resultSet);
                    messages.add(message);
                }
                return messages;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        /**
         * 데모버전이랑　좀　다릅니다．
         * return value is the same as findBySenderAndReceiver();
         * @param sender_id
         * @param receiver_id
         * @return
         */

        private List<Message> read(long sender_id, long receiver_id) {
            try {
                statement.execute(
                        "update messages " +
                                "set read_time = current_timestamp(), " +
                                "wasRead = 1 " +
                                "where wasRead = 0 " +
                                "and sender_id = '" + sender_id + "' " +
                                "and receiver_id = '" + receiver_id + "';"
                );

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return findBySenderAndReceiver(sender_id, receiver_id);
        }


        /**
         * private method
         * @param message
         * @param resultSet
         * @throws SQLException
         */
        private void setMessageObject(Message message, ResultSet resultSet) throws SQLException {
            message.setText(resultSet.getString("text"));
            message.setSender_id(resultSet.getLong("sender_id"));
            message.setReceiver_id(resultSet.getLong("receiver_id"));
            message.setIndex(resultSet.getLong("index"));
            message.setTransmitted_time(resultSet.getTimestamp("transmitted_time"));
            if (resultSet.getInt("wasRead") == 1) {
                message.setReadOrNot(true);
                message.setRead_time(resultSet.getTimestamp("read_time"));
            }
        }
    }

}
