package org.example;

import javax.swing.*;
import java.awt.*;

public class actionMenuDialog extends JDialog{
    private JButton editFieldBtn;
    private JButton deleteFieldBtn;
    private JPanel MainPanel;
    private String actualCollection;
    private Integer fieldId;

    public actionMenuDialog(Frame owner, String title, String actualCollection, Integer fieldId) {
        super(owner, title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.actualCollection = actualCollection;
        this.fieldId = fieldId;

        if(actualCollection.equals("Inhabitants")) {
            editFieldBtn.addActionListener(e -> openEditInhabitantsFrame(this.fieldId));
        } else if (actualCollection.equals("Buildings")) {
            editFieldBtn.addActionListener(e -> openEditBuildingsFrame(this.fieldId));
        } else {
            editFieldBtn.addActionListener(e -> openEditDistrictsFrame(this.fieldId));
        }
    }

    private void openEditDistrictsFrame(Integer fieldId) {
    }

    private void openEditBuildingsFrame(Integer fieldId) {
    }

    private void openEditInhabitantsFrame(Integer fieldId) {
        this.dispose();
        new EditInhabitantsForm("Edytowanie", fieldId).setVisible(true);
    }
}
