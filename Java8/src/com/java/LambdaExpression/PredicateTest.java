package com.java.LambdaExpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Manju","Madan","Basava","Abhi","Praveen","Sushanth","Sush","Sohan","Suraj");
		Predicate<String> len = (name)->name.length()>5;
		for(String s : names) {
			if(len.test(s)) {
				System.out.println(s);
			}
		}
		
		System.out.println("------------------------------------------");
		Predicate<String> startS = (name1)-> name1.substring(0,1).equals("S");
		for(String s : names) {
			if(startS.test(s)) {
				System.out.println(s);
			}
		}
		
		System.out.println("------------------------------------------ ");
		System.out.println("startsWith() method");
		Predicate<String> startSand4 = (name1)-> name1.startsWith("S") && name1.length()>4;
		for(String s : names) {
			if(startSand4.test(s)) {
				System.out.println(s);
			}
		}
		
		System.out.println("--------------------------------------");
		Predicate<String> p31 = (len1)-> len1.length()>5;
		Predicate<String> p32 = (startSWS)-> startSWS.startsWith("S");
		
		Predicate<String> p3f = p31.and(p32);
		
		for(String Sn4 : names) {
			if(p3f.test(Sn4)) {
				System.out.println(Sn4);
			}
		}
	}

}
