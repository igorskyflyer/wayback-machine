package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Includes.Factory;
import Model.Model_Exam;
import Model.Model_File;

public class Factory_File extends Factory{
	
	private static final String GET_BY_ITEM_ID = "SELECT * FROM `fajlovi` WHERE `item` = ?";
	
	/**
	 * Returns all files by item id
	 * @param int id
	 * @return List<Model_File>
	 */
	public List<Model_File> getAllByItemId(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		Model_File file= null;
		List<Model_File>files = new ArrayList<Model_File>();		
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_BY_ITEM_ID);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				file = new Model_File();
				file.setId(rs.getInt(1));
				file.setName(rs.getString(2));
				file.setItem(rs.getInt(3));
				
				
				files.add(file);
			}

		} catch (SQLException e) {
			files = new ArrayList<Model_File>();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return files;
	}
	

}
