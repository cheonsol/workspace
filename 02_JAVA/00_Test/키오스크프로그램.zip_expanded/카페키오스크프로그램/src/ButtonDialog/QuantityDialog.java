package ButtonDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 선택한 메뉴에 대한 수량을 -,+ 버튼으로 조절하는 역할을 하는 클래스이다.
public class QuantityDialog extends JDialog {
	private int quantity = 1; // 초기 수량 1
	private boolean confirmed = false;

	public QuantityDialog(JPanel menuPanel, String menuName) {
		
		//menuPanel을 포함하고 있는 가장 가까운 Window를 찾아주는 함수이다, 다이어로그창의 제목을 설정해준다.
		// 현재 다이얼로그창이 어느창에 종속되는지 , 창 제목 , 모달 다이얼로그로서 다이얼로그가 떠있는동안 다른 창을 클릭하거나 조작할 수 없다.
		super((JFrame) SwingUtilities.getWindowAncestor(menuPanel), menuName + " 수량 선택", true); 
 		setLayout(new FlowLayout());
		setSize(270, 80);
		setLocationRelativeTo(menuPanel);

		JLabel quantityLabel = new JLabel(String.valueOf(quantity)); //String.valueOf 숫자를 문자열로 바꾼다.
		quantityLabel.setFont(new Font("굴림", Font.BOLD, 24));

		JButton plusBtn = new JButton("+");
		JButton minusBtn = new JButton("-");

		plusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantity++;
				quantityLabel.setText(String.valueOf(quantity));
			}
		});

		minusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (quantity > 1) {
					quantity--;
					quantityLabel.setText(String.valueOf(quantity));
				}
			}
		});

		JButton okBtn = new JButton("확인");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = true;
				setVisible(false); // 창이 화면에서 사라진다
			}
		});

		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = false;
				setVisible(false);
			}
		});

		add(minusBtn);
		add(quantityLabel);
		add(plusBtn);
		add(okBtn);
		add(cancelBtn);
	}

	public int getQuantity() {
		return confirmed ? quantity : -1; // 취소 시 -1 반환
	}
}
