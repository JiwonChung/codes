package com.company.service.user;

import com.company.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlUserService implements UserService {
    SqlUserRepository repository = new SqlUserRepository();

    /**
     * 다　생각이　있었으니　건들지　말것　
     * -1을　리턴하면　실패한거임
     * @param user
     * @return
     */
    @Override
    public long join(User user) {
        try {
            repository.save(user);
            return user.getId();
        } catch (NullPointerException e) {
            return -1L;
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> searchByName(String name) {
        return repository.searchByName(name);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    private class SqlUserRepository {
        String url = "jdbc:mysql://1.235.203.128:3306/chatting?serverTimezone=Asia/Seoul";
        private final String id = "forChatting";
        private final String password = "!`tPZ(K{`$gr*9@x";
        Connection connection;
        Statement statement;
        public SqlUserRepository() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, id, password);
                statement = connection.createStatement();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }



        /**
         * 회원가입　통과
         */

        private User save(User user) {
            try {
                validateDuplicateUser(user);

                statement.execute("insert into users(name, password, joindatetime)" +
                        "values ('" + user.getName() + "', '" + user.getPassword() + "'," +
                        "current_timestamp());");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            return user;
        }


        /**
         * 아이디로　유저　반환하기　성공
         * @param id
         * @return
         */
        private Optional<User> findById(Long id) {
            try {
                ResultSet resultSet = statement.executeQuery("select * from users where _id = '" + id + "';");
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(id);
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setDate(resultSet.getTimestamp("joindatetime"));
                    return Optional.of(user);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return Optional.empty();
        }

        private List<User> searchByName(String name) {
            List<User> users = new ArrayList<>();
            try {
                ResultSet resultSet = statement.executeQuery(
                        "select * from users where name like '%" + name + "%';"
                );
                userSetter(users, resultSet);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return users;
        }

        private void userSetter(List<User> users, ResultSet resultSet) throws SQLException {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setDate(resultSet.getTimestamp("joindatetime"));
                users.add(user);
            }
        }

        private Optional<User> findByName(String name) {
            try {
                ResultSet resultSet = statement.executeQuery("select * from users where name = '" + name + "';");
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("_id"));
                    user.setName(name);
                    user.setPassword(resultSet.getString("password"));
                    user.setDate(resultSet.getTimestamp("joindatetime"));
                    return Optional.of(user);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return Optional.empty();
        }


        private List<User> findAll() {
            List<User> users = new ArrayList<>();
            try {
                ResultSet resultSet = statement.executeQuery("select * from users;");
                userSetter(users, resultSet);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return users;
        }

        /**
         * 너도　통과
         */
        private void validateDuplicateUser(User user) throws SQLException {
            // 중복검사를　하여야　합니다．
            ResultSet resultSet = statement.executeQuery("select name from users where name = '" + user.getName() + "';");
            if (resultSet.next()) {
                throw new IllegalStateException("이미　존재하는　계정입니다．　");
            }
        }
    }
}
