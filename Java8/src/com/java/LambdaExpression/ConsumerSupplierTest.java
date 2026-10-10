package com.java.LambdaExpression;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerSupplierTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter anything :");
		String any;
		
		Supplier<String> supply = ()-> sc.next();
		
		Consumer<String> consume = (in)->System.out.println(in);
		consume.accept(supply.get());
		
	}

}
