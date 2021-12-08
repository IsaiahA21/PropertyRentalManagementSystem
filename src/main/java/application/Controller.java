package application;

import database.DatabaseModel;
import presentation.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller {
    DatabaseModel model;
    View view;
    Controller(DatabaseModel model, View view){
        this.model = model;
        this.view = view;

        view.getRegisterNewUserButton().addActionListener(new RegisterButton());
        view.getLoginButton().addActionListener(new LoginButton());


    }

    //for registration of an account
    public class RegisterButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            int accessLevel = view.getComboRegisterBox();
            String email = view.getEmailRegisterField();
            String password = view.getPasswordRegisterField();
            //System.out.println(accessLevel +" "+ email +" "+ password);
            if(model.addUser(accessLevel,email,password)){
                view.GoRMain();
            }else{
                view.throwError("That email is already tied to an account!");
            }


        }
    }

    //for logging in
    public class LoginButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsernameField();
            String password = view.getPasswordField();
            System.out.println(username+" "+password);
            if(model.login(username,password)){
                view.successfulLogin();
            }else{
                view.failedLogin();
                view.throwError("Login was unsuccessful!");
            }
        }
    }


   /* public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            //talk to model here to get results
            view.setTestField("hello there");
            System.out.println("button was pressed");
        }
    }
    public class loginButton implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            String email = "jacob@gmail.com";//we should retrieve from view
            String password = "password12";//we should also retrive from view

            model.setUser(email,password);
        }
    }
    public class skipLoginButton implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            model.unregistedLogin();
        }
    }*/
}
