package com.jensen.util;
//En klass som representerar bankmoney tabellen
public class BankMoney {
	private int id;
	private int amount;
	
	public BankMoney(int id, int amount){
		this.id=id;
		this.amount=amount;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "BankMoney [amount=" + amount + "]";
	}
	
	
}
