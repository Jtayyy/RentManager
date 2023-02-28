package com.epf.rentmanager.dao;

import java.sql.Connection;
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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(*) AS total FROM Vehicle;";

	public long create(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public Vehicle findById(long id) throws DaoException {

		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();

			rs.next();
			String constructeur = rs.getString("constructeur");
			int nb_places = rs.getInt("nb_places");

			connection.close();
			statement.close();
			rs.close();

			return new Vehicle(id, constructeur, nb_places);
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle> vehicules = new ArrayList<Vehicle>();

		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				long id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nb_places = rs.getInt("nb_places");

				Vehicle vehicule = new Vehicle(id, constructeur, nb_places);
				vehicules.add(vehicule);

			}

			connection.close();
			statement.close();
			rs.close();

			return vehicules;
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
			ResultSet rs = statement.executeQuery(COUNT_VEHICLES_QUERY);

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
