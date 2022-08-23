package Service;

import java.util.List;

import Model.Model_Item;
import Model.Model_Student;
/**
 * 
 * @author Maja Devic
 *
 */
public class Service_Item {
	
	/**
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	public static Model_Item selectById(List<Model_Item>list,int id){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return list.get(i);
			}
		}

		return new Model_Item();
	}

}
