package org.example;

import org.example.collections.building.Architecture;
import org.example.collections.building.Buildings;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;

public class EditBuildingsForm extends JFrame{
    private JPanel MainPanel;
    private JTextField nameTextField;
    private JTextField typeTextField;
    private JButton updateDataButton;
    private JComboBox hasBalconyCb;
    private JTextField yearOfBuildTextField;
    private JTextField condignationsTextField;
    private JTextField wallColorTextField;
    private JTextField wallTypeTextField;
    private Integer fieldId;
    private Buildings chosenBuilding;

    public EditBuildingsForm(String title, Integer fieldId) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.fieldId = fieldId;

        chosenBuilding = new Buildings().createBuildingById(this.fieldId);
        assignDataToTextFields(chosenBuilding);

        updateDataButton.addActionListener(e -> {
                    Boolean hasBalcony = Boolean.TRUE;
                    if(hasBalconyCb.getSelectedIndex() == 1) {
                        hasBalcony = Boolean.FALSE;
                    }
                    chosenBuilding.setArchitecture(new Architecture(Integer.parseInt(condignationsTextField.getText()), hasBalcony, wallTypeTextField.getText(), wallColorTextField.getText()));
                    chosenBuilding.setName(nameTextField.getText());
                    chosenBuilding.setType(typeTextField.getText());
                    chosenBuilding.setYear(Integer.parseInt(yearOfBuildTextField.getText()));
                    chosenBuilding.modifyBuildingInDatabase();
                    JOptionPane.showMessageDialog(this, "Udało się zmodyfikować budynek!", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
        );
    }

    void assignDataToTextFields(Buildings chosenBuilding) {
        nameTextField.setText(chosenBuilding.getName());
        typeTextField.setText(chosenBuilding.getType());
        yearOfBuildTextField.setText(String.valueOf(chosenBuilding.getYear()));
        hasBalconyCb.setSelectedIndex(chosenBuilding.getArchitecture().getHasBalcony() ? 0 : 1);
        condignationsTextField.setText(String.valueOf(chosenBuilding.getArchitecture().getCondignations()));
        wallColorTextField.setText(chosenBuilding.getArchitecture().getWallColor());
        wallTypeTextField.setText(chosenBuilding.getArchitecture().getWallType());
    }
}
