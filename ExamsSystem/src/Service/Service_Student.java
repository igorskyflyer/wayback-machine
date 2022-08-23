package Service;

import java.util.List;

import Model.Model_Student;

/**
 * 
 * @author Maja Devic
 *
 */
public class Service_Student {

	/**
	 * Selects a Model_Student from the list for the given id.<br>
	 * If no student found, returns new ModelStudent object;
	 * 
	 * @param ArrayList
	 *            of students
	 * @param id
	 *            of the wanted student
	 * @return Model_Student
	 */
	public static Model_Student selectById(List<Model_Student> list, int id) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return list.get(i);
			}
		}

		return new Model_Student();

	}
	
	

}
