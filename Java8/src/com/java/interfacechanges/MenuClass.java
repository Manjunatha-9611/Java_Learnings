package com.java.interfacechanges;


interface Menu{
	void jeera_rice();
	default void paneerButterMasala() {
		System.out.println("paneer butter masala");
	}
	default void chickenChilli() {
		System.out.println("chicken chilli");
	}
}

class Veg implements Menu{

	@Override
	public void jeera_rice() {
		System.out.println("Jeera rice");
	}
	
	@Override
	public void paneerButterMasala() {
		Menu.super.paneerButterMasala();
	}
	
}

class NonVeg implements  Menu{

	@Override
	public void jeera_rice() {
		System.out.println("Jeera rice");
	}
	
	@Override
	public void chickenChilli() {
		Menu.super.chickenChilli();
	}
	
}

public class MenuClass {
	public static void main(String[] args) {
		Menu vg = new Veg();
		vg.jeera_rice();
		vg.paneerButterMasala();
		
		System.out.println("----------------------------------");
		
		Menu nvg = new NonVeg();
		nvg.jeera_rice();
		nvg.chickenChilli();
 	}
}
