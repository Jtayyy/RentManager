package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service

public class VehicleService {

	private VehicleDao vehicleDao;

	private VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}

	public void create(Vehicle vehicle) throws ServiceException {
		try{
			vehicleDao.create(vehicle);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public void delete(long id) throws ServiceException {
		try{
			vehicleDao.delete(id);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try{
			return vehicleDao.findById(id);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try{
			return vehicleDao.findAll();
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public int getCount() throws ServiceException {
		try{
			return vehicleDao.getCount();
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public void modify(Vehicle vehicle) throws ServiceException {
		try{
			vehicleDao.modify(vehicle);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}
}
