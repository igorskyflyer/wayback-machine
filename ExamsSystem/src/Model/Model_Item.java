package Model;

public class Model_Item {

	private int id;
	private String name;
	private int professor;
	private int course;

	public Model_Item() {
		id = 0;
		name = "";
		professor = 0;
		course = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfessor() {
		return professor;
	}

	public void setProfessor(int professor) {
		this.professor = professor;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

}
