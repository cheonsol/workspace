package com.kh.example.collection2;

import java.util.ArrayList;
import java.util.HashSet; 
import java.util.Random;
import java.util.TreeSet;

public class LotteryController{ 
	private HashSet<Lottery> lottery = new HashSet<>();
	private HashSet<Lottery> win = new HashSet<>();

	public boolean insertObject(Lottery l){ 
		return lottery.add(l);
	} 
	public boolean deleteObject(Lottery l){ 
		boolean isRemove = lottery.remove(l);
		if(win != null && isRemove) {
			win.remove(l);
		}
		return isRemove;
	} 
	
	public HashSet<Lottery> winObject(){ 
		//남은 인원만 선발
		if(win.size() < 4) {
			Random rand = new Random();
			
			ArrayList<Lottery> list = new ArrayList<>();
			list.addAll(lottery);
			
			while(win.size() < 4 && win.size() != lottery.size()) {
				int index = rand.nextInt(list.size()); //0~size()-1 중 랜덤으로 정수 추출
				win.add(list.get(index));
			}
		} 
		
		return win;
	} 
	
	public TreeSet sortedWinObject(){ 
		  // 이름으로 오름차순 정렬하되, 이름이 같으면 번호로 오름차순 하는 
		  // 정렬기준을 가지고 정렬된 결과를 반환 
		  // 이 때, 미리 만들어진 win을 가지고 정렬하기 때문에 
		  // 당첨 대상 확인을 꼭 먼저 해야함 
		return null;
	} 
	 
	public boolean searchWinner(Lottery l){ 
		 return win.contains(l);
	} 
} 
