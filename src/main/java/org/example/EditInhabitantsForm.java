package org.example;

import org.example.collections.inhabitant.Inhabitants;

import javax.swing.*;
import java.awt.*;

public class EditInhabitantsForm extends JFrame{
    private JPanel MainPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JButton updateDataButton;
    private JTextField yearOfBirthTextField;
    private JTextField genderTextField;
    private JTextField titleTextField;
    private JTextField professionTextField;
    private JTextField buildingIdTextField;
    private JTextField districtTextField;
    private Integer fieldId;

    public EditInhabitantsForm(String title, Integer fieldId) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.fieldId = fieldId;

        assignDataToTextFields(this.fieldId);

    }

    void assignDataToTextFields(Integer fieldId) {
        Inhabitants chosenInhabitant = new Inhabitants().createInhabitantById(fieldId);
        firstNameTextField.setText(chosenInhabitant.getPersonalInfo().getFirstName());
        lastNameTextField.setText(chosenInhabitant.getPersonalInfo().getLastName());
        yearOfBirthTextField.setText(String.valueOf(chosenInhabitant.getPersonalInfo().getDateOfBirth()));
        genderTextField.setText(chosenInhabitant.getPersonalInfo().getGender());
        titleTextField.setText(chosenInhabitant.getEducation());
        professionTextField.setText(chosenInhabitant.getProfession());
        buildingIdTextField.setText(String.valueOf(chosenInhabitant.getBuildingId()));
        districtTextField.setText(String.valueOf(chosenInhabitant.getDistrictId()));
    }
}
