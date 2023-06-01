package model.image;

public class Image {
	private int id_img;
	private String path;
	private int id_prod;
	
	public Image() {
		
	}
	
	public Image(int id_img, String path, int id_prod, String pie) {
		this.id_img = id_img;
		this.path = path;
		this.id_prod = id_prod;
		this.pie = pie;
	}

	public int getId_img() {
		return id_img;
	}
	public void setId_img(int id_img) {
		this.id_img = id_img;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
	}
	private String pie;
}
