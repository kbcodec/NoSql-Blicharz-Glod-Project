package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Building {
    private int buildingId;
    private String name;
    private String type;
    private String archWallType;
    private String archWallColor;
    private String archCondignations;
    private boolean archHasBalcony;
    private int yearOfBuild;
    private final static List<String[]> headers = new ArrayList<>(){{
        add(new String[]{"ID", "Nazwa", "Typ budynku", "Typ Ścian", "Kolor ścian", "Liczba kondygnacji", "Czy ma balkon", "Rok budowy"});
        add(new String[]{"building_id","name", "type", "architecture.wall_type", "architecture.wall_color", "architecture.condignations", "architecture.has_balcony", "year"});
    }};

    public Building(int buildingId, String name, String type, String archWallType, String archWallColor, String archCondignations, boolean archHasBalcony, int yearOfBuild) {
        this.buildingId = buildingId;
        this.name = name;
        this.type = type;
        this.archWallType = archWallType;
        this.archWallColor = archWallColor;
        this.archCondignations = archCondignations;
        this.archHasBalcony = archHasBalcony;
        this.yearOfBuild = yearOfBuild;
    }

    public Building(Document doc) {
        this.buildingId = doc.getInteger("building_id");
        this.name = doc.getString("name");
        this.type = doc.getString("type");
        this.archWallType = doc.get("architecture", Document.class).getString("wall_type");
        this.archWallColor = doc.get("architecture", Document.class).getString("wall_color");
        this.archCondignations = doc.get("architecture", Document.class).getString("condignations");
        this.archHasBalcony = doc.get("architecture", Document.class).getBoolean("has_balcony");
        this.yearOfBuild = doc.getInteger("year");
    }

    public Building() {
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getArchWallType() {
        return archWallType;
    }

    public String getArchWallColor() {
        return archWallColor;
    }

    public String getArchCondignations() {
        return archCondignations;
    }

    public String getArchHasBalcony() {
        return archHasBalcony ? "Tak":"Nie";
    }

    public int getYearOfBuild() {
        return yearOfBuild;
    }

    public List<String[]> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", archWallType='" + archWallType + '\'' +
                ", archWallColor='" + archWallColor + '\'' +
                ", archCondignations=" + archCondignations +
                ", archHasBalcony=" + archHasBalcony +
                ", yearOfBuild=" + yearOfBuild +
                '}';
    }
}
