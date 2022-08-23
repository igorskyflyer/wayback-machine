package Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Includes.Factory;
import Model.Model_Exam;
import Model.Model_Student;

public class Factory_Exam extends Factory {

	private static final String GET_BY_ITEM_ID = "SELECT * FROM `polaganja` WHERE `item_id` = ?";
	private static final String GET_ALL_PASSED = "SELECT * FROM `polaganja` WHERE  `grade` > '5'  ORDER BY `percent` DESC";
	private static final String GET_BY_STUDENT_AND_TEST = "SELECT * FROM `polaganja` WHERE `student_id`= ? AND `test_id` = ? ";
	private static final String INSERT = "INSERT INTO `polaganja` (`test_id`, `student_id`, `item_id`, `grade`, `percent`) VALUES (?,?,?,?,?)";
	private static final String SET_RESULT = "UPDATE `polaganja` SET `percent` = ? , `grade` = ? WHERE `id` = ? ";

	/**
	 * 
	 * @param exam
	 * @return
	 */
	public Model_Exam insert(Model_Exam exam) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con
					.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, exam.getTest_id());
			pstm.setInt(2, exam.getStudent_id());
			pstm.setInt(3, exam.getItem_id());
			pstm.setInt(4, exam.getGrade());
			pstm.setFloat(5, exam.getPercent());
			pstm.execute();

			// rs = pstm.getGeneratedKeys();

			ResultSet generatedKeys = pstm.getGeneratedKeys();

			if (generatedKeys.next()) {
				exam.setId(generatedKeys.getInt(1));

			}

		} catch (SQLException e) {
			exam = new Model_Exam();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exam;

	}

	/**
	 * 
	 * @param id
	 * @param percent
	 * @param grade
	 */
	public void setTestResult(int id, int percent, int grade) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SET_RESULT);
			pstm.setFloat(1, Float.valueOf(percent));
			pstm.setInt(2, grade);
			pstm.setInt(3, id);
			pstm.execute();

		} catch (SQLException e) {

		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns all exams by item id
	 * 
	 * @param int id
	 * @return List<Model_Exam>
	 */
	public List<Model_Exam> getAllByItemId(int id) {
		Connection con = null;
		PreparedStatement pstm = null;
		Model_Exam exam = null;
		List<Model_Exam> exams = new ArrayList<Model_Exam>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_BY_ITEM_ID);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				exam = new Model_Exam();
				exam.setId(rs.getInt(1));
				exam.setTest_id(rs.getInt(2));
				exam.setStudent_id(rs.getInt(3));
				exam.setItem_id(rs.getInt(4));
				exam.setDate_time(rs.getString(5));
				exam.setGrade(rs.getInt(6));
				exam.setPercent(rs.getFloat(7));

				exams.add(exam);
			}

		} catch (SQLException e) {
			exams = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exams;
	}

	/**
	 * Returns all exams by item id
	 * 
	 * @param int id
	 * @return List<Model_Exam>
	 */
	public List<Model_Exam> getAllPassedByItemId() {
		Connection con = null;
		PreparedStatement pstm = null;
		Model_Exam exam = null;
		List<Model_Exam> exams = new ArrayList<Model_Exam>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_ALL_PASSED);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				exam = new Model_Exam();
				exam.setId(rs.getInt(1));
				exam.setTest_id(rs.getInt(2));
				exam.setStudent_id(rs.getInt(3));
				exam.setItem_id(rs.getInt(4));
				exam.setDate_time(rs.getString(5));
				exam.setGrade(rs.getInt(6));
				exam.setPercent(rs.getFloat(7));

				exams.add(exam);
			}

		} catch (SQLException e) {
			exams = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exams;
	}

	/**
	 * 
	 * @param student_id
	 * @param test_id
	 * @return
	 */
	public Model_Exam getByStudentAndTest(int student_id, int test_id) {
		Connection con = null;
		PreparedStatement pstm = null;
		Model_Exam exam = null;

		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_BY_STUDENT_AND_TEST);
			pstm.setInt(1, student_id);
			pstm.setInt(2, test_id);
			pstm.execute();
			rs = pstm.getResultSet();

			while (rs.next()) {

				exam = new Model_Exam();
				exam.setId(rs.getInt(1));
				exam.setTest_id(rs.getInt(2));
				exam.setStudent_id(rs.getInt(3));
				exam.setItem_id(rs.getInt(4));
				exam.setDate_time(rs.getString(5));
				exam.setGrade(rs.getInt(6));
				exam.setPercent(rs.getFloat(7));

			}

		} catch (SQLException e) {
			exam = null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exam;
	}

}
