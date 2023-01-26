package org.example;

import org.example.collections.district.Districts;
import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AddDistrictForm extends JFrame{
    private JPanel MainPanel;
    private JTextField nameTextField;
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
    private JButton addDataButton;

    public AddDistrictForm(String title, Users loggedUser) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        addDataButton.addActionListener(e -> addDistrict());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuLoggedForm("Menu", loggedUser).setVisible(true);
            }
        });


        hasParkCb.addItemListener(itemEvent -> {
            if(itemEvent.getItem().equals("Tak")) {
                activateParkFields();
            } else {
                deactivateParkFields();
            }
        });

    }

    private void addDistrict() {
        int lastId = Districts.getLastId().getDistrictId();
        Districts district = new Districts();
        district.setDistrictId(lastId + 1);
        district.setName(nameTextField.getText());
        district.setAreaInKmSquare(Integer.parseInt(areaTextField.getText()));
        if(hasParkCb.getSelectedIndex() == 0) {
            district.setHasPark(Boolean.TRUE);
            district.getParkInfo().setParkName(parkNameTextField.getText());
            district.getParkInfo().setNumberOfFountains(Integer.parseInt(fountainsNumberTextField.getText()));
            district.getParkInfo().setNumberOFBenches(Integer.parseInt(benchesNumberTextField.getText()));
            district.getParkInfo().setNumberOfEntrances(Integer.parseInt(entrancesNumberTextField.getText()));
            district.getParkInfo().setAreaInKmSquare(Double.parseDouble(parkAreaTextField.getText()));
        } else {
            district.setHasPark(Boolean.FALSE);
        }
        district.getNumberOfBuildings().setUtilityBuildings(Integer.parseInt(utilityBuildingsTextField.getText()));
        district.getNumberOfBuildings().setResidentialBuildings(Integer.parseInt(residentialBuildingsTextField.getText()));
        district.getNumberOfBuildings().setIndustrialBuildings(Integer.parseInt(industrialBuildingsTextField.getText()));
        district.getPublicTransport().setBusLines(Arrays.stream(busLinesTextField.getText().replace("[", "").replace(" ", "").replace("]", "").split(",")).toList().stream().map(Integer::parseInt).collect(Collectors.toList()));
        district.getPublicTransport().setTramLines(Arrays.stream(tramLinesTextField.getText().replace("[", "").replace(" ", "").replace("]", "").split(",")).toList().stream().map(Integer::parseInt).collect(Collectors.toList()));
        district.setNumberOfInhabitants(Integer.parseInt(inhabitantsNumberTextField.getText()));
        Districts.addDistrict(district);

        JOptionPane.showMessageDialog(this, "Pomyślnie dodano rekord", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
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
