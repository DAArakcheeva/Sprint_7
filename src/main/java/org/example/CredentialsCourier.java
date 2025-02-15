package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CredentialsCourier {

    private String login;
    private String password;

    // конструктор с парметрами
    public CredentialsCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // конструктор без параметров
    public CredentialsCourier() {

    }

    // конструктор только с одним параметром login
    public CredentialsCourier(String login) {
        this.login = login;
    }

    public static CredentialsCourier from(Courier courier) {
        return new CredentialsCourier(courier.getLogin(), courier.getPassword());
    }


    // геттеры
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    // сеттеры
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // методы для генерации объектов
    public static CredentialsCourier basicCredentialsCourier() {
        return  new CredentialsCourier("ПервыйКурьер", "12345");
    }

    public static CredentialsCourier randomCredentialsCourier() {
        return new CredentialsCourier(RandomStringUtils.random(10), RandomStringUtils.random(5));
    }

    public static CredentialsCourier invalidParams() {
        return new CredentialsCourier(RandomStringUtils.random(10));
    }
}
