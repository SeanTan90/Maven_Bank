package assignment.bank.repository;

import java.util.ArrayList;
import java.util.Date;

import assignment.bank.Account;
import assignment.bank.Transaction;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.utilities.GenerateId;
import assignment.bank.exceptions.exceedWithdrawLimitException;

public class AccountRepo implements IAccountRepo {
	ArrayList<Account> accArray = new ArrayList<Account>();
	GenerateId generator = new GenerateId();
	double withdrawAmount = 0;
	private Exception exceedWithdrawLimitException;

	public String addAccount(Account a) {
		accArray.add(a);
		return "New account successfully added!";
		
	}

	public Account findAccount(int accNo) {
		for(int i=0; i<accArray.size(); i++) {
			if(accNo == (accArray.get(i).getAccountNo())) {
				return accArray.get(i); //return the "found" account
			}
		}

		return null;
	}

	public Account showBalance(int accNo) {
		for(int i=0; i<accArray.size(); i++) {
			if(accNo == (accArray.get(i).getAccountNo())) {
				return accArray.get(i); // return the "found" account object
			}
		}

		return null;
	}
	
	
	public Account deposit (Account a, double amount) {
		int transIdDeposit = generator.generateTransId();
		
		Date dateOfDeposit = new Date();
		
		a.setBalance(a.getBalance() + amount);
		double currentBalance = a.getBalance();
		
		String text = "Deposited $" + amount;
		
		a.addTransaction(new Transaction(transIdDeposit, dateOfDeposit, text, amount, currentBalance));
		
		for(int i=0; i<accArray.size(); i++) {
			if(a.getAccountNo() == accArray.get(i).getAccountNo()) {
				accArray.get(i).setBalance(currentBalance);
				break;
			}
		}
		return a;
	}
	
	public Account withdraw(Account a, double amount) throws negativeBalanceException, exceedWithdrawLimitException {
		if(a.getBalance() - amount < 0) {
			throw new negativeBalanceException();
		}
		

		this.withdrawAmount = withdrawAmount + amount;
		
		if(withdrawAmount > 1000) {
			throw new exceedWithdrawLimitException();
		}
		
		int transIdWithdraw = generator.generateTransId();
		
		Date dateOfWithdraw = new Date();
		
		a.setBalance(a.getBalance() - amount);
		
		
		
		return null;
	}

	
		

	public Transaction[] printTransactions(int accNo, Date startDate, Date endDate) {

		return null;
	}

	public Transaction[] print10Transactions(int accNo) {

		return null;
	}


	
	public Account findOne(int accNo) throws invalidAccountException {
		for(int i=0; i<accArray.size(); i++) {
			if(accArray.get(i).getAccountNo() == accNo) {
				return accArray.get(i);
			}
		}
		
		throw new invalidAccountException();
	}

	public ArrayList<Account> findAll(int accNo) throws invalidAccountException {
		// TODO Auto-generated method stub
		return null;
	}

//	public ArrayList<Account> findAll(int accNo) throws invalidAccountException {
//	
//	}

}
