/**
 * @description 测试继承特性
 */
package com.jimmy.inherit;

/**
 * @author tjm_Jimmy
 *
 */
public class CollegeStudent extends Student {
	
	public CollegeStudent(String grade, String dormitory){
		
		this.grade = grade;
		this.dormitory = dormitory;
	}
	
	public CollegeStudent(){
		System.out.println("CollegeStudent class hashCode:" + this.hashCode());
		System.out.println("Student class:" + this);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("grade=");
		sb.append(grade);
		sb.append(";dormitory=");
		sb.append(dormitory);
		return new String(sb);
	}
	
	public static void main(String[] args){
		new CollegeStudent();
	}
}
