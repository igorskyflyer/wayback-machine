package Model;


/**
 * 
 * @author Maja Devic
 *
 */
public class Model_ItemTestRel {
	
	private int id;
	private int test_id;
	private int item_id;
	private String test_name;
	private String item_name;
	
	/**
	 * Default Constructor
	 */
	public Model_ItemTestRel(){
		this.id = 0;
		this.test_id = 0;
		this.item_id = 0;
		this.test_name = "";
		this.item_name = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getTest_name() {
		return test_name;
	}

	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	

}
