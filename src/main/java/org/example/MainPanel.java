package org.example;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainPanel extends JFrame {
    private JPanel MainP;
    private JTable table1;
    private JButton button1;

    public MainPanel(String title) throws HeadlessException {
        super(title);
        setContentPane(MainP);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                frameOpened();
            }
        });
    }

    void frameOpened() {
        Building building = new Building();
        List<String> embeddedList = new ArrayList<>();
        embeddedList.add("architecture");
        FindIterable<Document> allBuildings = building.getAllBuildings();
        building.setName(String.valueOf(allBuildings.first().getEmbedded(embeddedList, Document.class).get("wall_type")));
        String n[] = {"Nazwa", "Bang"};
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, n);
        table1.setModel(model);
        building.showData(table1, model);
    }




}
