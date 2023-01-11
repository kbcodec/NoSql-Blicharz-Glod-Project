package org.example;

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

    public ListForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(ListPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //PROVIDE CLOSING WHOLE APPLICATION AFTER 'X' CLICKED, JUST SHOWING MENU FORM
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuForm("Menu").setVisible(true);
            }
        });


        buildingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChooseColumnValues();
                showBuildings();
            }
        });
        searchValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                findByField();
            }
        });
    }

    void findByField() {
        if(searchValue.getText().length() != 0) {
            List<Building> buildingList = new BuildingHandler().getBuildingsByName(new Building().getHeaders().get(1)[chooseColumn.getSelectedIndex()], searchValue.getText());
            System.out.println(chooseColumn.getSelectedIndex());
            System.out.println(buildingList.toString());
            fillBuildingList(buildingList);
        } else {
            showBuildings();
        }
    }

    void setChooseColumnValues() {
        String[] headers = new Building().getHeaders().get(0);
        DefaultComboBoxModel<String> comboboxModel = new DefaultComboBoxModel<>(headers);
        chooseColumn.setModel(comboboxModel);
    }

    void showBuildings() {
        String[] headers = new Building().getHeaders().get(0);
        model = new DefaultTableModel(null, headers);
        table.setModel(model);
        List<Building> buildingList = new BuildingHandler().getAllBuildings();
        fillBuildingList(buildingList);
    }

    void fillBuildingList(List<Building> buildingList) {
        model.setRowCount(buildingList.size());
        for(int iter = 0; iter < buildingList.size(); iter++) {
            table.setValueAt(buildingList.get(iter).getBuildingId(), iter, 0);
            table.setValueAt(buildingList.get(iter).getName(), iter, 1);
            table.setValueAt(buildingList.get(iter).getType(), iter, 2);
            table.setValueAt(buildingList.get(iter).getArchWallType(), iter, 3);
            table.setValueAt(buildingList.get(iter).getArchWallColor(), iter, 4);
            table.setValueAt(buildingList.get(iter).getArchCondignations(), iter, 5);
            table.setValueAt(buildingList.get(iter).getArchHasBalcony(), iter, 6);
            table.setValueAt(buildingList.get(iter).getYearOfBuild(), iter, 7);
        }
    }




}
