package model.product;

public class Product {
	private int id_prod;
	private String name;
	private float price;
	private String description;
	private String main_photo;
	private String link;
	
	public Product() {
		
	}
	
	public Product(int id_prod, String name, float price, String description, String main_photo, String link) {
		this.id_prod = id_prod;
		this.name = name;
		this.price = price;
		this.description = description;
		this.main_photo = main_photo;
		this.link = link;
	}

	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMain_photo() {
		return main_photo;
	}

	public void setMain_photo(String main_photo) {
		this.main_photo = main_photo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
