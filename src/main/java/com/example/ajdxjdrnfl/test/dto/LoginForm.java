package com.example.ajdxjdrnfl.test.dto;

public class LoginForm {
    String ID;
    String PWD;

    public String getID() {
        return ID;
    }

    public String getPwd() {
        return PWD;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public LoginForm(String ID, String PWD) {
        this.ID = ID;
        this.PWD = PWD;
    }
}
