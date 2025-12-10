package com.example.cloverville_project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GreenAction {

    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty greenPoints = new SimpleIntegerProperty();

    public GreenAction(String name, int points) {
        this.name.set(name);
        this.greenPoints.set(points);
    }

    public String getName() { return name.get(); }
    public void setName(String n) { name.set(n); }
    public StringProperty nameProperty() { return name; }

    public int getGreenPoints() { return greenPoints.get(); }
    public void setGreenPoints(int p) { greenPoints.set(p); }
    public IntegerProperty greenPointsProperty() { return greenPoints; }

    @Override
    public String toString() {
        return getName();
    }
}
