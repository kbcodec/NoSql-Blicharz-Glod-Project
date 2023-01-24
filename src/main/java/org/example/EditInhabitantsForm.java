package org.example;

import org.example.collections.inhabitant.Inhabitants;
import org.example.collections.inhabitant.PersonalInfo;

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
    private Inhabitants chosenInhabitant;

    public EditInhabitantsForm(String title, Integer fieldId) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.fieldId = fieldId;

        chosenInhabitant = new Inhabitants().createInhabitantById(this.fieldId);

        assignDataToTextFields(chosenInhabitant);

        updateDataButton.addActionListener(e -> {
                    chosenInhabitant.setPersonalInfo(new PersonalInfo(firstNameTextField.getText(), lastNameTextField.getText(), Integer.parseInt(yearOfBirthTextField.getText()), genderTextField.getText()));
                    chosenInhabitant.setProfession(professionTextField.getText());
                    chosenInhabitant.setEducation(titleTextField.getText());
                    chosenInhabitant.setBuildingId(Integer.parseInt(buildingIdTextField.getText()));
                    chosenInhabitant.setDistrictId(Integer.parseInt(districtTextField.getText()));
                    chosenInhabitant.modifyInhabitantInDatabase();
                    JOptionPane.showMessageDialog(this, "Udało się zmodyfikować użytkownika!", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
        );

    }

    void assignDataToTextFields(Inhabitants chosenInhabitant) {
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
