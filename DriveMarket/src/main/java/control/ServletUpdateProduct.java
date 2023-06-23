package control;

import java.io.File;
import java.io.IOException;
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

/**
 * Servlet implementation class ServletUpdateProduct
 */
@WebServlet(name="/ServletUpdateProduct", value="/ServletUpdateProduct")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class ServletUpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR = "immagini";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		User user=(User) session.getAttribute("user");
		
		if(user!=null && user.isAdmin() && request.getParameter("id_prod")!=null) {
			
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String link = request.getParameter("link");
			String main_photo="";
			ArrayList<Image> images = new ArrayList<Image>();
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
			
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
//			
			if (request.getParts() != null && request.getParts().size() > 0) {
				for (Part part : request.getParts()) {
					if(part.getName().equals("main_photo")) {
						String fileName = extractFileName(part);
						if (fileName != null && !fileName.equals("")) {
							part.write(savePath + File.separator + fileName);
							main_photo = "./immagini/"+fileName;
//							System.out.println(savePath + File.separator + fileName);
						}
					}
					
					if(part.getName().equals("images")) {
						String fileName = extractFileName(part);
						if (fileName != null && !fileName.equals("")) {
							part.write(savePath + File.separator + fileName);
							Image img = new Image();
							img.setPath("./immagini/"+fileName);
							images.add(img);
							
//							System.out.println(savePath + File.separator + fileName);
						}
					}
					
					
				}
			}
			
			
			int id_prod = Integer.parseInt(request.getParameter("id_prod"));
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.searchProduct(id_prod);
			if(errors.isEmpty()) {
				
				product.setName(name);
				product.setPrice(Float.parseFloat(price));
				product.setDescription(description);
				product.setLink(link);
				
				
				if(main_photo.equals("")) {
					main_photo = product.getMain_photo();
				}
				product.setMain_photo(main_photo);
				
				productDAO.updateProduct(product, id_prod);
				
				
				ImageDAO imageDAO = new ImageDAO();
				if(!images.isEmpty()) {
					
					for(Image img : images) {
						img.setId_prod(id_prod);
					}
					imageDAO.updateImagesOfProduct(images,id_prod);
				}
				
				
				
				
				request.setAttribute("product", product);
				images = imageDAO.getImagesByProduct(id_prod);
				request.setAttribute("images", images);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pagine/product.jsp");
				dispatcher.forward(request, response);
//				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
			}else {
				request.setAttribute("errors", errors);
				request.setAttribute("product", product);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pagine/updateProduct.jsp");
				dispatcher.forward(request, response);
//				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
			}
		}else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/index.jsp"));
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
