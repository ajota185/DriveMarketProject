package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.User;

/**
 * Servlet implementation class ServletLink
 */
@WebServlet(name = "ServletLink", value = "/ServletLink")
public class ServletLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLink() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option=request.getParameter("option");
//        HttpSession session= request.getSession();
//        User utente=(User) session.getAttribute("utente");
//        System.out.println("Sessione " );
        String pagina="";
        switch (option){

            case "home": pagina="/index.jsp";
                break;
            case "login" :
            	pagina="WEB-INF/pagine/login.jsp";
                break;
            case "register":
            	pagina="WEB-INF/pagine/register.jsp";
            	break;
            case "updateUser":
            	pagina="WEB-INF/pagine/updateUser.jsp";
            	break;
            case "changeUser":
            	pagina="WEB-INF/pagine/changeUser.jsp";
            	break;
            case "deleteUser":
            	pagina="WEB-INF/pagine/deleteUser.jsp";
            	break;
          /*  default:pagina="webapp/index.jsp";
                break;*/
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher(pagina);
        dispatcher.forward(request,response);
		
	}

}
