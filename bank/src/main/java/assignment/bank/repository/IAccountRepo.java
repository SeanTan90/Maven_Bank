package assignment.bank.repository;

import java.util.ArrayList;
import java.util.Date;

import assignment.bank.Account;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;

public interface IAccountRepo {
		
	public String addAccount(Account a);
	
	public Account findAccount(int accNo);
	
	public Account showBalance(int accNo);
	
	public Account deposit (int accNo, double amount) throws invalidAccountException;
	
	public Account withdraw(int accNo, double amount) throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException;
	
	public Account fundTransfer(int accNoTo, int accNoFrom, double amount) throws negativeBalanceException;
	
	public ArrayList<Transaction> printTransactions(int accNo, Date startDate, Date endDate) throws invalidAccountException;
	
	public ArrayList<Transaction> print10Transactions(int accNo) throws invalidAccountException;
	
	public Account findOne(int accNo) throws invalidAccountException;
	
}


