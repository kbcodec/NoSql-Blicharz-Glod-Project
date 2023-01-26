package org.example;

import org.example.collections.inhabitant.Inhabitants;

import javax.swing.*;
import java.awt.*;

public class AddInhabitantForm extends JFrame {
    private JPanel MainPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField yearOfBirthTextField;
    private JTextField genderTextField;
    private JTextField titleTextField;
    private JTextField professionTextField;
    private JTextField buildingIdTextField;
    private JTextField districtTextField;
    private JButton addDataButton;

    public AddInhabitantForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        addDataButton.addActionListener(e -> addInhibitant());
    }

    private void addInhibitant() {
        int lastId = Inhabitants.getLastId().getInhabitantId();
        Inhabitants inhabitant = new Inhabitants();
        inhabitant.setInhabitantId(lastId+1);
        inhabitant.getPersonalInfo().setFirstName(firstNameTextField.getText());
        inhabitant.getPersonalInfo().setLastName(lastNameTextField.getText());
        inhabitant.getPersonalInfo().setGender(genderTextField.getText());
        inhabitant.getPersonalInfo().setDateOfBirth(Integer.parseInt(yearOfBirthTextField.getText()));
        inhabitant.setEducation(titleTextField.getText());
        inhabitant.setProfession(professionTextField.getText());
        inhabitant.setDistrictId(Integer.parseInt(districtTextField.getText()));
        inhabitant.setBuildingId(Integer.parseInt(buildingIdTextField.getText()));
        Inhabitants.addInhabitant(inhabitant);
    }
}
