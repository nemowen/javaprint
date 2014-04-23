package com.pcide;

public class Detile {
	
	private int id;
	private String name;
	private Pa pa;
	private int countNumber;
	
	public Detile(int id, String name, Pa pa, int countNumber) {
		super();
		this.id = id;
		this.name = name;
		this.pa = pa;
		this.countNumber = countNumber;
	}
	
	public Detile() {
		super();
	}

	public Pa getPa() {
		return pa;
	}

	public void setPa(Pa pa) {
		this.pa = pa;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(int countNumber) {
		this.countNumber = countNumber;
	}
	
	

}
