package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import Model.Model_Course;

/**
 * 
 * @author Maja Devic
 *
 */
public class Factory_Course{

	private static String GET_ALL = "SELECT * FROM `smerovi`";
	private static final String GET_BY_ID = "SELECT * FROM `smerovi` WHERE `id` = ?";
	
	private DataSource ds;

	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJU
	public Factory_Course() {
		try {
			InitialContext cxt = new InitialContext();
			if (cxt == null) {

			}
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/mysql");
			if (ds == null) {

			}
		} catch (NamingException e) {

		}
	}

	public List<Model_Course> getAllCoursers() {

		List<Model_Course> smerovi = new ArrayList<Model_Course>();

		Connection con = null;
		PreparedStatement pstm = null;
		Model_Course smer = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_ALL);
			
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				smer = new Model_Course();
				smer.setId(rs.getInt(1));
				smer.setName(rs.getString(2));
				smer.setShort_name(rs.getString(3));

				smerovi.add(smer);

			}

		} catch (SQLException e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return smerovi;

	}
	
	public Model_Course getcourseById(int id) {
        Connection con = null;
		PreparedStatement pstm = null;
		Model_Course smer = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_BY_ID);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				smer = new Model_Course();
				smer.setId(rs.getInt(1));
				smer.setName(rs.getString(2));
				smer.setShort_name(rs.getString(3));

				

			}

		} catch (SQLException e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return smer;

	}
	
	

}
