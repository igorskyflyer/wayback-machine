package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Factory.Factory_Student;
import Model.Model_Student;

/**
 * 
 * @author Maja Devic
 * 
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String index_num = request.getParameter("index_num");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String course = request.getParameter("course");

		if (!user.matches("") && !pass.matches("") && !index_num.matches("")
				&& !name.matches("") && !surname.matches("")
				&& !email.matches("") && !address.matches("")
				&& !phone.matches("") && !course.matches("")) {

			Model_Student student = new Model_Student(user, pass, name,
					surname, email, phone, index_num, address,
					Integer.valueOf(course));
			Factory_Student studentDAO = new Factory_Student();

			if (studentDAO.checkIndex_Num(student)) {

				request.setAttribute("msg", "Broj indeksa vec postoji");
				request.getRequestDispatcher("registration.jsp").forward(
						request, response);

			} else if (studentDAO.checkUsername(student)) {
				request.setAttribute("msg", "korisnicko ime vec psotoji");
				request.getRequestDispatcher("registration.jsp").forward(
						request, response);

			} else {
				if (studentDAO.insertStudent(student)) {

					request.setAttribute("msg", "Registracija uspesna");
					request.getRequestDispatcher("registration.jsp").forward(
							request, response);
				} else {

					request.setAttribute("msg", "Greška pri unosu");
					request.getRequestDispatcher("registration.jsp").forward(
							request, response);
				}
			}

		} else {

			request.setAttribute("msg", "Uneti sve podatke!");
			request.getRequestDispatcher("registration.jsp").forward(request,
					response);
		}
	}

}
