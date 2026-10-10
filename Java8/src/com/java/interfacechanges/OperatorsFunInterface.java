package com.java.interfacechanges;

interface Operator{
	int add(int a , int b);
}

public class OperatorsFunInterface {
	public static void main(String[] args) {
		Operator o = (a,b)-> a+b;
		System.out.println(o.add(10, 20));
	}
}
