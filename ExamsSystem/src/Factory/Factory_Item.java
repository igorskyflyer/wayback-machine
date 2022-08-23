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

import Includes.Factory;
import Model.Model_Administrator;
import Model.Model_Item;

/**
 * 
 * @author Maja Devic
 *
 */
public class Factory_Item extends Factory {

	private static final String ITEMS = "SELECT * FROM `predmeti`";
	private static final String ITEMS_FOR_ADMIN = "SELECT * FROM `predmeti` WHERE `admin_id` = ?";
	private static final String GET_BY_ID = "SELECT * FROM `predmeti` WHERE `id` = ?";

	public static Factory_Item instance = null;

	/**
	 * Returns class instance
	 * 
	 * @return
	 */
	public static Factory_Item getInstance() {
		if (instance == null) {
			instance = new Factory_Item();
		}
		return instance;
	}

	/**
	 * Returns all items
	 * 
	 * @return List<Model_Item>
	 */
	public List<Model_Item> getAll() {

		List<Model_Item> items = new ArrayList<Model_Item>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Model_Item item = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ITEMS);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				item = new Model_Item();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setProfessor(rs.getInt(3));
				item.setCourse(rs.getInt(4));

				items.add(item);
			}

		} catch (SQLException e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;

	}

	/**
	 * Returns items for the given admin
	 * 
	 * @param Model_Administrator
	 *            admin
	 * @return List<Model_Item>
	 */
	public List<Model_Item> getItemsByAdmin(Model_Administrator admin) {
		return getItemsForAdminId(admin.getId());
	}

	/**
	 * Returns all items for the professor id
	 * 
	 * @param int id
	 * @return List<Model_Item>
	 */
	public List<Model_Item> getItemsForAdminId(int id) {

		List<Model_Item> items = new ArrayList<Model_Item>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Model_Item item = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ITEMS_FOR_ADMIN);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				item = new Model_Item();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setProfessor(rs.getInt(3));
				item.setCourse(rs.getInt(4));

				items.add(item);
			}

		} catch (SQLException e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;

	}

	/**
	 * Returns the item by id
	 * 
	 * @param id
	 * @return
	 */
	public Model_Item getById(int id) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Model_Item item = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_BY_ID);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				item = new Model_Item();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setProfessor(rs.getInt(3));
				item.setCourse(rs.getInt(4));

			}

		} catch (SQLException e) {
			item = new Model_Item();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;

	}

}
