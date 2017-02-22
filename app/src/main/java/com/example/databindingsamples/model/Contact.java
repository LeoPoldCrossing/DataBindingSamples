package com.example.databindingsamples.model;

/**
 * Created by LeoPoldCrossing on 2017/2/14.
 */

public class Contact {
    private String name;
    private String phoneNum;
    private String email;
    public Contact(String name,String phoneNum,String email){
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
