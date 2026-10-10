package com.java.LambdaExpression;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerAndSupplier {
	public static void main(String[] args) {
		
		Consumer<String> con = (a)-> System.out.println(a);
		con.accept("Manju");
		
		
		//Supplier
		Supplier<String> s = ()-> "Manju";
		System.out.println(s.get());
	}
}
