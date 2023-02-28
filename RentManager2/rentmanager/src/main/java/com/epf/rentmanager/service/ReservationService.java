package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

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

    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer un client
        return 0;
    }

    public List<Reservation> findResaByClientId(long id) throws ServiceException {
        // TODO: récupérer une reservations par son id
        try{
            return ReservationDao.getInstance().findResaByClientId(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(long id) throws ServiceException {
        // TODO: récupérer une reservations par son id
        try{
            return ReservationDao.getInstance().findResaByVehicleId(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        // TODO: récupérer toutes les reservations
        try{
            return ReservationDao.getInstance().findAll();
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    public int getCount() throws ServiceException {
        // TODO: compter toutes les reservations
        try{
            return ReservationDao.getInstance().getCount();
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }
}
