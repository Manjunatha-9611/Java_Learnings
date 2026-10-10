package com.java.optionalclass;

import java.util.Optional;

public class OptionalClassExample {

	public static void main(String[] args) {
		
		//1. Optional.of() ->to create a object
		Optional<String> s = Optional.of("Manju");
		System.out.println(s);  //output ->Optional[Manju]
		
		//what if the object is null
		//Optional<String> s1 = Optional.of(null);
		//System.out.println(s1);  //output ->NullPointerException
		
		
		//2. Optional.ofNullable() when we have object
		String name=null;
		Optional<Object> oc = Optional.ofNullable(name);
		if(oc.isPresent()) {
			String n = (String) oc.get();
			System.out.println(n);
			System.out.println(n.length());
		}
		System.out.println("it is null value");
		
		
		System.out.println("===============================");
		String name1 = null;
		Optional<Object> oc1 = Optional.ofNullable(name1);
		String n1 = (String) oc1.orElse("Not");
		System.out.println(n1);
		System.out.println(n1.length());
	}

}
