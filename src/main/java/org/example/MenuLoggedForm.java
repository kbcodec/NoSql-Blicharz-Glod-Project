package org.example;

import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;

public class MenuLoggedForm extends JFrame{
    private JPanel MainPanel;
    private JButton listBtn;
    private JButton addBtn;
    private JButton logOut;
    private JLabel menuTextLabel;
    private Users loggedUser;

    public MenuLoggedForm(String title, Users loggedUser) throws HeadlessException {

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("User: " + loggedUser.getLogin());
        this.loggedUser = loggedUser;

        listBtn.addActionListener(e -> openListForm());
        addBtn.addActionListener(e -> openAddForm());

        logOut.addActionListener(e -> logOut());

    }

    private void openAddForm() {
        this.dispose();
        new ChooseCollectionForm("Dodaj dane").setVisible(true);
    }

    private void openListForm() {
        this.dispose();
        new ListForm("Wyświetl dane", this.loggedUser).setVisible(true);

    }

    private void logOut() {
        this.dispose();
        new MenuForm("Menu").setVisible(true);
    }
}
