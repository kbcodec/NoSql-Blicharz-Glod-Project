package org.example;

import javax.swing.*;
import java.awt.*;

public class ChooseCollectionForm extends JFrame{
    private JPanel MainPanel;
    private JButton buildingsBtn;
    private JButton inhabitantsBtn;
    private JButton districtsBtn;

    public ChooseCollectionForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        inhabitantsBtn.addActionListener(e -> addInhabitant());
        buildingsBtn.addActionListener(e -> addBuilding());
    }

    private void addBuilding() {
        this.dispose();
        new AddBuildingForm("Dodaj budynek").setVisible(true);
    }

    private void addInhabitant() {
        this.dispose();
        new AddInhabitantForm("Dodaj mieszkańca").setVisible(true);
    }
}
