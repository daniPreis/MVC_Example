package com.example.dani.mvc_example.Controller;

public interface ILoginController {

    //Für Kommunikation mit der LoginActivity im View. Diese Klasse wird vom LoginPresenterCompl implementiert
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);

}
