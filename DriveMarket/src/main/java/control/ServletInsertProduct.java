package control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.image.Image;
import model.image.ImageDAO;
import model.product.Product;
import model.product.ProductDAO;
import model.user.User;
import model.user.UserDAO;

/**
 * Servlet implementation class ServletInsertProduct
 */
@WebServlet(name="/ServletInsertProduct", value="/ServletInsertProduct")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class ServletInsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR = "immagini";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertProduct() {
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
		if(user!=null && user.isAdmin()) {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String link = request.getParameter("link");
			String main_photo="";
			ArrayList<String> images = new ArrayList<String>();
			List<String> errors = new ArrayList<>();
			
			
			
			
			if(name== null || name.isEmpty()) {
				errors.add("There is no name.");
			}
			if(price == null || price.isEmpty()) {
				errors.add("There is no price.");
			}
			if(description == null || description.isEmpty()) {
				errors.add("There is no description.");
			}
			if(link==null||link.isEmpty()) {
				link="";
			}
			
			String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;
//			String savePath = "/home/antonio/Documentos/Erasmus/Asignaturas/Segundo Cuatri/Tecnologie Software per ir Web/DriveMarketProject/DriveMarket/WebContent/"+SAVE_DIR;
			
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			if (request.getParts() != null && request.getParts().size() > 0) {
				for (Part part : request.getParts()) {
					if(part.getName().equals("main_photo")) {
						String fileName = extractFileName(part);
						if (fileName != null && !fileName.equals("")) {
							part.write(savePath + File.separator + fileName);
							main_photo = "./immagini/"+fileName;
//							System.out.println(savePath + File.separator + fileName);
						} else {
							errors.add("Selection one file");

						}
					}
					
					if(part.getName().equals("images")) {
						String fileName = extractFileName(part);
						if (fileName != null && !fileName.equals("")) {
							part.write(savePath + File.separator + fileName);
							images.add(fileName);
							
//							System.out.println(savePath + File.separator + fileName);
						}
					}
					
					
				}
			}
			
			if(errors.isEmpty()) {
				Product product = new Product();
				product.setName(name);
				product.setPrice(Float.parseFloat(price));
				product.setDescription(description);
				product.setLink(link);
				product.setMain_photo(main_photo);
				
				
				ProductDAO productDAO = new ProductDAO();
				int id_prod = productDAO.insertProduct(product);
				
				Image img = new Image();
				img.setId_prod(id_prod);
				img.setPath(main_photo);
				
				
				
				for(String image : images) {
					Image img2 = new Image();
					img2.setId_prod(id_prod);
					img2.setPath("./immagini/"+image);
					ImageDAO imageDAO = new ImageDAO();
					imageDAO.insertImage(img2);
				}
				
				
				
				response.sendRedirect("ServletHome");
			}else {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pagine/insertProduct.jsp");
				dispatcher.forward(request, response);
			}
			
		}else {
			response.sendRedirect("ServletHome");
		}
		
		
		
	}
	
	private String extractFileName(Part part) {
		// content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}
