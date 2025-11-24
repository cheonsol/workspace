package VO;

public class Menu {
	private int id; // 메뉴번호
	private String name; // 메뉴이름
	private String category; // 메뉴 종류
	private int price; // 메뉴 가격

	public Menu(int id, String name, String category, int price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "("+ category + ")" + name + " : " + price + "원";
	}
}
