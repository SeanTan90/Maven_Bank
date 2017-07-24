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
	
	public Account deposit (Account a, double amount);
	
	public Account withdraw(Account a, double amount) throws negativeBalanceException, exceedWithdrawLimitException;
	
	public Transaction[] printTransactions(int accNo, Date startDate, Date endDate);
	
	public Transaction[] print10Transactions(int accNo);
	
	public ArrayList<Account> findAll(int accNo) throws invalidAccountException;
	
	public Account findOne(int accNo) throws invalidAccountException;
	
}


