package com.semi.cm.model.vo;

import java.util.Objects;

/**
 * T_BUY_EXP_MAN
 */
public class BuyExpManVo {

	private int expenseNo; //자금 번호
	private int deposit; // 입금액
	private int debit; // 출금액
	private int expense; // 자금
	public BuyExpManVo() {
		super();
	}
	public BuyExpManVo(int expenseNo, int deposit, int debit, int expense) {
		super();
		this.expenseNo = expenseNo;
		this.deposit = deposit;
		this.debit = debit;
		this.expense = expense;
	}
	public int getExpenseNo() {
		return expenseNo;
	}
	public void setExpenseNo(int expenseNo) {
		this.expenseNo = expenseNo;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(expenseNo);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BuyExpManVo) {
			BuyExpManVo b = (BuyExpManVo)obj;
			return this.expenseNo == b.expenseNo;
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "BuyExpManVo [expenseNo=" + expenseNo + ", deposit=" + deposit + ", debit=" + debit + ", expense="
				+ expense + "]";
	}
	
	
	
}
