package assignment.bank.service;

import java.util.ArrayList;
import java.util.Date;

import assignment.bank.Account;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.repository.AccountRepo;
import assignment.bank.utilities.GenerateId;

public class Service implements IService {
	private AccountRepo accR = new AccountRepo();
	ArrayList<Account> accArray = new ArrayList<Account>();
	GenerateId generator = new GenerateId();
	
	public String createAccount(Account a) {
		StringBuilder sb = new StringBuilder(40);
		
		if(a == null) {
			sb.append("Account should not be null"); //Check if account object passed is null
			return sb.toString();
		}
		
		if(a.getAccountNo() == 0) {
			sb.append("Account Number should not be zero"); //Check if the account number is zero
			return sb.toString();
		}
		
		return accR.addAccount(a);
		
	}
	
	

	public Account showBalance(int accNo) throws invalidAccountException {
		
		return accR.findOne(accNo);
	
	}

	public Account withdraw(Account a, double amount) throws negativeBalanceException, exceedWithdrawLimitException {
		return accR.withdraw(a, amount);
	}

	public Account deposit (Account a, double amount) {
		return accR.deposit(a, amount);
	}

	public Account fundTransfer(int accNoTo, int accNoFrom, double amount) {

		return null;
	}

	public Transaction[] printTransactions(int accNo, Date startDate, Date endDate) {

		return null;
	}

	public Transaction[] print10Transactions(int accNo) {

		return null;
	}

}
