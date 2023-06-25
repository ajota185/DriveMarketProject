package control;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.order.Order;
import model.order.OrderDAO;
import model.shoppingCart.ShoppingCart;
import model.shoppingCart.ShoppingCartDAO;
import model.user.User;
import model.user.UserDAO;

/**
 * Servlet implementation class ServletOrders
 */
@WebServlet(name="/ServletOrders", value="/ServletOrders")
public class ServletOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		
		User user=(User) session.getAttribute("user");
		
		if(user!=null && user.isAdmin()) {
			String nick = request.getParameter("nick");
			String date_start = request.getParameter("date_start");
			String date_finish = request.getParameter("date_finish");
			
			
			if(nick!=null&& date_start!=null && date_finish!=null) {
				if(!nick.isEmpty()&&!date_start.isEmpty()&&!date_finish.isEmpty()) {
					date_start += ":00";
					date_finish += ":00";
					
					Timestamp date_s = Timestamp.valueOf(date_start.replace("T", " "));
					Timestamp date_f = Timestamp.valueOf(date_finish.replace("T", " "));
					
					UserDAO userDAO = new UserDAO();
					User usern = userDAO.searchUser(nick);
					
					OrderDAO orderDAO = new OrderDAO();
					ArrayList<Order> orders= orderDAO.getOrdersByUserAndDate(usern,date_s,date_f);
					ArrayList<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
					if(orders!=null) {
						for(Order order : orders) {
							ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
							shoppingCarts.add(shoppingCartDAO.getShoppingCartByOrder(order));
						}
						if(!shoppingCarts.isEmpty()) {
							usern.setOrders(shoppingCarts);
						}
					}
					ArrayList<User> users = new ArrayList<User>();
					users.add(usern);
					
					request.setAttribute("users", users);
					RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/allOrders.jsp");
					requestDispatcher.forward(request, response);
				}else {
					UserDAO userDAO = new UserDAO();
					ArrayList<User> users = userDAO.getAllUsers();
					for(int i=0; i<users.size(); i++) {
						if(!users.get(i).isAdmin()) {
							OrderDAO orderDAO = new OrderDAO();
							ArrayList<Order> orders= orderDAO.getOrdersByUser(users.get(i));
							ArrayList<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
							if(orders!=null) {
								for(Order order : orders) {
									ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
									shoppingCarts.add(shoppingCartDAO.getShoppingCartByOrder(order));
								}
								if(!shoppingCarts.isEmpty()) {
									users.get(i).setOrders(shoppingCarts);
								}
							}
						}
					}
					
					
					request.setAttribute("users", users);
					RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/allOrders.jsp");
					requestDispatcher.forward(request, response);
				}
				
			}else {
				
				UserDAO userDAO = new UserDAO();
				ArrayList<User> users = userDAO.getAllUsers();
				for(int i=0; i<users.size(); i++) {
					if(!users.get(i).isAdmin()) {
						OrderDAO orderDAO = new OrderDAO();
						ArrayList<Order> orders= orderDAO.getOrdersByUser(users.get(i));
						ArrayList<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
						if(orders!=null) {
							for(Order order : orders) {
								ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
								shoppingCarts.add(shoppingCartDAO.getShoppingCartByOrder(order));
							}
							if(!shoppingCarts.isEmpty()) {
								users.get(i).setOrders(shoppingCarts);
							}
						}
					}
				}
				
				
				request.setAttribute("users", users);
				RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/allOrders.jsp");
				requestDispatcher.forward(request, response);
			}
		}else {
			response.sendRedirect("ServletHome");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
