package org.example;

import org.example.collections.building.Buildings;
import org.example.collections.inhabitant.Inhabitants;
import org.example.collections.user.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ShowBuildingsForm extends JFrame {
    private JPanel MainPanel;
    private JComboBox inhabitantsCb;
    private JTable table;
    DefaultTableModel model = new DefaultTableModel();
    private Users loggedUser;

    public ShowBuildingsForm(String title, Users loggedUser) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.loggedUser = loggedUser;

        String[] buildingHeader = new String[] {"ID", "Nazwa", "Typ", "Rodzaj ścian", "Kolor ścian", "Liczba kondygnacji", "Czy posiada balkon", "Rok budowy"};

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(loggedUser == null) {
                    new MenuForm("Menu").setVisible(true);
                } else {
                    new MenuLoggedForm("Menu", loggedUser).setVisible(true);
                }
            }
        });

        insertInhabitantsToComboBox();

        inhabitantsCb.addActionListener(e -> {
            fillTable(inhabitantsCb.getSelectedIndex(), buildingHeader);
        });

    }

    private void insertInhabitantsToComboBox() {
        List<Inhabitants> allInhabitants = new Inhabitants().getAllInhabitants();
        for (Inhabitants i : allInhabitants) {
            inhabitantsCb.addItem(i.getPersonalInfo().getFirstName() + " " + i.getPersonalInfo().getLastName());
        }
    }

    private void fillTable(int selectedIndex, String[] headers) {
        model = new DefaultTableModel(null, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        Buildings building = getBuildingsForInhabitant(selectedIndex);
        model.setRowCount(1);
//        String[] buildingHeader = new String[] {"ID", "Nazwa", "Typ", "Rodzaj ścian", "Kolor ścian", "Liczba kondygnacji", "Czy posiada balkon", "Rok budowy"};
        table.setValueAt(building.getBuildingId(), 0, 0);
        table.setValueAt(building.getName(), 0, 1);
        table.setValueAt(building.getType(), 0, 2);
        table.setValueAt(building.getArchitecture().getWallType(), 0, 3);
        table.setValueAt(building.getArchitecture().getWallColor(), 0, 4);
        table.setValueAt(building.getArchitecture().getCondignations(), 0, 5);
        table.setValueAt(building.getArchitecture().getHasBalcony() ? "Tak" : "Nie", 0, 6);
        table.setValueAt(building.getYear(), 0, 7);
    }

    private Buildings getBuildingsForInhabitant(int selectedIndex) {
        String name = (String) inhabitantsCb.getItemAt(selectedIndex);
        String[] nameSplited = name.split(" ");
        String firstName = nameSplited[0];
        String lastName = nameSplited[1];
        Inhabitants inhabitant = new Inhabitants().getInhabitantByName(firstName, lastName);
        return Buildings.getBuildingForInhabitant(inhabitant);
    }
}
