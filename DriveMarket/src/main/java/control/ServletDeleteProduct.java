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

import model.image.ImageDAO;
import model.product.Product;
import model.product.ProductDAO;
import model.user.User;

/**
 * Servlet implementation class ServletDeleteUser
 */
@WebServlet(name="/ServletDeleteProduct", value="/ServletDeleteProduct")
public class ServletDeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteProduct() {
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
			int id_prod = Integer.parseInt(request.getParameter("id_prod"));
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.searchProduct(id_prod);
			if(product!=null) {
				ImageDAO imageDAO = new ImageDAO();
				imageDAO.deleteImages(id_prod);
				productDAO.deleteProduct(id_prod);
			}
		}
		response.sendRedirect("ServletHome");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}