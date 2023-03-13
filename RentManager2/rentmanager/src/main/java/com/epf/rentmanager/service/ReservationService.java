package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }

    public void create(Reservation reservation) throws ServiceException {
        try{
            ReservationDao.getInstance().create(reservation);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public void delete(long id) throws ServiceException {
        try{
            ReservationDao.getInstance().delete(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(long id) throws ServiceException {
        try{
            return ReservationDao.getInstance().findResaByClientId(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(long id) throws ServiceException {
        try{
            return ReservationDao.getInstance().findResaByVehicleId(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try{
            return ReservationDao.getInstance().findAll();
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getCount() throws ServiceException {
        try{
            return ReservationDao.getInstance().getCount();
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getCount(Client client) throws ServiceException {
        try{
            return ReservationDao.getInstance().getCount(client);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Vehicle> findActualVehiclesByClientId(long id) throws ServiceException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try{
            for (Reservation reservation:ReservationDao.getInstance().findResaByClientId(id)) {
                Vehicle vehicle = reservation.getVehicle();
                if(!vehicles.contains(vehicle) && LocalDate.now().isAfter(reservation.getDebut()) && LocalDate.now().isBefore(reservation.getFin())){
                    vehicles.add(vehicle);
                }
            }
            return vehicles;
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getActualVehiclesCount(long id) throws ServiceException {
        try{
            return findActualVehiclesByClientId(id).size();
        }
        catch (ServiceException e){
            throw new ServiceException();
        }
    }
}
