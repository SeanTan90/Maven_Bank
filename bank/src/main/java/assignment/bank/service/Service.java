package assignment.bank.service;

import java.util.ArrayList;
import java.util.Date;

import assignment.bank.Account;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.insufficientBalanceException;
import assignment.bank.exceptions.insufficientFundException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.repository.AccountRepo;
import assignment.bank.utilities.GenerateId;

public class Service implements IService {
	private AccountRepo accR = new AccountRepo();
	ArrayList<Account> accArray = new ArrayList<Account>();
	GenerateId generator = new GenerateId();
	
	public String createAccount(Account a) throws invalidAccountException, insufficientBalanceException {
			
		if(a == null) {
			throw new invalidAccountException("Account number passed is zero");
		}
		
		if(a.getAccountNo() == 0) {
			throw new invalidAccountException("Account number passed is zero");
		}
		
		if(a.getBalance() < 100) {
			throw new insufficientBalanceException("Balance is less than 100");
		}
		
		
		return accR.addAccount(a);
		
	}
	
	

	public Account showBalance(int accNo) throws invalidAccountException {
		
		if(accNo == 0) { //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		return accR.findOne(accNo);
	
	}

	public Account withdraw(int accNo, double amount) throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException {
		if(accNo == 0){ //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		return accR.withdraw(accNo, amount);
	}

	
	
	
	public Account deposit (int accNo, double amount) throws invalidAccountException  {
		return accR.deposit(accNo, amount);
	}

	
	
	
	public Account fundTransfer(int accNoTo, int accNoFrom, double amount) throws invalidAccountException, insufficientFundException {
		
		
		if(accNoTo == 0 || accNoFrom == 0) {
			throw new invalidAccountException("Account number passed is zero");
		}
		
		if(accR.findOne(accNoFrom).getBalance() < amount) {
			throw new insufficientFundException("Insufficient funds in sender's account");
		}

		if(accR.findOne(accNoTo) == null) { 
			throw new invalidAccountException("Account number not found in AccountRepo");
		}
		
		
		if(accR.findOne(accNoFrom) == null) { 
			throw new invalidAccountException("Account number not found in AccountRepo");
		}
		
		
		return accR.fundTransfer(accNoTo, accNoFrom, amount);
	}

	public ArrayList<Transaction> printTransactions(int accNo, Date startDate, Date endDate) throws invalidAccountException {
		if(accNo == 0){ //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		return accR.printTransactions(accNo, startDate, endDate);
		
	}

	public ArrayList<Transaction> print10Transactions(int accNo) throws invalidAccountException {
		if(accNo == 0){ //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		return accR.print10Transactions(accNo);
		
	}

}
