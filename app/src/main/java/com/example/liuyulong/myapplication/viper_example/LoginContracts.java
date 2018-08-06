package com.example.liuyulong.myapplication.viper_example;

class LoginContracts {

    interface View {

        void showError();

    }

    interface Presenter {

        void onDestroy();

        void onLoginButtonPressed();

    }


    interface Interactor {

        void login();

    }


    interface InteractorOutput {

        void onLoginSuccess();

        void onLoginError();

    }

    interface Router {

        void goToHomeScreen();
    }
}
