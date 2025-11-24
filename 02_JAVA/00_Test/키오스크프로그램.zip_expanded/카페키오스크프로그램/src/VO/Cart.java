package VO;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> items = new ArrayList<>();

	public void addItem(Menu menu, int quantity) {
		for (CartItem item : items) {
			if (item.getMenu().getId() == menu.getId()) {
				// 같은 메뉴 있으면 수량만 증가
				int newQuantity = item.getQuantity() + quantity;

				// indexOf >> 해당 객체의 첫 위치를 가리킨다!!
				// 바꾸고 싶은 item 위치에 새로 추가된 수량으로 설정해준 객체인 CartItem(menu, newQuantity)를 넣어준다.
				items.set(items.indexOf(item), new CartItem(menu, newQuantity));
				return;
			}
		}
		items.add(new CartItem(menu, quantity));
	}

	public int getTotalPrice() {
		int total = 0;
		for (CartItem item : items) {
			total += item.getTotalPrice();
		}
		return total;
	}

	public void removeItem(int index) {
		if (index >= 0 && index < items.size()) {
			items.remove(index);
		}
	}

	public List<CartItem> getItems() {
		return items;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void clearCart() {
		items.clear(); // items는 Cart가 가지고 있는 List<CartItem> 객체라고 가정
	}
}
