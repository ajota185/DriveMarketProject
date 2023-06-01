package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.image.Image;
import model.image.ImageDAO;
import model.product.Product;
import model.product.ProductDAO;

/**
 * Servlet implementation class ServletProduct
 */
@WebServlet(name="/ServletProduct", value="/ServletProduct")
public class ServletProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("id_prod")!=null) {
			int id_prod = Integer.parseInt(request.getParameter("id_prod"));
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.searchProduct(id_prod);
			request.setAttribute("product", product);
			
			ImageDAO imageDAO = new ImageDAO();
			ArrayList<Image> images = imageDAO.getImagesByProduct(id_prod);
			request.setAttribute("images", images);
			
			RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/product.jsp");
			requestDispatcher.forward(request, response);
		}else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
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
