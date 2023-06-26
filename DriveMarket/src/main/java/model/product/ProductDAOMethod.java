package model.product;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductDAOMethod {
	public Product searchProduct(int id_prod);
	public ArrayList<Product> getAllProducts();
	public ArrayList<Product> getProductsByText(String text);
	public void deleteProduct(int id_prod);
    public int insertProduct(Product p);
    public void updateProduct(Product p, int id_prod);
}
