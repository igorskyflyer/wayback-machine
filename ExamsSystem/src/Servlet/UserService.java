package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Factory.Factory_Administrator;
import Factory.Factory_Student;
import Model.Model_Administrator;
import Model.Model_Student;

/**
 * Servlet implementation class UserService
 */
@WebServlet("/UserService")
public class UserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String return_url_admin = "administratorLogIn.jsp";
	public static final String return_url_student = "index.jsp";
	public static final String url_student = "student_prikaz.jsp";
	public static final String url_admin = "admin_prikaz.jsp";
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserService() {
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

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String log_type = request.getParameter("log_type");

		if (!user.matches("") && !pass.matches("")) {

			if (log_type.matches("student")) {
				Factory_Student factory_student = new Factory_Student();

				Model_Student student = null;
				student = factory_student.validateLogin(user, pass);
				if (student != null) {

					HttpSession sesija = request.getSession();
					sesija.setAttribute("Student", student);

					request.getRequestDispatcher("student_prikaz.jsp").forward(
							request, response);

				} else {
					request.setAttribute("msg",
							"Korisnicko ime ili sifra su pogresni");
					request.getRequestDispatcher(return_url_student).forward(request,
							response);
				}
			} else {
				Factory_Administrator adminDAO = new Factory_Administrator();
				Model_Administrator admin = null;

				admin = adminDAO.validateLogin(user, pass);
				if (admin != null) {

					HttpSession sesija = request.getSession();
					sesija.setAttribute("Admin", admin);

					request.getRequestDispatcher("admin_view.jsp").forward(
							request, response);

				} else {
					request.setAttribute("msg",
							"Korisnicko ime ili sifra su pogresni");
					request.getRequestDispatcher("admin_login").forward(request,
							response);
				}

			}

		} else {

			request.setAttribute("msg", "Uneti sve podatke!");
			if(log_type.matches("student")){
				request.getRequestDispatcher(return_url_student)
				.forward(request, response);
			}else{
				request.getRequestDispatcher(return_url_admin)
				.forward(request, response);
			}
			
		}
		
		
		

	}

}
