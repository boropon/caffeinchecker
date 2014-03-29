package com.example.caffeinechecker;

class CaffeinTarget {
	private int iconid;
	private String name;
	private int Caffein;
	private int num;
	private int color;
	
	public CaffeinTarget(int id, String name, int Caffein, int color) {
		this.iconid = id;
		this.name = name;
		this.Caffein = Caffein;
		this.num = 0;
		this.color = color;
	}
	
	public int getIconId() {
		return iconid;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCaffein() {
		return Caffein;
	}
	
	public int getNum() {
		return num;
	}
	
	void setNum(int num) {
		this.num = num;
	}
	
	int getColor() {
		return color;
	}
}