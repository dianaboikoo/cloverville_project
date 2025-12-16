package com.example.cloverville_project;

import java.util.ArrayList;
import java.util.List;

public class GreenActionList {

    private List<GreenAction> actions = new ArrayList<>();

    public GreenActionList() {}

    public void addAction(GreenAction action) {
        actions.add(action);
    }

    public void removeAction(GreenAction action) {
        actions.remove(action);
    }
    public void clear() {
        actions.clear();   // or offers.clear(), tasks.clear(), actions.clear()
    }


    public List<GreenAction> getAllActions() {
        return actions;
    }

    public GreenAction findByName(String name) {
        for (GreenAction action : actions) {
            if (action.getName().equalsIgnoreCase(name)) {
                return action;
            }
        }
        return null;
    }

    public boolean exists(String name) {
        return findByName(name) != null;
    }

    public List<GreenAction> getActionsByPointValue(int points) {
        List<GreenAction> result = new ArrayList<>();
        for (GreenAction action : actions) {
            if (action.getGreenPoints() == points) {
                result.add(action);
            }
        }
        return result;
    }
}
