package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service

public class ClientService {

	private ClientDao clientDao;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}

	public void create(Client client) throws ServiceException {
		try{
			clientDao.create(client);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public void modify(Client client) throws ServiceException {
		try{
			clientDao.modify(client);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public void delete(long id) throws ServiceException {
		try{
			clientDao.delete(id);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		try{
			return clientDao.findById(id);
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		try{
			return clientDao.findAll();
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}

	public int getCount() throws ServiceException {
		try{
			return clientDao.getCount();
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}
	
}
