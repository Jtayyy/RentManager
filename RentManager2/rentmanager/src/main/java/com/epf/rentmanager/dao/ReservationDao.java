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

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(*) AS total FROM Reservation;";

	public void delete(long id) throws DaoException {

		try(Connection connection = ConnectionManager.getConnection();){
			PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			statement.setLong(1, id);

			statement.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public void create(Reservation reservation) throws DaoException {

		try(Connection connection = ConnectionManager.getConnection();){
			PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY);
			statement.setLong(1, reservation.getClient().getId());
			statement.setLong(2, reservation.getVehicle().getId());
			statement.setDate(3, Date.valueOf(reservation.getDebut()));
			statement.setDate(4, Date.valueOf(reservation.getFin()));

			statement.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Reservation> findResaByClientId(long clientId) throws DaoException {

		List<Reservation> reservations = new ArrayList<Reservation>();

		try(Connection connection = ConnectionManager.getConnection();){
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			statement.setLong(1, clientId);

			ResultSet rs = statement.executeQuery();

			while(rs.next()){
				long id = rs.getLong("id");
				long vehicleId = rs.getLong("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, ClientDao.getInstance().findById(clientId), VehicleDao.getInstance().findById(vehicleId), debut, fin);
				reservations.add(reservation);
			}
			return reservations;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {

		List<Reservation> reservations = new ArrayList<Reservation>();

		try(Connection connection = ConnectionManager.getConnection();){
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			statement.setLong(1, vehicleId);

			ResultSet rs = statement.executeQuery();

			while(rs.next()){
				long id = rs.getLong("id");
				long clientId = rs.getLong("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, ClientDao.getInstance().findById(clientId), VehicleDao.getInstance().findById(vehicleId), debut, fin);
				reservations.add(reservation);
			}
			return reservations;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Reservation> findAll() throws DaoException {

		List<Reservation> reservations = new ArrayList<Reservation>();

		try(Connection connection = ConnectionManager.getConnection();){
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while(rs.next()){
				long id = rs.getLong("id");
				long clientId = rs.getLong("client_id");
				long vehicleId = rs.getLong("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, ClientDao.getInstance().findById(clientId), VehicleDao.getInstance().findById(vehicleId), debut, fin);
				reservations.add(reservation);
			}
			return reservations;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public int getCount() throws DaoException {

		try(Connection connection = ConnectionManager.getConnection();){
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(COUNT_RESERVATIONS_QUERY);

			rs.next();
			int count = rs.getInt("total");
			return count;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}
}
