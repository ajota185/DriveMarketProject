package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

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

/**
 * Servlet implementation class ServletAddOrder
 */
@WebServlet("/ServletAddOrder")
public class ServletAddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null && !user.isAdmin() && user.getShoppingCart()!=null) {
			Order order = new Order();
			Date date = new Date(System.currentTimeMillis());
			order.setDate(date);
			Time time = new Time(System.currentTimeMillis());
			order.setHour(time);
			order.setUser(user);
			
			
			
			OrderDAO orderDAO = new OrderDAO();
			int id_order = orderDAO.addOrder(order);
			order.setId_order(id_order);
			
			user.getShoppingCart().setOrder(order);
			
			
			
			ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
			for(int i=0; i<user.getShoppingCart().getProducts().size(); i++) {
				shoppingCartDAO.doOrder(user.getNickName(),user.getShoppingCart().getProducts().get(i).getId_prod(), id_order);
			}
			
			if(user.getOrders()==null) {
				user.setOrders(new ArrayList<ShoppingCart>());
			}
			user.getOrders().add(user.getShoppingCart());
			user.setShoppingCart(null);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("index.jsp");
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
