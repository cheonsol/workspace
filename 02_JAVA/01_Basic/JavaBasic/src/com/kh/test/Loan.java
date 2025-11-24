package com.kh.test;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final String itemId;
    private final String memberId;
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnedDate; // null = 미반납

    public Loan(String itemId, String memberId, LocalDate loanDate, LocalDate dueDate) {
        this.itemId = Objects.requireNonNull(itemId);
        this.memberId = Objects.requireNonNull(memberId);
        this.loanDate = Objects.requireNonNull(loanDate);
        this.dueDate = Objects.requireNonNull(dueDate);
    }

    public String getItemId() { return itemId; }
    public String getMemberId() { return memberId; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = Objects.requireNonNull(dueDate); }
    public LocalDate getReturnedDate() { return returnedDate; }
    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }

    /** 연체 여부 판단 */
    public boolean isOverdue(LocalDate today) {
        LocalDate end = (returnedDate != null) ? returnedDate : today;
        return end.isAfter(dueDate);
    }

    /** 연체 일수(0 이상) */
    public int overdueDays(LocalDate today) {
        LocalDate end = (returnedDate != null) ? returnedDate : today;
        long diff = end.toEpochDay() - dueDate.toEpochDay();
        return (diff > 0) ? (int) diff : 0;
    }

    @Override
    public String toString() {
        String ret = (returnedDate == null) ? "미반납" : returnedDate.toString();
        return String.format("Loan[item=%s, member=%s, loan=%s, due=%s, ret=%s]",
                itemId, memberId, loanDate, dueDate, ret);
    }
}
