package assignment.bank;

import java.util.*;

public class Transaction {
	private int transId;
	private Date date;
	private String description;
	private double balance;
	private double amount;
	
	public Transaction(int transId, Date date, String description, double amount, double balance) {
		this.transId = transId;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.balance = balance;
		
	}
	
	public Date getDate() {
		return date;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getTransId() {
		return transId;
	}
	
public void setTransId(int transId) {
		this.transId = transId;
	}

}
