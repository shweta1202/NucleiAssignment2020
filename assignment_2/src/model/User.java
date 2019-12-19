package model;

import java.util.ArrayList;

public class User {
	private String fullName;
	private int age;
	private String address;
	private int rollNo;
	ArrayList<String> courses=new ArrayList<String>();
	static int choice1;
	static int choice2;
	
	
	public int getChoice1() {
		return choice1;	
	}
	public int getChoice2() {
		return choice2;
	}
	public void setChoice1(int choice1) {
		this.choice1=choice1;
	}
	public void setChoice2(int choice2) {
		this.choice2=choice2;
	}
	public String getName() {
		return fullName;
	}
	public void setName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
}
