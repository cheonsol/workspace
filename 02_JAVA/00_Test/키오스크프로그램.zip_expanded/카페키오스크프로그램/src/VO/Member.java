package VO;

public class Member {
	private String phoneNumber; // 전화번호
	private int stampCount; // 도장 개수
	private boolean hasCoupon; // 쿠폰 보유 여부

	// 생성자, getter/setter 생략

	public Member() {

		super();
	}

	public Member(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Member(String phoneNumber, int stampCount, boolean hasCoupon) {
		this.phoneNumber = phoneNumber;
		this.stampCount = stampCount;
		this.hasCoupon = hasCoupon;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getStampCount() {
		return stampCount;
	}

	public boolean hasCoupon() {
		return hasCoupon;
	}

	public void setStampCount(int stampCount) {
		this.stampCount = stampCount;
	}

	public void setHasCoupon(boolean hasCoupon) {
		this.hasCoupon = hasCoupon;
	}

	// DB용 변환
	public String getHasCouponAsString() {
		return hasCoupon ? "Y" : "N";
	}

	public void setHasCouponFromString(String s) {
		this.hasCoupon = "Y".equals(s);
	}

	@Override
	public String toString() {
		return "도장 : " + stampCount + " , 쿠폰 보유 : " + (hasCoupon ? "있음" : "없음");
	}
}
