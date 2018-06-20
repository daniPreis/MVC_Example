package com.example.dani.mvc_example.Model;

public interface IUser {

    String getName();
    String getPasswd();
    int checkUserValidity(String name, String passwd);

}
