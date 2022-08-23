package Service;

import java.util.List;
import Model.Model_Test;

/**
 * 
 * @author Maja Devic
 *
 */
public class Service_Test {

	
	/**
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	public static Model_Test selectById(List<Model_Test> list, int id) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return list.get(i);
			}
		}

		return new Model_Test();

	}
	
	
}
