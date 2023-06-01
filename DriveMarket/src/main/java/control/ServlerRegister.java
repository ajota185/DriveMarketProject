package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
 * Servlet implementation class ServlerRegister
 */
@WebServlet(name="/ServletRegister", value="/ServletRegister")
public class ServlerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServlerRegister() {
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
		
		
		if(user == null) {
			String nick = request.getParameter("nick");
			String passw = request.getParameter("password");
			String email = request.getParameter("email");
			List<String> errors = new ArrayList<>();
			
			
			
			
			if(nick== null || nick.trim().isEmpty()) {
				errors.add("There is no nickName.");
			}
			if(passw == null || passw.isEmpty()) {
				errors.add("There is no password.");
			}
			if(email == null || email.isEmpty()) {
				errors.add("There is no email.");
			}
			
			if(errors.isEmpty()) {
				user = new User();
				user.setNickName(nick);
				String hashPassw = toHash(passw);
				user.setPassw(hashPassw);
				user.setEmail(email);
				user.setAdmin(false);
				UserDAO userDAO = new UserDAO();
				try {
					userDAO.insertUser(user);
					session.setAttribute("user", user);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					errors.add("User exists.");
					RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/register.jsp");
					request.setAttribute("errors", errors);
					requestDispatcher.forward(request, response);
				}
			}
			else {
				RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/register.jsp");
				request.setAttribute("errors", errors);
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
