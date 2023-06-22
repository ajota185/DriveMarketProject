package model.image;

import java.util.ArrayList;

public interface ImageDAOMethod {
	public ArrayList<Image> getImagesByProduct(int id_prod);
	public void insertImage(Image image);
	public void updateImagesOfProduct(ArrayList<Image> images, int id_prod);
	public void deleteImages(int id_prod);
}
