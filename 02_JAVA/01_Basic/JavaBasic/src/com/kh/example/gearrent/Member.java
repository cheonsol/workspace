package com.kh.example.gearrent;

import java.util.Objects;

public class Member {
	private String id;
	private String name;
	public Member() {
		super();
	}
	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member m = (Member)obj;
			return m.getId().equals(this.id);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + "]";
	}
}
