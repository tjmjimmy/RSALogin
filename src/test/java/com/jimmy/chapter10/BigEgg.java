package com.jimmy.chapter10;

public class BigEgg extends Egg {

	//此处的Yolk类虽然和Egg.Yolk同名，但这两个内部类是完全独立的两个实体，各自在自己的命名空间内
	class Yolk extends Egg.Yolk{		
		public Yolk(){
			System.out.println("BigEgg.Yolk()");
		}
		
		public void f(){
			System.out.println("BigEgg.Yolk.f()");
		}
	}
	
	public BigEgg(){
		insertYolk(new Yolk());
	}
	
	public static void main(String[] args){
		new Egg();
		System.out.println("---------------");
		BigEgg bigEgg = new BigEgg();
		System.out.println("---------------");
		bigEgg.g();
	}
}

class Egg{
	
	protected class Yolk{
		public Yolk(){
			System.out.println("Egg.Yolk()");
		}
		
		public void f(){
			System.out.println("Egg.Yolk.f()");
		}
	}
	
	private Yolk y = new Yolk();
	
	public Egg(){
		System.out.println("new Egg()");
	}
	
	public void insertYolk(Yolk y){
		this.y = y;
	}
	
	public void g(){
		y.f();
	}
}
