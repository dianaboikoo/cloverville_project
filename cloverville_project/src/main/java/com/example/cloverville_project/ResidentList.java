package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ResidentList {
  private List<Resident> residents = new ArrayList<>();

  public ResidentList() {}
  public void addResident(Resident resident)
  {
      residents.add(resident);
  }
  public void removeResident(Resident resident)
  {
      residents.remove(resident);
  }

  public List<Resident> getAllResidents()
  {
      return residents;
  }
    public void clear() {
        residents.clear();
    }


    public Resident findByName(String name)
  {
      for(Resident r : residents)
      {
          if(r.getName().equalsIgnoreCase(name))
              return r;
      }
      return null;
  }
     public boolean exists(String name)
     {
         return findByName(name) != null;
     }

}
