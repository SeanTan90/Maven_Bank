package assignment.bank.service;
import java.util.*;

import assignment.bank.Account;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;

public interface IService {
		
	public String createAccount(Account a);
	
	public Account showBalance(int accNo) throws invalidAccountException;
	
	public Account withdraw(Account a, double amount) throws negativeBalanceException, exceedWithdrawLimitException;
	
	public Account deposit(Account a, double amount);
	
	public Account fundTransfer(int accNoTo, int accNoFrom, double amount);
	
	public Transaction[] printTransactions(int accNo, Date startDate, Date endDate);
	
	public Transaction[] print10Transactions(int accNo);
	
	
}
