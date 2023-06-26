package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.product.Product;
import model.product.ProductDAO;
import model.shoppingCart.ShoppingCart;
import model.shoppingCart.ShoppingCartDAO;
import model.user.User;
import model.user.UserDAO;

/**
 * Servlet implementation class AddCarrello
 */
@WebServlet(name="/ServletAddShoppingCart", value="/ServletAddShoppingCart")
public class ServletAddShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		
		if(user!=null && !user.isAdmin()) {
			int id_prod = Integer.parseInt(request.getParameter("id_prod"));
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.searchProduct(id_prod);
			if(product!=null) {
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				if(quantity>0) {
					if(user.getShoppingCart()==null) {
						ShoppingCart shoppingCart = new ShoppingCart();
						ArrayList<Product> ini1 = new ArrayList<Product>();
						ini1.add(product);
						shoppingCart.setProducts(ini1);
						ArrayList<Integer> ini2 = new ArrayList<Integer>();
						ini2.add(quantity);
						shoppingCart.setQuantity(ini2);
						shoppingCart.setUser(user);
						
						
						ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
						shoppingCartDAO.addProductToShoppingCart(user.getNickName(), id_prod, quantity, product.getPrice());
						user.setShoppingCart(shoppingCart);
						
					}else {
						ShoppingCart shoppingCart = user.getShoppingCart();
						if(shoppingCart.getProducts().contains(product)) {
							int index =shoppingCart.getProducts().indexOf(product);
							shoppingCart.getQuantity().set(index, quantity);
							ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
							shoppingCartDAO.updateProductToShoppingCart(user.getNickName(), id_prod, quantity, product.getPrice());
						}else {
							shoppingCart.getProducts().add(product);
							shoppingCart.getQuantity().add(quantity);
							ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
							shoppingCartDAO.addProductToShoppingCart(user.getNickName(), id_prod, quantity, product.getPrice());
						}
					}
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pagine/shoppingCart.jsp");
					dispatcher.forward(request, response);
				}else {
					if(user.getShoppingCart()!=null) {
						ShoppingCart shoppingCart = user.getShoppingCart();
						if(shoppingCart.getProducts().contains(product)) {
							int index =shoppingCart.getProducts().indexOf(product);
							shoppingCart.getProducts().remove(index);
							shoppingCart.getQuantity().remove(index);
							
							ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
							shoppingCartDAO.deleteProductToShoppingCart(user.getNickName(), id_prod);
							
							
							
							if(shoppingCart.getProducts().isEmpty()) {
								user.setShoppingCart(null);
								response.sendRedirect("ServletHome");
							}else {
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pagine/shoppingCart.jsp");
								dispatcher.forward(request, response);
							}
						}
					}else {
						response.sendRedirect("ServletHome");
					}
				}
			}
			
			
		}else {
			response.sendRedirect("ServletHome");
		}
		
	}

}
