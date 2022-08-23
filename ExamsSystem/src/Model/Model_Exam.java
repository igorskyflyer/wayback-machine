package Model;


/**
 * 
 * @author Maja Devic
 *
 */
public class Model_Exam {
	
	private int id;
	private int test_id;
	private int student_id;
	private int item_id;
	private String date_time;
	private int grade;
	private float percent;
	
	/**
	 * Default Constructor
	 */
	public Model_Exam(){
		this.id = 0;
		this.test_id = 0;
		this.student_id = 0;
		this.item_id = 0;
		this.date_time = "";
		this.percent = 0;
		this.grade = 0;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	
	

}
