package model.product;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductDAOMethod {
	public Product searchProduct(int id_prod);
	public ArrayList<Product> getAllProducts();
	public void deleteProdotto(int id_prod);
    public void insertProdotto(Product p);
    public void updateProdotto(Product p, int id_prod);
}
