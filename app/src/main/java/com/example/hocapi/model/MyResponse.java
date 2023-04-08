package com.example.hocapi.model;

public class MyResponse {
    private String current_user_url;

    public MyResponse(String current_user_url) {
        this.current_user_url = current_user_url;
    }

    public String getCurrent_user_url() {
        return current_user_url;
    }

    public void setCurrent_user_url(String current_user_url) {
        this.current_user_url = current_user_url;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "current_user_url='" + current_user_url + '\'' +
                '}';
    }
}
