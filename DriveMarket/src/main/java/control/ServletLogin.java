package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
 * Servlet implementation class ServletLogin
 */
@WebServlet(name="/ServletLogin", value="/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at:").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session= request.getSession();
		
		User user=(User) session.getAttribute("user");
		
		if(user == null) {
			String nick = request.getParameter("nick");
			String passw = request.getParameter("password");
			String hashPassw = toHash(passw);
			
			UserDAO userDAO = new UserDAO();
			user = (User) userDAO.searchUser(nick);
			
			
			if(user!=null) {
				if(user.getPassw().equals(hashPassw)) {
					session.setAttribute("user", user);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
				}else {
					request.setAttribute("error", "Incorrect Password");
					RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/login.jsp");
					requestDispatcher.forward(request, response);
				}
			}else {
				request.setAttribute("error", "User doesn't find");
				RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/login.jsp");
				requestDispatcher.forward(request, response);
			}
			
		}else {
			 response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
		}
	}
	
	
	private String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                                  (hash[i] & 0xFF) | 0x100 
                              ).toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashString;
    }

}
