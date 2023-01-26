package org.example;

import org.example.collections.building.Buildings;
import org.example.collections.district.Districts;
import org.example.collections.inhabitant.Inhabitants;
import org.example.collections.user.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListForm extends JFrame{
    private JPanel ListPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton buildingsBtn;
    private JButton districtsBtn;
    private JButton inhabitantsBtn;
    private JComboBox chooseColumn;
    private JTextField searchValue;
    DefaultTableModel model = new DefaultTableModel();
    private Users loggedUser;
    private String actualCollection;

    public ListForm(String title, Users loggedUser) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(ListPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.loggedUser = loggedUser;

        List<Buildings> buildings = new Buildings().getAllBuildings();
        List<Inhabitants> inhabitants = new Inhabitants().getAllInhabitants();
        List<Districts> districts = new Districts().getAllDistricts();
        String[] buildingHeader = new String[] {"ID", "Nazwa", "Typ", "Rodzaj ścian", "Kolor ścian", "Liczba kondygnacji", "Czy posiada balkon", "Rok budowy"};
        String[] inhabitantHeader = new String[] {"ID", "Imię", "Nazwisko", "Rok urodzenia", "Płeć", "Tytuł", "Profesja"};
        String[] districtHeader = new String[] {"ID", "Nazwa", "Powierzchnia w km2", "Budynki użytkowe", "Budynki mieszkalne", "Budynki przemysłowe", "Nazwa Parku", "Liczba ławek", "Liczba fontann", "Powierzchnia parku w km2", "Liczba wejść", "Linie autobusowe", "Linie tramwajowe", "Liczba mieszkańców"};

        //PROVIDE CLOSING WHOLE APPLICATION AFTER 'X' CLICKED, JUST SHOWING MENU FORM
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

        showBuildings(buildings, buildingHeader);
        actualCollection = "Buildings";
        assignChoseColumnValues(buildingHeader, 0);

        buildingsBtn.addActionListener(e -> {
            int lastSelectedIndex = chooseColumn.getSelectedIndex();
            if(lastSelectedIndex + 1 > buildingHeader.length) {
                lastSelectedIndex = 0;
            }
            if(searchValue.getText().equals("")) {
                showBuildings(buildings, buildingHeader);
            }
            else {
                List<Buildings> chosenBuildings = getChosenBuildings(chooseColumn, searchValue);
                showBuildings(chosenBuildings, buildingHeader);
            }
            actualCollection = "Buildings";
            assignChoseColumnValues(buildingHeader, lastSelectedIndex);
        });

        inhabitantsBtn.addActionListener(e -> {
            int lastSelectedIndex = chooseColumn.getSelectedIndex();
            if(lastSelectedIndex + 1 > inhabitantHeader.length) {
                lastSelectedIndex = 0;
            }
            if(searchValue.getText().equals("")) {
                showInhabitants(inhabitants, inhabitantHeader);
            } else {
                List<Inhabitants> chosenInhabitants = getChosenInhabitants(chooseColumn, searchValue);
                showInhabitants(chosenInhabitants, inhabitantHeader);
            }
            actualCollection = "Inhabitants";
            assignChoseColumnValues(inhabitantHeader, lastSelectedIndex);
        });

        districtsBtn.addActionListener(e -> {
            int lastSelectedIndex = chooseColumn.getSelectedIndex();
            if(lastSelectedIndex + 1 > districtHeader.length) {
                lastSelectedIndex = 0;
            }
            if(searchValue.getText().equals("")) {
                showDistricts(districts, districtHeader);
            } else {
                List<Districts> chosenDistricts = getChosenDistricts(chooseColumn, searchValue);
                showDistricts(chosenDistricts, districtHeader);
            }
            actualCollection = "Districts";
            assignChoseColumnValues(districtHeader, lastSelectedIndex);
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if(loggedUser != null && table.getSelectedRow() != -1) {
                if(!e.getValueIsAdjusting()) {
                    new actionMenuDialog(this, "Możliwe akcje na wybranym polu", actualCollection, (Integer) table.getValueAt(table.getSelectedRow(), 0)).setVisible(true);
                }
            }

        });



    }

    void showBuildings(List<Buildings> buildingsList, String[] headers) {
        model = new DefaultTableModel(null, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        fillBuildingList(buildingsList);
    }

    void showInhabitants(List<Inhabitants> inhabitantsList, String[] headers) {
        model = new DefaultTableModel(null, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        fillInhabitantList(inhabitantsList);
    }

    void showDistricts(List<Districts> districtsList, String[] headers) {
        model = new DefaultTableModel(null, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        fillDistrictList(districtsList);
    }

    void fillDistrictList(List<Districts> districtsList) {
        model.setRowCount(districtsList.size());
        for(int iter = 0; iter < districtsList.size(); iter++) {
            table.setValueAt(districtsList.get(iter).getDistrictId(), iter, 0);
            table.setValueAt(districtsList.get(iter).getName(), iter, 1);
            table.setValueAt(districtsList.get(iter).getAreaInKmSquare(), iter, 2);
            table.setValueAt(districtsList.get(iter).getNumberOfBuildings().getUtilityBuildings(), iter, 3);
            table.setValueAt(districtsList.get(iter).getNumberOfBuildings().getResidentialBuildings(), iter, 4);
            table.setValueAt(districtsList.get(iter).getNumberOfBuildings().getIndustrialBuildings(), iter, 5);
            if(districtsList.get(iter).getHasPark()) {
                table.setValueAt(districtsList.get(iter).getParkInfo().getParkName(), iter, 6);
                table.setValueAt(districtsList.get(iter).getParkInfo().getNumberOfFountains(), iter, 7);
                table.setValueAt(districtsList.get(iter).getParkInfo().getNumberOFBenches(), iter, 8);
                table.setValueAt(districtsList.get(iter).getParkInfo().getAreaInKmSquare(), iter, 9);
                table.setValueAt(districtsList.get(iter).getParkInfo().getNumberOfEntrances(), iter, 10);
            }
            table.setValueAt(districtsList.get(iter).getPublicTransport().getBusLines(), iter, 11);
            table.setValueAt(districtsList.get(iter).getPublicTransport().getTramLines(), iter, 12);
            table.setValueAt(districtsList.get(iter).getNumberOfInhabitants(), iter, 13);
        }

    }

    void fillInhabitantList(List<Inhabitants> inhabitantsList) {
        model.setRowCount(inhabitantsList.size());
        for(int iter = 0; iter < inhabitantsList.size(); iter++) {
            table.setValueAt(inhabitantsList.get(iter).getInhabitantId(), iter, 0);
            table.setValueAt(inhabitantsList.get(iter).getPersonalInfo().getFirstName(), iter, 1);
            table.setValueAt(inhabitantsList.get(iter).getPersonalInfo().getLastName(), iter, 2);
            table.setValueAt(inhabitantsList.get(iter).getPersonalInfo().getDateOfBirth(), iter, 3);
            table.setValueAt(inhabitantsList.get(iter).getPersonalInfo().getGender(), iter, 4);
            table.setValueAt(inhabitantsList.get(iter).getEducation(), iter, 5);
            table.setValueAt(inhabitantsList.get(iter).getProfession(), iter, 6);
        }
    }

    void fillBuildingList(List<Buildings> buildingsList) {
        model.setRowCount(buildingsList.size());
        for(int iter = 0; iter < buildingsList.size(); iter++) {
            table.setValueAt(buildingsList.get(iter).getBuildingId(), iter, 0);
            table.setValueAt(buildingsList.get(iter).getName(), iter, 1);
            table.setValueAt(buildingsList.get(iter).getType(), iter, 2);
            table.setValueAt(buildingsList.get(iter).getArchitecture().getWallType(), iter, 3);
            table.setValueAt(buildingsList.get(iter).getArchitecture().getWallColor(), iter, 4);
            table.setValueAt(buildingsList.get(iter).getArchitecture().getCondignations(), iter, 5);
            table.setValueAt(buildingsList.get(iter).getArchitecture().getHasBalcony(), iter, 6);
            table.setValueAt(buildingsList.get(iter).getYear(), iter, 7);
        }
    }

    void assignChoseColumnValues(String[] headers, int index) {
        chooseColumn.removeAllItems();
        for (String text: headers) {
            chooseColumn.addItem(text);
        }
        chooseColumn.setSelectedIndex(index);
    }

    List<Buildings> getChosenBuildings (JComboBox chooseColumn, JTextField searchValue) {
        String fieldName = (String) chooseColumn.getItemAt(chooseColumn.getSelectedIndex());
        String fieldValue = searchValue.getText();
        return new Buildings().getFilteredBuildings(fieldName, fieldValue);
    }

    List<Inhabitants> getChosenInhabitants (JComboBox chooseColumn, JTextField searchValue) {
        String fieldName = (String) chooseColumn.getItemAt(chooseColumn.getSelectedIndex());
        String fieldValue = searchValue.getText();
        return new Inhabitants().getFilteredInhabitants(fieldName, fieldValue);
    }

    List<Districts> getChosenDistricts(JComboBox chooseColumn, JTextField searchValue) {
        String fieldName = (String) chooseColumn.getItemAt(chooseColumn.getSelectedIndex());
        String fieldValue = searchValue.getText();
        return new Districts().getFilteredDistricts(fieldName, fieldValue);
    }

}
