package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Maja Devic
 *
 */
public class Model_Test {

	private int id;
	private String name;
	private int item_id;
	private String item_name;
	private String titles;
	private String values;
	private String answers;
	
	
	public Model_Test(){
		this.id = 0;
		this.name = "";
		this.item_id = 0;
		this.item_name = "";
		this.titles = "";
		this.values = "";
		this.answers = "";
	}
	
	
	
	
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	/**
	 * Returns the id
	 * @return int
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the item id
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getPublic_html() {
		return titles;
	}
	public void setPublic_html(String titles) {
		this.titles = titles;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	public List<String> getTitlesList(){
		String temp[] = this.titles.split(";");
		List<String>list = new ArrayList<String>();
		for(int i = 0; i < temp.length ; i++){
			list.add(temp[i]);
		}
		return list;
		
		
	}
	
	public  List<String> getValuesList(){
		String temp[] = this.values.split(";");
		List<String>list = new ArrayList<String>();
		for(int i = 0; i < temp.length ; i++){
			list.add(temp[i]);
		}
		return list;
		
		
	}
	
	public static List<String> getValuesSubList(String values){
		values = values.replace("$", ";");
		String temp[] = values.split(";");
		List<String>list = new ArrayList<String>();
		for(int i = 0; i < temp.length ; i++){
			list.add(temp[i].replace("`", "' "));
		}
		return list;
		
		
	}
	
	
	
}
