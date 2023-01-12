package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class District {
    private int district_id;
    private String name;
    private int number_of_inhabitatns;
    //private int number_of_buildings;
    private int utility_buildings;
    private int residential_buildings;
    private int industrial_buildings;

    private boolean has_park;
    private String park_name;
    private int number_of_fountains;
    private int number_of_benches;
    private double parkArea;

    private int number_of_entrances;

    private String[] bus_lines = new String[100];
    private String[] tram_lines = new String[100];
    private double area;


    public District() {
    }

    public District(int district_id, String name, int number_of_inhabitatns, int utility_buildings, int residential_buildings, int industrial_buildings, boolean has_park, String park_name, int number_of_fountains, int number_of_benches, double parkArea, int number_of_entrances, String[] bus_lines, String[] tram_lines, double area) {
        this.district_id = district_id;
        this.name = name;
        this.number_of_inhabitatns = number_of_inhabitatns;
        this.utility_buildings = utility_buildings;
        this.residential_buildings = residential_buildings;
        this.industrial_buildings = industrial_buildings;
        this.has_park = has_park;
        this.park_name = park_name;
        this.number_of_fountains = number_of_fountains;
        this.number_of_benches = number_of_benches;
        this.parkArea = parkArea;
        this.number_of_entrances = number_of_entrances;
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FindIterable<Document> getAllDistricts() {

        MongoDBConnection database = new MongoDBConnection();
        MongoCollection<Document> districtsCollection = database.getCollection("Districts");
        return districtsCollection.find();
    }
    @Override
    public String toString() {
        return "District{" +
                "district_id=" + district_id +
                ", name='" + name + '\'' +
                ", number_of_inhabitatns=" + number_of_inhabitatns +
                ", utility_buildings=" + utility_buildings +
                ", residential_buildings=" + residential_buildings +
                ", industrial_buildings=" + industrial_buildings +
                ", has_park=" + has_park +
                ", park_name='" + park_name + '\'' +
                ", number_of_fountains=" + number_of_fountains +
                ", number_of_benches=" + number_of_benches +
                ", parkArea=" + parkArea +
                ", number_of_entrances=" + number_of_entrances +
                ", bus_lines=" + Arrays.toString(bus_lines) +
                ", tram_lines=" + Arrays.toString(tram_lines) +
                ", area=" + area +
                '}';
    }

    void showData(JTable jTable, DefaultTableModel dtm) {
        dtm.setRowCount(2);
        jTable.setValueAt(name, 0, 0);
        jTable.setValueAt(name, 0, 1);
        jTable.setValueAt(name, 1, 0);
        jTable.setValueAt(name, 1, 1);
    }
}
