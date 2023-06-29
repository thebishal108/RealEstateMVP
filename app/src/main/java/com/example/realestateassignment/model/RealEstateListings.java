package com.example.realestateassignment.model;

import java.util.ArrayList;

public class RealEstateListings {
    Facility facility;
    Option selectedOption;

    public RealEstateListings(Facility facility,Option selectedOption){
        this.facility = facility;
        this.selectedOption = selectedOption;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Option getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Option selectedOption) {
        this.selectedOption = selectedOption;
    }
}
