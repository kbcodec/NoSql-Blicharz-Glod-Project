package org.example;

import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;

public class MenuLoggedForm extends JFrame{
    private JPanel MainPanel;
    private JButton listBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton logOut;
    private Users loggedUser;

    public MenuLoggedForm(String title, Users loggedUser) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.loggedUser = loggedUser;
    }
}
