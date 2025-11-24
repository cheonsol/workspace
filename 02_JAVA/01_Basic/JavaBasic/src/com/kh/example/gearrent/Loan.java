package com.kh.example.gearrent;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Loan {
	private String itemid;
	private String memberId;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	
	public Loan(String itemid, String memberId, LocalDate loanDate, LocalDate dueDate) {
		super();
		this.itemid = itemid;
		this.memberId = memberId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
	}
	
	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}

	public boolean isOverdue(LocalDate today) {
		
		//계산기준일(반납날짜 또는 오늘)
		LocalDate day = (returnedDate != null) ? returnedDate : today;
		
		//기준일이 마감일(duDate)보다 늦으면 연체 발생
		return day.isAfter(dueDate);
	}
	
	public int overdueDays(LocalDate today) {
		//계산기준일(반납날짜 또는 오늘)
		LocalDate day = (returnedDate != null) ? returnedDate : today;
	
		if(day.isAfter(dueDate)) {
			long days = ChronoUnit.DAYS.between(dueDate, day);
			
			return (int)Math.abs(days);
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "Loan [itemid=" + itemid + ", memberId=" + memberId + ", loanDate=" + loanDate + ", dueDate=" + dueDate
				+ ", returnedDate=" + returnedDate + "]";
	}
}
