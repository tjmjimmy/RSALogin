package com.jimmy.inherit;

public class Student extends Person {

	public String grade;
	public String dormitory;
	
	public Student(){
//		System.out.println("Student class hashCode:" + this.hashCode());
//		System.out.println("Student class:" + this);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDormitory() {
		return dormitory;
	}

	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	
	
}
