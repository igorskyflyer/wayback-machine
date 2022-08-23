package Service;

import java.util.List;

import Model.Model_Course;



/**
 * 
 * @author Maja Devic
 *
 */
public class Service_Course {
	
	
	/**
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	public static Model_Course selectById(List<Model_Course> list, int id) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return list.get(i);
			}
		}

		return new Model_Course();

	}

}
