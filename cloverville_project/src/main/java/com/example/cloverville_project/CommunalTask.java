package com.example.cloverville_project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommunalTask {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final IntegerProperty personalPoints = new SimpleIntegerProperty();

    public CommunalTask(String name, String description, int personalPoints) {
        this.name.set(name);
        this.description.set(description);
        this.personalPoints.set(personalPoints);
    }

    public String getName() { return name.get(); }
    public void setName(String n) { name.set(n); }
    public StringProperty nameProperty() { return name; }

    public String getDescription() { return description.get(); }
    public void setDescription(String d) { description.set(d); }
    public StringProperty descriptionProperty() { return description; }

    public int getPersonalPoints() { return personalPoints.get(); }
    public void setPersonalPoints(int p) { personalPoints.set(p); }
    public IntegerProperty personalPointsProperty() { return personalPoints; }

    @Override
    public String toString() {
        return getName();
    }
}
