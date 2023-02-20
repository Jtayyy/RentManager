package com.epf.rentmanager.model;

public class Vehicle {

    private long id;
    private String constructor;
    private int nb_places;

    public Vehicle(long id, String constructor, int nb_places) {
        this.id = id;
        this.constructor = constructor;
        this.nb_places = nb_places;
    }

    public Vehicle() {
        this.id = 000;
        this.constructor = "None";
        this.nb_places = 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructor='" + constructor + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }
}
