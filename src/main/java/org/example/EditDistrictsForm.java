package org.example;

import org.example.collections.district.Districts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditDistrictsForm extends JFrame {
    private JPanel MainPanel;
    private JTextField nameTextField;
    private JButton updateDataButton;
    private JTextField areaTextField;
    private JTextField utilityBuildingsTextField;
    private JTextField residentialBuildingsTextField;
    private JTextField industrialBuildingsTextField;
    private JComboBox hasParkCb;
    private JTextField parkNameTextField;
    private JTextField fountainsNumberTextField;
    private JTextField benchesNumberTextField;
    private JTextField entrancesNumberTextField;
    private JTextField parkAreaTextField;
    private JTextField busLinesTextField;
    private JTextField tramLinesTextField;
    private JTextField inhabitantsNumberTextField;
    private Integer fieldId;
    private Districts chosenDistrict;

    public EditDistrictsForm(String title, Integer fieldId) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.fieldId = fieldId;

        chosenDistrict = new Districts().createDistrictById(this.fieldId);
        assignDataToTextFields(chosenDistrict);

        hasParkCb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getItem().equals("Tak")) {
                    activateParkFields();
                } else {
                    deactivateParkFields();
                }
            }
        });
    }

    void assignDataToTextFields(Districts chosenDistrict) {
        nameTextField.setText(chosenDistrict.getName());
        areaTextField.setText(String.valueOf(chosenDistrict.getAreaInKmSquare()));
        utilityBuildingsTextField.setText(String.valueOf(chosenDistrict.getNumberOfBuildings().getUtilityBuildings()));
        residentialBuildingsTextField.setText(String.valueOf(chosenDistrict.getNumberOfBuildings().getResidentialBuildings()));
        industrialBuildingsTextField.setText(String.valueOf(chosenDistrict.getNumberOfBuildings().getIndustrialBuildings()));
        hasParkCb.setSelectedIndex(chosenDistrict.getHasPark() ? 0 : 1);
        if(chosenDistrict.getHasPark()) {
            parkNameTextField.setText(chosenDistrict.getParkInfo().getParkName());
            fountainsNumberTextField.setText(String.valueOf(chosenDistrict.getParkInfo().getNumberOfFountains()));
            benchesNumberTextField.setText(String.valueOf(chosenDistrict.getParkInfo().getNumberOFBenches()));
            entrancesNumberTextField.setText(String.valueOf(chosenDistrict.getParkInfo().getNumberOfEntrances()));
            parkAreaTextField.setText(String.valueOf(chosenDistrict.getParkInfo().getAreaInKmSquare()));
        } else {
            deactivateParkFields();
        }
        busLinesTextField.setText(chosenDistrict.getPublicTransport().getBusLines().toString());
        tramLinesTextField.setText(chosenDistrict.getPublicTransport().getTramLines().toString());
        inhabitantsNumberTextField.setText(String.valueOf(chosenDistrict.getNumberOfInhabitants()));

    }
    void deactivateParkFields() {
        parkNameTextField.setEnabled(false);
        fountainsNumberTextField.setEnabled(false);
        benchesNumberTextField.setEnabled(false);
        entrancesNumberTextField.setEnabled(false);
        parkAreaTextField.setEnabled(false);
    }
    void activateParkFields() {
        parkNameTextField.setEnabled(true);
        fountainsNumberTextField.setEnabled(true);
        benchesNumberTextField.setEnabled(true);
        entrancesNumberTextField.setEnabled(true);
        parkAreaTextField.setEnabled(true);
    }
}
