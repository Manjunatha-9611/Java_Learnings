package com.java.LambdaExpression;

import java.util.Scanner;
import java.util.function.Function;

public class FunctionTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Function<String, Integer> fl = (in) -> in.length();
		Integer res = fl.apply("Manju");
		System.out.println(res);
		
		Function<String, Character> fc = (in) ->in.charAt(2);
		System.out.println(fc.apply("Manju"));
		
		
		System.out.println("Enter anything");
		Function<String, String> f = (in)-> in;
		String any =f.apply(sc.next());
		System.out.println(any);

	}

}
