package org.example.collections.building;

public class Architecture {
    private int condignations;
    private Boolean hasBalcony;
    private String wallType;
    private String wallColor;

    public int getCondignations() {
        return condignations;
    }

    public void setCondignations(int condignations) {
        this.condignations = condignations;
    }

    public Boolean getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(Boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public String getWallType() {
        return wallType;
    }

    public void setWallType(String wallType) {
        this.wallType = wallType;
    }

    public String getWallColor() {
        return wallColor;
    }

    public void setWallColor(String wallColor) {
        this.wallColor = wallColor;
    }

    public Architecture() {
    }

    public Architecture(int condignations, Boolean hasBalcony, String wallType, String wallColor) {
        this.condignations = condignations;
        this.hasBalcony = hasBalcony;
        this.wallType = wallType;
        this.wallColor = wallColor;
    }

    @Override
    public String toString() {
        return "Architecture{" +
                "condignations='" + condignations + '\'' +
                ", hasBalcony=" + hasBalcony +
                ", wallType='" + wallType + '\'' +
                ", wallColor='" + wallColor + '\'' +
                '}';
    }
}
