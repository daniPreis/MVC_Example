package com.example.dani.mvc_example.Model;

public class User implements IUser {

    String name;
    String passwd;
    IUserListener userListener;

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
        this.userListener = null;
    }

    public void setIUserListener(IUserListener listener) {
        this.userListener = listener;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getPasswd() {
        return passwd;
    }
    @Override
    public int checkUserValidity(String name, String passwd) {
        if (name == null || passwd == null || !name.equals(getName()) || !passwd.equals(getPasswd())) {
            return -1;
        }
        return 0;
    }

    public void changeValidity() {
        if (this.userListener != null) {
            userListener.onAction(1);
        }
    }

}
