/*
 * 探究private成员在继承中的表现
 * */
package com.jimmy.test;

//下面这句会报错，com.jimmy.obj.Car是默认访问权限（包访问权限）
//import com.jimmy.obj.Car;

//此时的Car类是内部类
public class MotoCar extends Car {
	
	//這里的driveLicense和父类中的driveLicense不是同一个成员变量，是两个不同的driveLicense，有两个不同的存储空间
	public String driveLicense = new String("C2");//驾照资格
	
	public MotoCar(){
		//在调用构造函数时，会先调用父类的构造函数，此时子类和父类的构造函数打印出的hashCode值是一样的
		System.out.println("child hashCode--" + this.hashCode());
		System.out.println(super.equals(this));//打印出true
		System.out.println(System.identityHashCode(this));
	}
	
	public String getString(){
		return "getString method";
	}

	public void test1(){
		System.out.println("this is child normal method!");
	}
	
	public static void main(String[] args){
		
		/*
		 * 很疑惑：以下三个对象中的父类的driveLicense的hashCode是一样的
		 * */
		
		Car c = new Car();
		c.setWheel_num(2);
		System.out.println(c.getWheel_num());
		System.out.println(c.driveLicense.hashCode());
//		c.wheel_num = 2;	//private成员不能被访问
		
		MotoCar moto = new MotoCar();
		moto.setWheel_num(4);//对于这句有很大疑惑：子类是否拥有成员变量wheel_num，在setWheel_num(int)方法中，set的wheel_num是子类的还是父类的？
		System.out.println(moto.getWheel_num());
		System.out.println("moto.brand = " + moto.brand);
		System.out.println("moto.driveLicense = " + moto.driveLicense);
		System.out.println("moto.driveLicense = " + moto.getDriveLicense());
		moto.printDriveLicenseHashCode();
		
		Car motoCar = new MotoCar();
		//下面两句输出表明成员变量是不具有多态性的，普通成员方法具有多态性
		System.out.println("motoCar.driveLicense = " + motoCar.driveLicense);//探究是打印在父类中的driveLicense还是子类中的driveLicense
		motoCar.test1();
		System.out.println("motoCar.brand = " + motoCar.brand);
		System.out.println(motoCar.driveLicense.hashCode());
		//下面这两句会报错，因为motoCar找不到getString()方法和driveLicense字段，这是在向上转型时成员丢失。
//		System.out.println(motoCar.getString());
//		System.out.println(motoCar.driveLicense);
	}
	
	public void printDriveLicenseHashCode(){
		
		System.out.println("---printDriveLicenseHashCode start---");
		System.out.println(this.driveLicense.hashCode());
		System.out.println(super.driveLicense.hashCode());
		System.out.println("---printDriveLicenseHashCode end---");
	}

}

class Car {
	
	private int wheel_num;
	
	public String driveLicense = new String("C1");//驾照资格
	public String brand = "Audo";
	
	public Car(){
		System.out.println("father hashCode--" + this.hashCode());
	}

	public int getWheel_num() {
		return wheel_num;
	}

	public void setWheel_num(int wheel_num) {
		this.wheel_num = wheel_num;
	}

	public String getDriveLicense() {
		return driveLicense;
	}

	public void setDriveLicense(String driveLicense) {
		this.driveLicense = driveLicense;
	}

	public void test1(){
		System.out.println("this is father normal method!");
	}
	
	public static void test2(){
		System.out.println("father static method!");
	}
}