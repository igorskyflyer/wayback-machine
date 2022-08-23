package Model;

public class Model_Administrator {

	private int id;
	private String user;
	private String pass;
	private String name;
	private String surname;
	
	public Model_Administrator(){
		this.id = 0;
		this.user = "";
		this.pass = "";
		this.name = "";
		this.surname = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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
	
	

}
