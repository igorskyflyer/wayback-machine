package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Includes.Factory;
import Model.Model_Administrator;

/**
 * 
 * @author Maja Devic
 *
 */
public class Factory_Administrator extends Factory {

	private static String LOGIN = "SELECT * FROM `administrator` WHERE `username` =  ? AND `password` = ?";

	public Model_Administrator validateLogin(String user, String pass) {

		Connection con = null;
		PreparedStatement pstm = null;
		Model_Administrator admin = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(LOGIN);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			pstm.execute();
			rs = pstm.getResultSet();

			if (rs.next()) {

				admin = new Model_Administrator();
				admin.setId(rs.getInt(1));
				admin.setUser(rs.getString(2));
				admin.setPass(rs.getString(3));
				admin.setName(rs.getString(4));
				admin.setSurname(rs.getString(5));

			}

		} catch (SQLException e) {
			admin = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admin;

	}

}
