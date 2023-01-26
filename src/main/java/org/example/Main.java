package org.example;

import org.example.collections.building.BuildingGenerator;
import org.example.collections.building.Buildings;
import org.example.collections.district.DistrictGenerator;
import org.example.collections.district.Districts;
import org.example.collections.inhabitant.InhabitantGenerator;
import org.example.collections.inhabitant.Inhabitants;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new MenuForm("Menu").setVisible(true);

        //InhabitantGenerator.Generator();
        //DistrictGenerator.Generator();
        //BuildingGenerator.Generator();
    }
}
