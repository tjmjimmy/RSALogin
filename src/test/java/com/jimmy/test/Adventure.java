package com.jimmy.test;

public class Adventure {

	public static void main(String[] args){
		CanFight x = new Hero();
		x.fight();
		
		ActionCharacter ac = new Hero();
		ac.fight();
		
		/* 0.1 + 0.2输出结果：0.30000000000000004
		 * 在内存中，并不能精确地记录小数，记录的只是和该小数很接近的一个数值
		 * 0.2 + 0.2则输出0.4
		 */
		System.err.println(0.1 + 0.2);
		System.err.println(0.2 + 0.2);
	}
}

interface CanFight{
	void fight();
}
interface CanFly{
	void fly();
}
interface CanSwim{
	void swim();
}
class ActionCharacter{
 	public void fight(){
		System.out.println("ActionCharacter fight!");
	}
}
/**
 * Hero继承了ActionCharacter同时实现CanFly, CanSwim, CanFight接口，
 * ActionCharacter中的fight()方法与CanFight接口中的fight()方法同名，
 * 所以ActionCharacter中的fight()方法必须是public权限
 * */
class Hero extends ActionCharacter implements CanFly, CanSwim, CanFight{

	public void swim() {
		// TODO Auto-generated method stub
		
	}

	public void fly() {
		// TODO Auto-generated method stub
		
	}
	
	public void fight(){
		System.out.println("Hero fight!");
	}
	
}