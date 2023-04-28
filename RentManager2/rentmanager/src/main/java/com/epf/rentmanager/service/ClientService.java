package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

@Service

public class ClientService {

	private ClientDao clientDao;
	private ReservationDao reservationDao;

	private ClientService(ClientDao clientDao, ReservationDao reservationDao){
		this.clientDao = clientDao;
		this.reservationDao = reservationDao;
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
			for (Reservation reservation:reservationDao.findResaByClientId(clientDao.findById(id))) {
				reservationDao.delete(reservation);
			}
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

	public boolean valideAge(Client client){
		if(client.getBdate().isAfter(LocalDate.now().minusYears(18))) {
			// Test de l'âge du client (plus de 18 ans)
			System.out.println("Le client a moins de 18 ans.");
			return false;
		}
		return true;
	}

	public boolean valideName(String nom){
		if(nom.length() < 3){
			// Test du nombre de caractères (3 minimum)
			System.out.println("Le nom ou le prénom saisi est trop court.");
			return false;
		}
		return true;
	}

	public boolean valideEmail(String email) throws ServiceException {
		try{
			for (Client client:clientDao.findAll()) {
				// Test de l'email du client (pas de doublons)
				if(client.getEmail().equals(email)){
					System.out.println("L'email a déjà été utilisé.");
					return false;
				}
			}
			return true;
		}
		catch (DaoException e){
			throw new ServiceException();
		}
	}
}
