package org.example.collections.district;

public class NumberOfBuildings {
    private int utilityBuildings;
    private int residentialBuildings;
    private int industrialBuildings;

    public int getUtilityBuildings() {
        return utilityBuildings;
    }

    public void setUtilityBuildings(int utilityBuildings) {
        this.utilityBuildings = utilityBuildings;
    }

    public int getResidentialBuildings() {
        return residentialBuildings;
    }

    public void setResidentialBuildings(int residentialBuildings) {
        this.residentialBuildings = residentialBuildings;
    }

    public int getIndustrialBuildings() {
        return industrialBuildings;
    }

    public void setIndustrialBuildings(int industrialBuildings) {
        this.industrialBuildings = industrialBuildings;
    }

    public NumberOfBuildings() {
    }

    public NumberOfBuildings(int utilityBuildings, int residentialBuildings, int industrialBuildings) {
        this.utilityBuildings = utilityBuildings;
        this.residentialBuildings = residentialBuildings;
        this.industrialBuildings = industrialBuildings;
    }

    @Override
    public String toString() {
        return "NumberOfBuildings{" +
                "utilityBuildings=" + utilityBuildings +
                ", residentialBuildings=" + residentialBuildings +
                ", industrialBuildings=" + industrialBuildings +
                '}';
    }
}
