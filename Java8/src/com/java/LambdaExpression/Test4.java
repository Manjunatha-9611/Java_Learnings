package com.java.LambdaExpression;

class Emp {
	Integer id;
	String name;
	String phone;
	Integer age;

	public Emp(Integer id, String name, String phone, Integer age) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
	}
}

interface IDGenerator {
	String generate(Emp e);
}

public class Test4 {
	public static void main(String[] args) {
		IDGenerator ig = (e) -> e.name.substring(0, 3) + e.phone.substring(8);
		String id = ig.generate(new Emp(1, "Manju", "9876543210", 20));
		System.out.println(id);
	}

}
