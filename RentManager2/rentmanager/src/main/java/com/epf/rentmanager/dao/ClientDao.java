package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(*) AS total FROM Client;";
	
	public void delete(long id) throws DaoException {

		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_QUERY);
			statement.setLong(1, id);

			statement.execute();

			connection.close();
			statement.close();
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public void create(Client client) throws DaoException {

		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY);
			statement.setString(1, client.getLastname());
			statement.setString(2, client.getFirstname());
			statement.setString(3, client.getEmail());
			statement.setDate(4, Date.valueOf(client.getBdate()));

			statement.execute();

			connection.close();
			statement.close();
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public Client findById(long id) throws DaoException {

		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_QUERY);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();

			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			LocalDate date = rs.getDate("naissance").toLocalDate();

			connection.close();
			statement.close();
			rs.close();

			return new Client(id, nom, prenom, email, date);
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Client> findAll() throws DaoException {

		List<Client> clients = new ArrayList<Client>();

		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				long id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate date = rs.getDate("naissance").toLocalDate();

				Client client = new Client(id, nom, prenom, email, date);
				clients.add(client);
			}

			connection.close();
			statement.close();
			rs.close();

			return clients;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public int getCount() throws DaoException {

		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(COUNT_CLIENTS_QUERY);

			rs.next();
			int count = rs.getInt("total");

			connection.close();
			statement.close();
			rs.close();

			return count;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}
}
