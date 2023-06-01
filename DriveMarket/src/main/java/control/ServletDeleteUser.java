package control;

import java.io.IOException;
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
 * Servlet implementation class ServletDeleteUser
 */
@WebServlet(name="/ServletDeleteUser", value="/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteUser() {
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
			User usern = userDAO.searchUser(nick);
			if(usern!=null) {
				if(usern.equals(user)) {
					session.removeAttribute("user");
				}
				userDAO.deleteUser(nick);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
				
			}else {
				errors.add("Nickname doesn't exist.");
				request.setAttribute("errors", errors);
				RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/deleteUser.jsp");
				requestDispatcher.forward(request, response);
			}
		}else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
		}
	}

}
