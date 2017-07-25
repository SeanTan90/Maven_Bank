package assignment.bank;

import java.util.ArrayList;

import assignment.bank.utilities.GenerateId;

public class Account {
	private int accNo;
	private double balance;
	private ArrayList<Transaction> transactions;
	private Customer customer;
	
	public Account() {
		//default no parameter constructor
	}
	
	public Account(Customer customer, double balance) {
		accNo = GenerateId.generateBankAccId();
		this.customer = customer;
		this.balance = balance;
		transactions = new ArrayList<Transaction>(); // Must initialize arrayList otherwise will get nullpointerException when adding transactions
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
