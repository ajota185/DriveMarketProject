package model.image;

import java.util.ArrayList;

public interface ImageDAOMethod {
	public ArrayList<Image> getImagesByProduct(int id_prod);
}
