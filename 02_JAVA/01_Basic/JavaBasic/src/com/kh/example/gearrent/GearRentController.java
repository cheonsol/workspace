package com.kh.example.gearrent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale.Category;

public class GearRentController {
	private HashMap<String, Device> catalog = new HashMap<>();
	private HashMap<String, Member> members = new HashMap<>();
	private HashMap<String, Loan> activeLoans = new HashMap<>();
	
	public boolean addDevice(Device device) {
		if(catalog.containsKey(device.getId())) {
			return false;
		}
		
		catalog.put(device.getId(), device);
		return true;
	}
	
	public boolean addMember(Member member) {
		if(members.containsKey(member.getId())) {
			return false;
		}
		
		members.put(member.getId(), member);
		return true;
	}
	
	public Loan borrow(String memberId, String itemId, LocalDate today) {
		Device device = catalog.get(itemId);
		if(device == null) return null;
		
		LocalDate due = today.plusDays(device.getBorrowLimitDays());
		Loan loan = new Loan(itemId, memberId, today, due);
		if(activeLoans.containsKey(itemId)) return null;
		
		activeLoans.put(itemId, loan);
		device.increaseBorrowCount();
		return loan;
	}
	
	public int returnItem(String itemId, LocalDate today) {
		Loan loan = activeLoans.get(itemId); //대여정보객체
		
		if(loan == null) return -1;
		
		loan.setReturnedDate(today); //실제 반납일 
		int overdueDay = loan.overdueDays(today);
		Device device = catalog.get(itemId); //기기정보객체
		int fee = device.calcLateFee(overdueDay); //연체료
		activeLoans.remove(itemId);
		
		return fee;
	}
	
	public ArrayList<Device> findByTag(String tag){
		ArrayList<Device> list = new ArrayList<>();
		
		if(tag == null) return list;
		
		for(Device d : catalog.values()) {
			if(d.hasTag(tag)) {
				list.add(d);
			}
		}
		
		return list;
	}
	
	public ArrayList<Device> findByKeyword(String keyword){
		ArrayList<Device> list = new ArrayList<>();
		
		if(keyword == null || keyword.equals(" ")) return list;
		
		String key = keyword.toLowerCase();
		
		for(Device d : catalog.values()) {
			String name = d.getName().toLowerCase();
			String cat = d.getCategory().toLowerCase();
			if(name.contains(key) || cat.contains(key)) {
				list.add(d);
			}
		}
		
		return list;
	}
	
	public Collection<Device> getAllDevices(){
		//읽기전용 뷰를 만들어준다 -> 추가/삭제/수정 불가
		return Collections.unmodifiableCollection(catalog.values());
	}
	
	public Collection<Loan> getActiveLoans(){
		//읽기전용 뷰를 만들어준다 -> 추가/삭제/수정 불가
		return Collections.unmodifiableCollection(activeLoans.values());
	}
	
	
	
}
