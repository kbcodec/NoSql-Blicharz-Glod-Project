package org.example.collections.district;

public class ParkInfo {
    private String parkName;
    private int numberOfFountains;
    private int numberOFBenches;
    private double areaInKmSquare;
    private int numberOfEntrances;

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getNumberOfFountains() {
        return numberOfFountains;
    }

    public void setNumberOfFountains(int numberOfFountains) {
        this.numberOfFountains = numberOfFountains;
    }

    public int getNumberOFBenches() {
        return numberOFBenches;
    }

    public void setNumberOFBenches(int numberOFBenches) {
        this.numberOFBenches = numberOFBenches;
    }

    public double getAreaInKmSquare() {
        return areaInKmSquare;
    }

    public void setAreaInKmSquare(double areaInKmSquare) {
        this.areaInKmSquare = areaInKmSquare;
    }

    public int getNumberOfEntrances() {
        return numberOfEntrances;
    }

    public void setNumberOfEntrances(int numberOfEntrances) {
        this.numberOfEntrances = numberOfEntrances;
    }

    public ParkInfo() {
    }

    public ParkInfo(String parkName, int numberOfFountains, int numberOFBenches, double areaInKmSquare, int numberOfEntrances) {
        this.parkName = parkName;
        this.numberOfFountains = numberOfFountains;
        this.numberOFBenches = numberOFBenches;
        this.areaInKmSquare = areaInKmSquare;
        this.numberOfEntrances = numberOfEntrances;
    }

    @Override
    public String toString() {
        return "ParkInfo{" +
                "parkName='" + parkName + '\'' +
                ", numberOfFountains=" + numberOfFountains +
                ", numberOFBenches=" + numberOFBenches +
                ", areaInKmSquare=" + areaInKmSquare +
                ", numberOfEntrances=" + numberOfEntrances +
                '}';
    }
}
