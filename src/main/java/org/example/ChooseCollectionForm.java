package org.example;

import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChooseCollectionForm extends JFrame{
    private JPanel MainPanel;
    private JButton buildingsBtn;
    private JButton inhabitantsBtn;
    private JButton districtsBtn;

    public ChooseCollectionForm(String title, Users loggedUser) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuLoggedForm("Menu", loggedUser).setVisible(true);
            }
        });

        inhabitantsBtn.addActionListener(e -> addInhabitant(loggedUser));
        buildingsBtn.addActionListener(e -> addBuilding(loggedUser));
        districtsBtn.addActionListener(e -> addDistrict(loggedUser));
    }

    private void addDistrict(Users loggedUser) {
        this.dispose();
        new AddDistrictForm("Dodaj dzielnicę", loggedUser).setVisible(true);
    }

    private void addBuilding(Users loggedUser) {
        this.dispose();
        new AddBuildingForm("Dodaj budynek", loggedUser).setVisible(true);
    }

    private void addInhabitant(Users loggedUser) {
        this.dispose();
        new AddInhabitantForm("Dodaj mieszkańca", loggedUser).setVisible(true);
    }
}
