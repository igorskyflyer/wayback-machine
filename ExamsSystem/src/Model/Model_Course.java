package Model;

public class Model_Course {

	private int id;
	private String name;
	private String short_tname;
	
	public Model_Course(){
		id = 0;
		name = "";
		short_tname = "";
		
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
	public String getShort_name() {
		return short_tname;
	}
	public void setShort_name(String short_tname) {
		this.short_tname = short_tname;
	}
	
	
}
