package control;

import model.product.Product;
import model.product.ProductDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletHome", value = "/ServletHome",loadOnStartup = 0)
public class ServletHome extends HttpServlet{

//	@Override
//	public void init() throws ServletException{
//		super.init();
//		ProductDAO productDAO = new ProductDAO();
//		ArrayList<Product> products = productDAO.getAllProducts();
//		getServletContext().setAttribute("products", products);
//		
//	}
	
	@Override
	public void init() throws ServletException{
		super.init();
		ProductDAO productDAO = new ProductDAO();
		ArrayList<Product> products = productDAO.getAllProducts();
		getServletContext().setAttribute("products", products);
		
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		ArrayList<Product> products = productDAO.getAllProducts();
		request.setAttribute("products", products);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	
}
