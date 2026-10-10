package com.java.methodreference;

import java.util.Arrays;
import java.util.List;

interface Demo1{
	Integer add(Integer a, Integer b);
	
	default Integer defaultaddTest(Integer a,Integer b) {
		return add(a, b);
	}
	
}

class Demo1Imp implements Demo1{

	@Override
	public Integer add(Integer a, Integer b) {
		return a+b;
	}
	
}
public class MethodRefExammple {
	
	public MethodRefExammple() {
		System.out.println("0 param Constructor");
	}
	
	public void MethodRefExample(Integer a, Integer b) {
		System.out.println("Param constructor");
	}
	
	public  Integer defaultaddTest(Integer a,Integer b) {
		return a+b;
	}
	
	public static Integer compare(Integer a, Integer b) {
		if(a > b) {
			return a;
		}
		else {
			return b;
		}
	}
	

	public static void main(String[] args) {
	
		//Approach 1
		Demo1 d1 = new Demo1Imp();
		System.out.println(d1.add(10, 20));
		
		System.out.println("=============================");
		
		//Approach 2 using functional interface
		Demo1 d2 = (a,b)-> a+b;
		System.out.println(d2.add(30, 50));
		
		System.out.println("=============================");
		
		
		//Approach 3 using method reference
		
		//Referring test class method to other method of any class or interface
		//Instance method reference
		MethodRefExammple m = new MethodRefExammple();
		Demo1 d3 = (a,b)->m.defaultaddTest(a, b);
		System.out.println(d3.add(33, 33));
		
		System.out.println("=================================");
		
		//Direct referrencing using reference variable
		Demo1 d4 = m::defaultaddTest;
		System.out.println(d4.add(44, 44));
		
		System.out.println("=================================");
		
		//Static method reference
		Demo1 d5 = MethodRefExammple :: compare;
		System.out.println(d5.add(34, 35));
		
		System.out.println("===============================");
		//Constructor reference:
		//Demo1 d6 = MethodRefExammple :: new;
		//Error : The type MethodRefExammple does not define MethodRefExammple(Integer, Integer) that is applicable here
		
		System.out.println("===============================");
		//forEach(Consumer c)
		List<String> names1 = Arrays.asList("Manju","Madan","Basava","Abhi","Praveen","Sushanth","Sush","Sohan","Suraj");
		names1.forEach(System.out::println);
	}

}
