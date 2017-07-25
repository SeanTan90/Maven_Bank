package assignment.bank.service;
import java.util.*;

import assignment.bank.Account;
import assignment.bank.Customer;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.incorrectDateRangeException;
import assignment.bank.exceptions.insufficientBalanceException;
import assignment.bank.exceptions.insufficientFundException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;

public interface IService {
		
	public Account createAccount(Customer customer, double balance) throws insufficientBalanceException;
	
	public Account showBalance(int accNo) throws invalidAccountException;
	
	public Account withdraw(int accNo, double amount) throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException;
	
	public Account deposit(int accNo, double amount) throws invalidAccountException;
	
	public Account fundTransfer(int accNoTo, int accNoFrom, double amount) throws invalidAccountException, insufficientFundException;
	
	public ArrayList<Transaction> printTransactions(int accNo, Date startDate, Date endDate) throws invalidAccountException, incorrectDateRangeException;
	
	public ArrayList<Transaction> print10Transactions(int accNo) throws invalidAccountException;
	
	
}
