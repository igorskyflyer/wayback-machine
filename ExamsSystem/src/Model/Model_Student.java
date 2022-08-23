package Model;

import Factory.Factory_Exam;

public class Model_Student {

	private int id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String index_num;
	private String address;
	private String course_name;
	private int course;

	public Model_Student() {
		this.id = 0;
		this.username = "";
		this.password = "";
		this.name = "";
		this.surname = "";
		this.email = "";
		this.phone = "";
		this.index_num = "";
		this.address = "";
		this.course= 0;
		this.course_name = "";
	}

	public Model_Student(String username, String password, String name,
			String surname, String email, String phone, String index_num,
			String address, int course) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.index_num = index_num;
		this.address = address;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndex_num() {
		return index_num;
	}

	public void setIndex_num(String index_num) {
		this.index_num = index_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}
	
	public String getCourseName() {
		return this.course_name;
	}

	public void setCourseName(String course_name) {
		this.course_name = course_name;
	}
	
	/**
	 * If the test is aveliable returns null,<br>
	 * else it returns Model_Exam
	 * @param test
	 * @return
	 */
	public Model_Exam isTestAveliable(int test_id){
		
		Factory_Exam  fe = new Factory_Exam();
		return fe.getByStudentAndTest(this.id, test_id);
		
		}
}
