package VO;

public class CartItem {
	private Menu menu;
	private int quantity;

	public CartItem(Menu menu, int quantity) {
		this.menu = menu;
		this.quantity = quantity;
	}

	public Menu getMenu() {
		return menu;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getTotalPrice() {
		return menu.getPrice() * quantity;
	}

	@Override
	public String toString() {
		return String.format("%s x %d = %d원", menu.getName(), quantity, getTotalPrice());
	}
}