package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Factory.Factory_Item;
import Factory.Factory_ItemTestRel;
import Factory.Factory_Test;
import Model.Model_ItemTestRel;

/**
 * Servlet implementation class AdminService
 */
@WebServlet("/AdminService")
public class AdminService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminService() {
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

		String msg = "";

		int item_id = Integer.valueOf((String) request.getParameter("item"));
		int test_id = Integer.valueOf((String) request.getParameter("test"));

		Factory_ItemTestRel itr = new Factory_ItemTestRel();

		if (!itr.exists(item_id, test_id)) {

			String item_name = Factory_Item.getInstance().getById(item_id)
					.getName();
			String test_name = Factory_Test.getInstance().getById(String.valueOf(test_id))
					.getName();

			Model_ItemTestRel itr_obj = new Model_ItemTestRel();
			itr_obj.setItem_id(item_id);
			itr_obj.setTest_id(test_id);
			itr_obj.setItem_name(item_name);
			itr_obj.setTest_name(test_name);

			if (itr.insert(itr_obj)) {
				msg = "Relacija uspesno unesena";
			} else {
				msg = "Neuspesan unos u bazu";
			}

		} else {
			msg = "Relacija vec postoji";
		}

		request.setAttribute("msg", msg);
		request.getRequestDispatcher("admin_edit.jsp").forward(request,
				response);

	}
}
