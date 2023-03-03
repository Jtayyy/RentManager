package com.epf.rentmanager.model;

import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class Reservation {

    private long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation(long id, Client client, Vehicle vehicle, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation(Client client, Vehicle vehicle, LocalDate debut, LocalDate fin) {
        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation() {
        this.id = 000;
        this.client = new Client();
        this.vehicle = new Vehicle();
        this.debut = LocalDate.now();
        this.fin = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client_id=" + client +
                ", vehicle_id=" + vehicle +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }

    public long getId() { return id; }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public LocalDate getFin() {
        return fin;
    }
}
