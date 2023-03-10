package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}

	public void create(Client client) throws ServiceException {
		// TODO: créer un client
		try{
			ClientDao.getInstance().create(client);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public void delete(long id) throws ServiceException {
		// TODO: supprimer un client
		try{
			ClientDao.getInstance().delete(id);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		// TODO: récupérer un client par son id
		try{
			return ClientDao.getInstance().findById(id);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try{
			return ClientDao.getInstance().findAll();
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public int getCount() throws ServiceException {
		// TODO: compter tous les clients
		try{
			return ClientDao.getInstance().getCount();
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}
	
}
