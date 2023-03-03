package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {

    private long id;
    private long client_id;
    private long vehicle_id;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation(long id, long client_id, long vehicle_id, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation(long client_id, long vehicle_id, LocalDate debut, LocalDate fin) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation() {
        this.id = 000;
        this.client_id = 000;
        this.vehicle_id = 000;
        this.debut = LocalDate.now();
        this.fin = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", vehicle_id=" + vehicle_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }

    public long getId() { return id; }

    public long getClientId() {
        return client_id;
    }

    public long getVehicleId() {
        return vehicle_id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public LocalDate getFin() {
        return fin;
    }
}
