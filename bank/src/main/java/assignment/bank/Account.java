package assignment.bank;

import java.util.ArrayList;

public class Account {
	private int accNo;
	private double balance;
	private ArrayList<Transaction> transactions;
	
	public Account() {
		//default no parameter constructor
	}
	
	public Account(int accNo, double balance) {
		this.accNo = accNo;
		this.balance = balance;
		transactions = new ArrayList<Transaction>(); // Must initialize arrayList otherwise will get nullpointerException when adding transactions
	}
	
	public int getAccountNo() {
		return accNo;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addTransaction(Transaction trans) {
	
				this.transactions.add(trans);
	}
	
	public ArrayList<Transaction> getTransaction() {
		return transactions;
	}
	
}
