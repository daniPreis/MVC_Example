package com.example.dani.mvc_example;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.dani.mvc_example.Controller.ILoginController;
import com.example.dani.mvc_example.Controller.LoginController;
import com.example.dani.mvc_example.Model.IUserListener;
import com.example.dani.mvc_example.Model.User;
import com.example.dani.mvc_example.View.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener{

    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private Button btnClear;
    private ILoginController loginController;
    private ProgressBar progressBar;
    private User user;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler = new Handler(Looper.getMainLooper());

        //find view
        editUser = (EditText) this.findViewById(R.id.et_login_username);
        editPass = (EditText) this.findViewById(R.id.et_login_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login_login);
        btnClear = (Button) this.findViewById(R.id.btn_login_clear);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_login);

        user = new User("","");
        //set listener
        user.setIUserListener(new IUserListener() {
            @Override
            public void onAction(int val) {
                final Boolean isLoginSuccess;
                if(val != 0)isLoginSuccess =false;
                else isLoginSuccess = true;
                final Boolean result = isLoginSuccess;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoginResult(isLoginSuccess);
                    }
                }, 5000);
            }
        });
        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        //init
        loginController = new LoginController(this);
        loginController.setProgressBarVisiblity(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_clear:
                loginController.clear();
                break;
            case R.id.btn_login_login:
                loginController.setProgressBarVisiblity(View.VISIBLE);
                btnLogin.setEnabled(false);
                btnClear.setEnabled(false);
                loginController.doLogin(editUser.getText().toString(),
                        editPass.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText() {
        editUser.setText("");
        editPass.setText("");
    }

    //@Override
    public void onLoginResult(Boolean result) {
        loginController.setProgressBarVisiblity(View.INVISIBLE);
        btnLogin.setEnabled(true);
        btnClear.setEnabled(true);
        if (result) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
