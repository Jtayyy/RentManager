package com.epf.rentmanager.model;

public class Vehicle {

    private long id;
    private String constructor;
    private int nbplaces;

    public Vehicle(long id, String constructor, int nbplaces) {
        this.id = id;
        this.constructor = constructor;
        this.nbplaces = nbplaces;
    }

    public Vehicle(String constructor, int nbplaces) {
        this.constructor = constructor;
        this.nbplaces = nbplaces;
    }

    public Vehicle() {
        this.id = 000;
        this.constructor = "None";
        this.nbplaces = 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructor='" + constructor + '\'' +
                ", nb_places=" + nbplaces +
                '}';
    }

    public long getId() { return id; }

    public String getConstructor() {
        return constructor;
    }

    public int getNbplaces() {
        return nbplaces;
    }
}
