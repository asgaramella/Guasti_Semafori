/***************************************************************************\
 *               *                                                         *
 *    #####      *  (!) 2014 by Giovanni Squillero                         *
 *   ######      *  Politecnico di Torino - Dip. Automatica e Informatica  *
 *   ###   \     *  Cso Duca degli Abruzzi 24 / I-10129 TORINO / ITALY     *
 *    ##G  c\    *                                                         *
 *    #     _\   *  tel : +39-011-564.7092  /  Fax: +39-011-564.7099       *
 *    |   _/     *  mail: giovanni.squillero@polito.it                     *
 *    |  _/      *  www : http://www.cad.polito.it/staff/squillero/        *
 *               *                                                         *
\***************************************************************************/

package it.polito.tdp.meteo.db;

import it.polito.tdp.meteo.bean.Situazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per l'accesso al database {@code meteo}
 * 
 * @author Fulvio
 * 
 */
public class MeteoDAO {

	/**
	 * Interroga il database e restituisce tutti i dati nella tabella
	 * {@code situazione} sotto forma di un {@link ArrayList} di
	 * {@link Situazione}, ordinati in modo crescente per data.
	 * 
	 * @return la {@link ArrayList} di {@link Situazione}
	 */
	public List<Situazione> getAllSituazioni() {

		final String sql = "SELECT * FROM situazione ORDER BY data ASC";

		List<Situazione> situazioni = new ArrayList<Situazione>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Situazione s = new Situazione(rs.getString("Localita"),
						rs.getDate("Data").toLocalDate(), rs.getInt("Tmedia"),
						rs.getInt("Tmin"), rs.getInt("Tmax"),
						rs.getInt("Puntorugiada"), rs.getInt("Umidita"),
						rs.getInt("Visibilita"), rs.getInt("Ventomedia"),
						rs.getInt("Ventomax"), rs.getInt("Raffica"),
						rs.getInt("Pressioneslm"), rs.getInt("Pressionemedia"),
						rs.getInt("Pioggia"), rs.getString("Fenomeni"));
				situazioni.add(s);
			}
			conn.close();
			return situazioni;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

	public List<String> getAllLocalita() {
	

		final String sql = "SELECT DISTINCT s.Localita " + 
				"FROM situazione AS s";

		List<String> localita = new ArrayList<String>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				localita.add(rs.getString("s.Localita"));
			}
			conn.close();
			return localita;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}



	public List<Situazione> getAllSituaForCitta(String s) {
		
		final String sql = "SELECT * " + 
				"FROM situazione AS s " + 
				"WHERE s.Localita=? "+
				"ORDER BY s.`Data`" ;
				

		List<Situazione> situazioni = new ArrayList<Situazione>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, s);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Situazione stemp = new Situazione(rs.getString("Localita"),
						rs.getDate("Data").toLocalDate(), rs.getInt("Tmedia"),
						rs.getInt("Tmin"), rs.getInt("Tmax"),
						rs.getInt("Puntorugiada"), rs.getInt("Umidita"),
						rs.getInt("Visibilita"), rs.getInt("Ventomedia"),
						rs.getInt("Ventomax"), rs.getInt("Raffica"),
						rs.getInt("Pressioneslm"), rs.getInt("Pressionemedia"),
						rs.getInt("Pioggia"), rs.getString("Fenomeni"));
				situazioni.add(stemp);
			}
			conn.close();
			return situazioni;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}



	public Integer getTempMaxIeri(LocalDate ieri, String s) {
		final String sql = "SELECT s.Tmax " + 
				"FROM situazione AS s " + 
				"WHERE s.Localita=? " + 
				"AND s.`Data`=?";

		

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, s);
			st.setDate(2, Date.valueOf(ieri));

			ResultSet rs = st.executeQuery();

			int tempIeri=-250;
			if(rs.next()){
			tempIeri=rs.getInt("s.Tmax");}
			
			conn.close();
			return tempIeri;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
