package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





import Includes.Factory;
import Model.Model_Item;
//import Model.Item;
import Model.Model_Student;

public class Factory_Student extends Factory{

	
	private static final String INSERTOSOBA = "INSERT INTO `studenti`(`username`, `password`, `name`, `surname`, `index_num`, `email`, `phone`, `course`, `address`) VALUES (?, ?, ?,?,?, ?, ?,?,?)";
	private static final String SELECBYINDEX = "SELECT * FROM `studenti` WHERE `index_num` =?";
	private static final String SELECBYUSER = "SELECT * FROM `studenti` WHERE `username` =  ?";
	private static final String LOGIN = "SELECT * FROM `studenti` WHERE `username` =  ? AND `password` = ?";
	private static final String ITEMS = "SELECT * FROM `predmeti` WHERE `course` =  ?";
	private static final String GET_ALL = "SELECT * FROM `students`";
	
	
	
	/**
	 * Inserts a new student in DB
	 * @param Model_Student student<br>
	 * @return boolean
	 */
	public boolean insertStudent(Model_Student student) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		boolean status = true;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTOSOBA);
			pstm.setString(1, student.getUsername());
			pstm.setString(2, student.getPassword());
			pstm.setString(3, student.getName());
			pstm.setString(4, student.getSurname());
			pstm.setString(5, student.getIndex_num());
			pstm.setString(6, student.getEmail());
			pstm.setString(7, student.getPhone());
			pstm.setInt(8, student.getCourse());
			pstm.setString(9, student.getAddress());

			pstm.execute();

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
	 * Checks if the given index number exists
	 * 
	 * @param Model_Student
	 *            student
	 * @return boolean
	 */
	public boolean checkIndex_Num(Model_Student student) {
		Connection con = null;
		PreparedStatement pstm = null;
		boolean status = true;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECBYINDEX);
			pstm.setString(1, student.getIndex_num());

			pstm.execute();
			rs = pstm.getResultSet();

			if (!rs.next()) {
				status = false;
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

	
	/**
	 * Validates the user login
	 * @param String user
	 * @param String pass
	 * @return Model_Student 
	 */
	public Model_Student validateLogin(String user, String pass) {

		Connection con = null;
		PreparedStatement pstm = null;
		Model_Student student = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(LOGIN);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			pstm.execute();
			rs = pstm.getResultSet();

			if (rs.next()) {

				student = new Model_Student();
				student.setId(rs.getInt(1));
				student.setUsername(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setName(rs.getString(4));
				student.setSurname(rs.getString(5));
				student.setIndex_num(rs.getString(6));
				student.setEmail(rs.getString(7));
				student.setPhone(rs.getString(8));
				student.setCourse(rs.getInt(9));
				student.setAddress(rs.getString(10));
			}

		} catch (SQLException e) {
			student = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;

	}

	/**
	 * Returns all items for the given student
	 * @param Model_Student student
	 * @return  List<Model_Item>
	 */
	public List<Model_Item> getItems(Model_Student student) {

		List<Model_Item> items = new ArrayList<Model_Item>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Model_Item item = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ITEMS);
			pstm.setInt(1, student.getCourse());

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
	 * Checks if the given username exists
	 * @param student
	 * @return
	 */
	public boolean checkUsername(Model_Student student) {
		Connection con = null;
		PreparedStatement pstm = null;
		boolean status = true;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECBYUSER);
			pstm.setString(1, student.getUsername());

			pstm.execute();
			rs = pstm.getResultSet();

			if (!rs.next()) {
				status = false;
			}

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
	
	public List<Model_Student> getAll(){
		Connection con = null;
		PreparedStatement pstm = null;
		Model_Student student = null;
		List<Model_Student>students = new ArrayList<Model_Student>();		
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_ALL);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				student = new Model_Student();
				student.setId(rs.getInt(1));
				student.setUsername(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setName(rs.getString(4));
				student.setSurname(rs.getString(5));
				student.setIndex_num(rs.getString(6));
				student.setEmail(rs.getString(7));
				student.setPhone(rs.getString(8));
				student.setCourse(rs.getInt(9));
				student.setAddress(rs.getString(10));
				
				students.add(student);
			}

		} catch (SQLException e) {
			student = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}
	
	
	


}
