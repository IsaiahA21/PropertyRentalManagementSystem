package presentation;
import application.*;


import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame {
    private JLabel title = new JLabel("Property Rental Management System");
    private JButton button = new JButton();
    private JTextField testField = new JTextField(10);


    public View() {
        JPanel panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        panel.add(title);
        panel.add(button);
        panel.add(testField);


        this.add(panel); //add panel to frame (view is the JFrame)
    }

    public void addButtonListener(ActionListener listenForButtonPress){
        button.addActionListener(listenForButtonPress);
    }

    public void setTestField(String test){
        testField.setText(test);
    }


}
