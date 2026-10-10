package com.java.interfacechanges;

interface Bank{
	
	void getBal();
	
	void deposit();
	
	default void intlDeposit() {
		System.out.println("Dollars");
	}
	
	static void loanSanctions() {
		System.out.println("Loan feature");
	}
}

class CNBK implements Bank{

	@Override
	public void getBal() {
		System.out.println("CNBK Balance");
	}

	@Override
	public void deposit() {
		System.out.println("CNBK deposit");
	}
	
	@Override
	public void intlDeposit() {
		Bank.super.intlDeposit();
	}
}


class AXBK implements Bank{

	@Override
	public void getBal() {
		System.out.println("AXBK balance");
	}

	@Override
	public void deposit() {
		System.out.println("AXBK deposit");
	}
	
	@Override
	public void intlDeposit() {
		Bank.super.intlDeposit();
	}
	
}


class COOPBK implements Bank{

	@Override
	public void getBal() {
		System.out.println("COOPBK balance");
		
	}

	@Override
	public void deposit() {
		System.out.println("COOPBK");
	}
	

}




public class InterfaceEnchancement {

	public static void main(String[] args) {
		Bank cb = new CNBK();
		cb.getBal();
		cb.deposit();
		cb.intlDeposit();
		Bank.loanSanctions();
		System.out.println("---------------------");
		
		Bank ab = new AXBK();
		ab.getBal();
		ab.deposit();
		ab.intlDeposit();
		//Bank.loanSanctions();
		
		System.out.println("------------------------");
		
		Bank cob = new COOPBK();
		cob.getBal();
		cob.deposit();
		//cob.intlDeposit();
	}

}
