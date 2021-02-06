package main;

/**
 * Main
 */
public class Main {

    class Person {
        String name;
        int age;
    }

    Main() {
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = p2;
        System.out.println(p2.equals(p1));
    }
    public static void main(String[] args) {
        Main main = new Main();
    }
}