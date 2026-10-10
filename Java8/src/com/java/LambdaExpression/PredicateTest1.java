package com.java.LambdaExpression;

import java.util.function.Predicate;

public class PredicateTest1 {

	public static void main(String[] args) {
		Predicate<Integer> p = (i)-> i%3 ==0;
		if(p.test(6)) {
			System.out.println("Divisible");
		}
		else {
			System.out.println("Not Divisible");
		}
	}
}
