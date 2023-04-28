package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service

public class ReservationService {

    private ReservationDao reservationDao;
    private VehicleService vehicleService;
    private ReservationService(ReservationDao reservationDao, VehicleService vehicleService){
        this.reservationDao = reservationDao;
        this.vehicleService = vehicleService;
    }

    public void create(Reservation reservation) throws ServiceException {
        try{
            reservationDao.create(reservation);
            if(LocalDate.now().isAfter(reservation.getBeginning()) && LocalDate.now().isBefore(reservation.getEnding())){
                reservation.getVehicle().setReserved(true);
                vehicleService.modify(reservation.getVehicle());
            }
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public void modify(Reservation reservation) throws ServiceException {
        try{
            reservationDao.modify(reservation);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public Reservation findById(long id) throws ServiceException {
        try{
            return reservationDao.findById(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public void delete(Reservation reservation) throws ServiceException {
        try{
            reservationDao.delete(reservation);
            if(LocalDate.now().isAfter(reservation.getBeginning()) && LocalDate.now().isBefore(reservation.getEnding())){
                reservation.getVehicle().setReserved(false);
                vehicleService.modify(reservation.getVehicle());
            }
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(Client client) throws ServiceException {
        try{
            return reservationDao.findResaByClientId(client);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(Vehicle vehicle) throws ServiceException {
        try{
            return reservationDao.findResaByVehicleId(vehicle);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try{
            return reservationDao.findAll();
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getCount() throws ServiceException {
        try{
            return reservationDao.getCount();
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getCount(Client client) throws ServiceException {
        try{
            return reservationDao.getCount(client);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getCount(Vehicle vehicle) throws ServiceException {
        try{
            return reservationDao.getCount(vehicle);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Vehicle> findActualVehiclesByClientId(Client client) throws ServiceException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try{
            for (Reservation reservation:reservationDao.findResaByClientId(client)) {
                Vehicle vehicle = reservation.getVehicle();
                if(!vehicles.contains(vehicle) && LocalDate.now().isAfter(reservation.getBeginning()) && LocalDate.now().isBefore(reservation.getEnding())){
                    vehicles.add(vehicle);
                }
            }
            return vehicles;
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getActualVehiclesCount(Client client) throws ServiceException {
        try{
            return findActualVehiclesByClientId(client).size();
        }
        catch (ServiceException e){
            throw new ServiceException();
        }
    }

    public boolean valideReserv(Reservation reservation) throws ServiceException {
        try{
            for (Reservation resa: reservationDao.findResaByVehicleId(reservation.getVehicle())) {
                if(reservation.getBeginning().isBefore(resa.getEnding()) && reservation.getEnding().isAfter(resa.getBeginning())){
                    System.out.println("Cette voiture est déjà réservée pendant cette date.");
                    return false;
                }
            }
            return true;
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public boolean valideSept(Reservation reservation){
        if(ChronoUnit.DAYS.between(reservation.getBeginning(), reservation.getEnding()) > 7){
            System.out.println("Une voiture ne peut être réservée plus de 7 jours de suite.");
            return false;
        }
        return true;
    }
}