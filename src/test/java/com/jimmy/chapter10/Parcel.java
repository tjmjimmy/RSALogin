package com.jimmy.chapter10;

public class Parcel {

	public Contents getContent(){
		class PContent implements Contents{

			public int value() {
				return i;
			}
			
		}
		return new PContent();	
	}
	
	
	public static void main(String[] args){
		Parcel p = new Parcel();
		Contents content = p.getContent();
		System.out.println("content.value()返回值=" + content.value( ));
	}
}
