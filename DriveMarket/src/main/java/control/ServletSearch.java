package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.product.Product;
import model.product.ProductDAO;

/**
 * Servlet implementation class ServletSearch
 */
@WebServlet("/ServletSearch")
public class ServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		if(text==null ) {
			ProductDAO productDAO = new ProductDAO();
			ArrayList<Product> products = productDAO.getAllProducts();
			
			request.setAttribute("products", products);
			RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/pagine/searchCar.jsp");
			requestDispatcher.forward(request, response);
		}else {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			String result="";
			
			
			ProductDAO productDAO = new ProductDAO();
			ArrayList<Product> products;
			if(text.isEmpty()) {
				products = productDAO.getAllProducts();
			}else {
				products = productDAO.getProductsByText(text);
			}
			
			JSONObject json = new JSONObject();
			json.put("products", products);
			out.print(json.toString());
			
			
			
			
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
