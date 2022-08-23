package Model;


/**
 * 
 * @author Maja Devic
 *
 */
public class Model_File {
	
	private int id;
	private String name;
	private int item;
	
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

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public Model_File(){
		this.id = 0;
		this.name = "";
		this.item = 0;
	}

	

}
