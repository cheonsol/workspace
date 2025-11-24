package com.kh.object.ex2;

public class Run {

	public static void main(String[] args) {
		BankAccount ac1 = new BankAccount();
		BankAccount ac2 = ac1;
		
		ac1.deposit(5000);
		ac2.withdraw(1000);
		ac1.checkMyBalnace();
		
		ac2 = new BankAccount(2000);
		
		ac1.transfer(ac2, 1000);
	}

}
