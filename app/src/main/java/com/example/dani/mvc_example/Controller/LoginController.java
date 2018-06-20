package com.example.dani.mvc_example.Controller;

import android.os.Handler;
import android.os.Looper;

import com.example.dani.mvc_example.Model.IUser;
import com.example.dani.mvc_example.Model.User;
import com.example.dani.mvc_example.View.ILoginView;

public class LoginController implements ILoginController {

    ILoginView iLoginView;
    IUser user;
    Handler handler;

    public LoginController(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(name, passwd);
        if (code != 0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result);
            }
        }, 5000);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }

    private void initUser() {
        user = new User("mvc", "mvc");
    }


}
