package com.java.interfacechanges;

//Function interface --> Only one abstarct class only along with all the methods like defualt and static
interface EvenOdd{
	
	String check(int a);
}

public class FunctionalInterface {
	
//	static EvenOdd oe = (a)-> {
//		return (a%2==0) ? "Even":"Odd";
//	};

	public static void main(String[] args) {
		EvenOdd oe = (a)-> {
			return (a%2==0) ? "Even":"Odd";
		};
		//or
		EvenOdd oe1 = (a)-> (a%2==0) ? "Even":"Odd";
		
		System.out.println(oe.check(10));
		System.out.println(oe1.check(11));
	}

}
