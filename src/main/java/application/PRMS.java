package application;
import database.*;
import presentation.View;

public class PRMS {
    public static void main(String [] args){
        View view = new View();
        DatabaseModel model = new DatabaseModel();

        Controller controller = new Controller(model,view);

        view.setVisible(true);
    }
}
