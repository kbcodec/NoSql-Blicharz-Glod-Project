package org.example;

import org.example.collections.building.Buildings;
import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddBuildingForm extends JFrame {
    private JPanel MainPanel;
    private JTextField nameTextField;
    private JTextField typeTextField;
    private JTextField yearOfBuildTextField;
    private JTextField condignationsTextField;
    private JComboBox hasBalconyCb;
    private JTextField wallColorTextField;
    private JTextField wallTypeTextField;
    private JButton addDataButton;

    public AddBuildingForm(String title, Users loggedUser) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        addDataButton.addActionListener(e -> addBuilding());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuLoggedForm("Menu", loggedUser).setVisible(true);
            }
        });
    }

    private void addBuilding() {
        int lastId = Buildings.getLastId().getBuildingId();
        Buildings building = new Buildings();
        building.setBuildingId(lastId+1);
        building.setName(nameTextField.getText());
        building.setType(typeTextField.getText());
        building.getArchitecture().setCondignations(Integer.parseInt(condignationsTextField.getText()));
        if(hasBalconyCb.getSelectedIndex() == 0) {
            building.getArchitecture().setHasBalcony(Boolean.TRUE);
        } else {
            building.getArchitecture().setHasBalcony(Boolean.FALSE);
        }
        building.getArchitecture().setWallColor(wallColorTextField.getText());
        building.getArchitecture().setWallType(wallTypeTextField.getText());
        building.setYear(Integer.parseInt(yearOfBuildTextField.getText()));
        Buildings.addBuilding(building);

        JOptionPane.showMessageDialog(this, "Pomyślnie dodano rekord", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
    }
}
