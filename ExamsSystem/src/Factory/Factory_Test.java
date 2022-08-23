package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Includes.Factory;
import Model.Model_Exam;
import Model.Model_Test;

public class Factory_Test extends Factory {

	private static final String GET_ALL = "SELECT * FROM `testovi`";
	private static final String GET_BY_ID = "SELECT * FROM `testovi` WHERE `id` = ?";

	public static Factory_Test instance = null;

	/**
	 * Returns class instance
	 * 
	 * @return
	 */
	public static Factory_Test getInstance() {
		if (instance == null) {
			instance = new Factory_Test();
		}
		return instance;
	}

	/**
	 * Returns all exams by item id
	 * 
	 * @param int id
	 * @return List<Model_Exam>
	 */
	public List<Model_Test> getAll() {
		Connection con = null;
		PreparedStatement pstm = null;
		Model_Test test = null;
		List<Model_Test> tests = new ArrayList<Model_Test>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_ALL);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				test = new Model_Test();
				test.setId(rs.getInt(1));
				test.setName(rs.getString(2));
				test.setTitles(rs.getString(3));
				test.setAnswers(rs.getString(4));
				test.setValues(rs.getString(5));

				tests.add(test);
			}

		} catch (SQLException e) {
			tests = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tests;
	}

	public Model_Test getById(String id) {
		Connection con = null;
		PreparedStatement pstm = null;
		Model_Test test = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_BY_ID);
			pstm.setInt(1, Integer.valueOf(id));
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				test = new Model_Test();
				
				test.setId(rs.getInt(1));
				test.setName(rs.getString(2));
				test.setTitles(rs.getString(3));
				test.setAnswers(rs.getString(4));
				test.setValues(rs.getString(5));

			}

		} catch (SQLException e) {
			test = new Model_Test();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return test;
	}

}
