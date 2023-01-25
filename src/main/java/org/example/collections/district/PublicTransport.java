package org.example.collections.district;

import java.util.List;

public class PublicTransport {
    private List<Integer> busLines;
    private List<Integer> tramLines;

    public List<Integer> getBusLines() {
        return busLines;
    }

    public void setBusLines(List<Integer> busLines) {
        this.busLines = busLines;
    }

    public List<Integer> getTramLines() {
        return tramLines;
    }

    public void setTramLines(List<Integer> tramLines) {
        this.tramLines = tramLines;
    }

    public PublicTransport() {
    }

    public PublicTransport(List<Integer> busLines, List<Integer> tramLines) {
        this.busLines = busLines;
        this.tramLines = tramLines;
    }

    @Override
    public String toString() {
        return "PublicTransport{" +
                "busLines=" + busLines +
                ", tramLines=" + tramLines +
                '}';
    }
}
