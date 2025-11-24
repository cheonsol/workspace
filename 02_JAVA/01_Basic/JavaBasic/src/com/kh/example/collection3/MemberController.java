package com.kh.example.collection3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MemberController {
	private HashMap<String, Member> map = new HashMap<>();
	
	public boolean joinMembership(String id, Member m) {
		if (map.containsKey(id)) return false;
		map.put(id, m);
		return true;
	}
	
	public String logIn(String id, String password) {
		Member m = map.get(id);
		if(m != null && m.getPassword().equals(password)) {
			return m.getName();
		}
		return null;
	}
	
	public boolean changePassword(String id, String oldPw, String newPw) {
		Member m = map.get(id);
		if(m != null && m.getPassword().equals(oldPw)) {
			m.setPassword(newPw);
			return true;
		}
		
		return false;
	}
	
	public void changeName(String id, String newName) {
		Member m = map.get(id);
		if(m != null) {
			m.setName(newName);
		}
	}
	
	public TreeMap<String, String> sameName(String name){
		TreeMap<String, String> result = new TreeMap<>();
		for(Map.Entry<String, Member> e : map.entrySet()) {
			Member m = e.getValue();
			if(m != null && m.getName().equals(name)) {
				result.put(e.getKey(), m.getName());
			}
		}
		return result;
	}
}
