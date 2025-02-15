package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    // конструктор со всеми параметрами
    public Courier(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // конструктор без параметров
    public Courier(){

    }

    // геттеры
    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    // cеттеры
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Методы для генерации объектов
    public static Courier random() {
        return new Courier(RandomStringUtils.random(10), RandomStringUtils.random(5), RandomStringUtils.random(10));
    }

    public static Courier basic() {
        return new Courier("FirstCourier", "09876", "First");
    }

    public static Courier invalid() {
        return new Courier(RandomStringUtils.random(10), RandomStringUtils.random(10), RandomStringUtils.random(10));
    }
}
