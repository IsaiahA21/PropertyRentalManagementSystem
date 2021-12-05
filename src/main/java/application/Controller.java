package application;

import database.DatabaseModel;
import presentation.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    DatabaseModel model;
    View view;
    Controller(DatabaseModel model, View view){
        this.model = model;
        this.view = view;


        this.view.addButtonListener(new ButtonListener());

    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            //talk to model here to get results
            view.setTestField("hello there");
            System.out.println("button was pressed");
        }
    }
}
