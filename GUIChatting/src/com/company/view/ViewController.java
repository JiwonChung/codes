package com.company.view;

import com.company.domain.User;
import com.company.service.user.SqlUserService;
import com.company.service.user.UserService;
import com.company.view.searchname.SearchName;
import com.company.view.signin.SignIn;

public class ViewController {
    public static void main(String[] args) {
        UserService userService = new SqlUserService();
        new SignIn(userService);
//        User test = new User();
//        test.setName("jiwon");
//        test.setId(2L);
//        test.setPassword("password");
//        new SearchName(userService, test);
    }
}
