package com.example.liuyulong.myapplication.viper_example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements LoginContracts.View {

    private LoginPresenter presenter = new LoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showError() {

    }

    public void onClick() {
        presenter.onLoginButtonPressed();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter = null;

        super.onDestroy();
    }

    class LoginPresenter<V extends LoginContracts.View> implements LoginContracts.Presenter, LoginContracts.InteractorOutput {

        V view;
        LoginRouter router;
        LoginInteractor interactor = new LoginInteractor(this);

        public LoginPresenter(V view) {
            this.view = view;
            this.router = new LoginRouter((LoginActivity) view);
        }

        @Override
        public void onDestroy() {
            view = null;
            interactor = null;
        }

        @Override
        public void onLoginButtonPressed() {
            interactor.login();
        }

        @Override
        public void onLoginSuccess() {
            router.goToHomeScreen();
        }

        @Override
        public void onLoginError() {
            view.showError();
        }
    }

    class LoginInteractor<I extends LoginContracts.InteractorOutput> implements LoginContracts.Interactor {

        I interactorOutput;

        public LoginInteractor(I interactorOutput) {
            this.interactorOutput = interactorOutput;
        }

        @Override
        public void login() {
            interactorOutput.onLoginSuccess();
            interactorOutput.onLoginError();
        }
    }

    class LoginRouter<A extends LoginActivity> implements LoginContracts.Router {

        A view;

        public LoginRouter(A view){
            this.view = view;
        }

        @Override
        public void goToHomeScreen() {
            Intent intent = new Intent(view, LoginActivity.class);
            view.startActivity(intent);
        }
    }
}
