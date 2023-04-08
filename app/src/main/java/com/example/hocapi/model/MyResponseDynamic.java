package com.example.hocapi.model;

public class MyResponseDynamic {
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public MyResponseDynamic(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "MyResponseDynamic{" +
                "login='" + login + '\'' +
                '}';
    }
}
