package com.example.My_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public String helloApi(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-2api")
    @ResponseBody
    public ArrayList<Hello> helloApi(@RequestParam("name") String name, @RequestParam("age") long age) {
        ArrayList<Hello> returnValue = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            returnValue.add(new Hello(name + i, age + i));
        }
        return returnValue;
    }

    static class Hello {
        private String name;
        private long age;

        public Hello(String name, long age) {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(long age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public long getAge() {
            return age;
        }
    }
}
