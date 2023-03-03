package com.epf.rentmanager.model;

public class Vehicle {

    private long id;
    private String constructor;
    private String modele;
    private int nbplaces;

    public Vehicle(long id, String constructor, String modele, int nbplaces) {
        this.id = id;
        this.constructor = constructor;
        this.modele = modele;
        this.nbplaces = nbplaces;
    }

    public Vehicle(String constructor, String modele, int nbplaces) {
        this.constructor = constructor;
        this.modele = modele;
        this.nbplaces = nbplaces;
    }

    public Vehicle() {
        this.id = 000;
        this.constructor = "None";
        this.modele = "None";
        this.nbplaces = 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructor='" + constructor + '\'' +
                ", modele='" + modele + '\'' +
                ", nbplaces=" + nbplaces +
                '}';
    }

    public long getId() { return id; }

    public String getConstructor() {
        return constructor;
    }

    public String getModele() { return modele; }

    public int getNbplaces() {
        return nbplaces;
    }
}
