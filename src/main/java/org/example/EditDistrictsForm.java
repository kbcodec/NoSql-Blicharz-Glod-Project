package org.example;

import org.example.collections.district.Districts;
import org.example.collections.inhabitant.PersonalInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        hasParkCb.addItemListener(itemEvent -> {
            if(itemEvent.getItem().equals("Tak")) {
                activateParkFields();
            } else {
                deactivateParkFields();
            }
        });

        updateDataButton.addActionListener(e -> {
                    chosenDistrict.setName(nameTextField.getText());
                    chosenDistrict.setAreaInKmSquare(Integer.parseInt(areaTextField.getText()));
                    chosenDistrict.getNumberOfBuildings().setUtilityBuildings(Integer.parseInt(utilityBuildingsTextField.getText()));
                    chosenDistrict.getNumberOfBuildings().setResidentialBuildings(Integer.parseInt(residentialBuildingsTextField.getText()));
                    chosenDistrict.getNumberOfBuildings().setIndustrialBuildings(Integer.parseInt(industrialBuildingsTextField.getText()));
                    if(hasParkCb.getSelectedIndex() == 0) {
                        chosenDistrict.setHasPark(Boolean.TRUE);
                        chosenDistrict.getParkInfo().setParkName(parkNameTextField.getText());
                        chosenDistrict.getParkInfo().setNumberOfFountains(Integer.parseInt(fountainsNumberTextField.getText()));
                        chosenDistrict.getParkInfo().setNumberOFBenches(Integer.parseInt(benchesNumberTextField.getText()));
                        chosenDistrict.getParkInfo().setNumberOfEntrances(Integer.parseInt(entrancesNumberTextField.getText()));
                        chosenDistrict.getParkInfo().setAreaInKmSquare(Double.parseDouble(parkAreaTextField.getText()));
                    } else {
                        chosenDistrict.setHasPark(Boolean.FALSE);
                    }
                    chosenDistrict.getPublicTransport().setBusLines(Arrays.stream(busLinesTextField.getText().replace("[", "").replace(" ", "").replace("]", "").split(",")).toList().stream().map(Integer::parseInt).collect(Collectors.toList()));
                    chosenDistrict.getPublicTransport().setTramLines(Arrays.stream(tramLinesTextField.getText().replace("[", "").replace(" ", "").replace("]", "").split(",")).toList().stream().map(Integer::parseInt).collect(Collectors.toList()));
                    chosenDistrict.setNumberOfInhabitants(Integer.parseInt(inhabitantsNumberTextField.getText()));
                    chosenDistrict.modifyDistrictInDatabase();

                    JOptionPane.showMessageDialog(this, "Udało się zmodyfikować użytkownika!", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
        );
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
        parkNameTextField.setText("");
        fountainsNumberTextField.setEnabled(true);
        fountainsNumberTextField.setText("");
        benchesNumberTextField.setEnabled(true);
        benchesNumberTextField.setText("");
        entrancesNumberTextField.setEnabled(true);
        entrancesNumberTextField.setText("");
        parkAreaTextField.setEnabled(true);
        parkAreaTextField.setText("");
    }
}
