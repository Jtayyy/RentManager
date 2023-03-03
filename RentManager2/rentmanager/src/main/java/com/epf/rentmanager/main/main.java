package com.epf.rentmanager.main;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    public static void main(String args[]) throws ServiceException {

        ClientService clients = ClientService.getInstance();
        VehicleService vehicules = VehicleService.getInstance();
        ReservationService reservations = ReservationService.getInstance();

        Boolean boucle = true;
        while(boucle){

            Scanner sc = new Scanner(System.in);

            System.out.println("Que voulez-vous faire ?"
            + "\n(1) Partie client"
            + "\n(2) Partie véhicule"
            + "\n(3) Partie réservation"
            + "\n(0) Annuler");

            try{
                switch (sc.nextInt()) {

                    case 1:
                        System.out.println("Que voulez-vous faire ?"
                                + "\n(1) Ajouter un client"
                                + "\n(2) Supprimer un client"
                                + "\n(3) Afficher les clients"
                                + "\n(4) Rechercher un client"
                                + "\n(0) Annuler");

                        switch (sc.nextInt()) {

                            case 1:
                                clients.create(new Client(sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                                break;

                            case 2:
                                clients.delete(sc.nextLong());
                                break;

                            case 3:
                                for (Client client : clients.findAll()) {
                                    System.out.println(client);
                                }
                                break;

                            case 4:
                                clients.findById(sc.nextLong());
                                break;
                        }
                        break;

                    case 2:
                        System.out.println("Que voulez-vous faire ?"
                                + "\n(1) Ajouter un véhicule"
                                + "\n(2) Supprimer un véhicule"
                                + "\n(3) Afficher les véhicules"
                                + "\n(4) Rechercher un véhicule"
                                + "\n(0) Annuler");

                        switch (sc.nextInt()) {

                            case 1:
                                vehicules.create(new Vehicle(sc.next(), sc.nextInt()));
                                break;

                            case 2:
                                vehicules.delete(sc.nextLong());
                                break;

                            case 3:
                                for (Vehicle vehicle : vehicules.findAll()) {
                                    System.out.println(vehicle);
                                }
                                break;

                            case 4:
                                vehicules.findById(sc.nextLong());
                                break;
                        }
                        break;

                    case 3:
                        System.out.println("Que voulez-vous faire ?"
                                + "\n(1) Ajouter une réservation"
                                + "\n(2) Supprimer une réservation"
                                + "\n(3) Afficher les réservations"
                                + "\n(4) Rechercher les réservations pour un client"
                                + "\n(5) Rechercher les réservations pour un véhicule"
                                + "\n(0) Annuler");

                        switch (sc.nextInt()) {

                            case 1:
                                reservations.create(new Reservation(sc.nextLong(), sc.nextLong(), LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                                break;

                            case 2:
                                reservations.delete(sc.nextLong());
                                break;

                            case 3:
                                for (Reservation reservation : reservations.findAll()) {
                                    System.out.println(reservation);
                                }
                                break;

                            case 4:
                                reservations.findResaByClientId(sc.nextLong());
                                break;

                            case 5:
                                reservations.findResaByVehicleId(sc.nextLong());
                                break;
                        }
                        break;

                    case 0:
                        boucle = false;
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Erreur de saisie, veuillez réessayer.");
            }
            catch (DateTimeParseException e){
                System.out.println("Erreur de saisie dans la date de naissance (format : JJ/MM/AAAA)");
            }
        }
    }
}