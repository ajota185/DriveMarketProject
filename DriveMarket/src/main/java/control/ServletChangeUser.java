package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.User;
import model.user.UserDAO;

/**
 * Servlet implementation class ChangeUser
 */
@WebServlet(name="/ServletChangeUser", value="/ServletChangeUser")
public class ServletChangeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletChangeUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		
		
		if(user!=null && user.isAdmin()) {
			String nick = request.getParameter("nick");
			String admin = request.getParameter("type_user");
			List<String> errors = new ArrayList<>();
			UserDAO userDAO = new UserDAO();
			if(userDAO.searchUser(nick)!=null) {
				boolean type;
				if(admin.equals("admin")) {
					type = true;
				}else {
					type=false;
				}
				
				userDAO.changeUser(nick,type);
				response.sendRedirect("ServletHome");
				
			}else {
				errors.add("Nickname doesn't exist.");
				request.setAttribute("errors", errors);
				RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/changeUser.jsp");
				requestDispatcher.forward(request, response);
			}
		}else {
			response.sendRedirect("ServletHome");
		}
	}
}
