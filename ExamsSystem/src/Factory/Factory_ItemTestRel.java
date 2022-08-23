package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Includes.Factory;
import Model.Model_Item;
import Model.Model_ItemTestRel;

public class Factory_ItemTestRel extends Factory {

	private static final String GET_RELS_FOR_ITEM_ID = "SELECT * FROM `item_test_rel` WHERE `item_id` = ?";
	private static final String DELETE_REL_BY_ID = "DELETE FROM `item_test_rel` WHERE `id` = ?";
	private static final String EXISTS = "SELECT * FROM `item_test_rel` WHERE `item_id`= ? AND `test_id`= ?";
	private static final String INSERT = "INSERT INTO `item_test_rel` (test_id,item_id,test_name,item_name) VALUES ( ? , ? , ? , ? )";

	/**
	 * Inserts new Model_ItemTestRel in DB.
	 * 
	 * @param itr
	 * @return
	 */
	public boolean insert(Model_ItemTestRel itr) {
		boolean status = false;
        Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERT);
			pstm.setInt(1, itr.getTest_id());
			pstm.setInt(2, itr.getItem_id());
			pstm.setString(3, itr.getTest_name());
			pstm.setString(4, itr.getItem_name());
			pstm.execute();

			status = true;

		} catch (SQLException e) {
			status = false;

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * Returns all rels for the given item
	 * 
	 * @param Model_Item
	 *            item
	 * @return List<Model_ItemTestRel>
	 */
	public List<Model_ItemTestRel> getRelsForItem(Model_Item item) {
		return getRelsForItemId(item.getId());
	}

	/**
	 * Returns all rels for the given id
	 * 
	 * @param int id
	 * @return List<Model_ItemTestRel>
	 */
	public List<Model_ItemTestRel> getRelsForItemId(int id) {

		List<Model_ItemTestRel> rels = new ArrayList<Model_ItemTestRel>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Model_ItemTestRel rel = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_RELS_FOR_ITEM_ID);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				rel = new Model_ItemTestRel();
				rel.setId(rs.getInt(1));
				rel.setTest_id(rs.getInt(2));
				rel.setItem_id(rs.getInt(3));
				rel.setTest_name(rs.getString(4));
				rel.setItem_name(rs.getString(5));

				rels.add(rel);
			}

		} catch (SQLException e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rels;

	}

	/**
	 * Deletes the rel by id
	 * 
	 * @param int id
	 * @return boolean
	 */
	public boolean deleteById(int id) {
		boolean status = false;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETE_REL_BY_ID);
			pstm.setInt(1, id);
			pstm.execute();

			status = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = false;
		}

		return status;
	}

	public boolean exists(int item_id, int test_id) {
		boolean status = false;

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EXISTS);
			pstm.setInt(1, item_id);
			pstm.setInt(2, test_id);
			pstm.execute();
			rs = pstm.getResultSet();

			if (rs.next()) {

				status = true;
			}

		} catch (SQLException e) {
			status = true;

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

}
