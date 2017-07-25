package assignment.bank.service;

import java.util.ArrayList;
import java.util.Date;

import assignment.bank.Account;
import assignment.bank.Customer;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.incorrectDateRangeException;
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
	
	public Account createAccount(Customer customer, double balance) throws insufficientBalanceException {
		
		if(balance < 100) {
			throw new insufficientBalanceException("Balance is less than 100");
		}
		
		Account a = new Account(customer, balance);
		accR.addAccount(a);

		return a;
		
	}
	
	

	public Account showBalance(int accNo) throws invalidAccountException {
		
		if(accNo == 0) { //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		Account acc = accR.findAccount(accNo);
		
		if(acc == null) {
			throw new invalidAccountException("Account not found in AccountRepo");
		}
	
				return acc; // return the "found" account
	}

	

	public Account withdraw(int accNo, double amount) throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException {
		if(accNo == 0){ //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		Account acc = accR.findAccount(accNo);
		
		if(acc == null) {
			throw new invalidAccountException("Account not found in AccountRepo");
		}
		
			if (acc.getBalance() - amount < 0) {
					throw new negativeBalanceException("Account balance is negative");
				}

				Date dateOfWithdrawal = new Date();

				if (checkWithdrawalLimit(acc, dateOfWithdrawal, amount)) { // Check if the daily withdrawal exceed 1000
					throw new exceedWithdrawLimitException("Withdraw amount exceeded $1000");
				}

				acc.setBalance(acc.getBalance() - amount);
				double currentBalance = acc.getBalance();

				String text = "Withdrawed $" + amount;

				acc.addTransaction(new Transaction(GenerateId.generateTransId(), dateOfWithdrawal, text, amount, currentBalance));
				
				return acc;
			}
	

	private boolean checkWithdrawalLimit(Account acc, Date dateOfWithdrawal, double amount) throws invalidAccountException {
		
		Account acc1 = accR.findAccount(acc.getAccountNo());
		
		double withdrawAmount = 0;
		for(Transaction trans : acc1.getTransaction()) {
			if (trans.getDate().equals(dateOfWithdrawal) && trans.getDescription().contains("Withdrawed $")) {
				withdrawAmount = withdrawAmount + trans.getAmount();
			}
		}
		if (withdrawAmount + amount > 1000) {
			return true;
		}
		return false;
	}
	
	
	public Account deposit (int accNo, double amount) throws invalidAccountException  {
		
		Account acc = accR.findAccount(accNo);
		if(acc == null) {
			throw new invalidAccountException("Account not found in AccountRepo");
		} else {
		
				Date dateOfDeposit = new Date();

				acc.setBalance(acc.getBalance() + amount);
				double currentBalance = acc.getBalance();

				String text = "Deposited $" + amount;

				acc.addTransaction(new Transaction(GenerateId.generateTransId(), dateOfDeposit, text, amount, currentBalance));
				return acc;
			} 
		
	}

	
	
	
	public Account fundTransfer(int accNoTo, int accNoFrom, double amount) throws invalidAccountException, insufficientFundException {
		Account senderAcc = accR.findAccount(accNoTo);
		Account receiverAcc = accR.findAccount(accNoFrom);
		
		if(senderAcc == null) {
			throw new invalidAccountException("Account number not found in AccountRepo");
		}
		
		if(receiverAcc == null) {
			throw new invalidAccountException("Account number not found in AccountRepo");
		}
		
		
		if(senderAcc.getAccountNo() == 0 || receiverAcc.getAccountNo() == 0) {
			throw new invalidAccountException("Account number passed is zero");
		}
		
		if(senderAcc.getBalance() < amount) {
			throw new insufficientFundException("Insufficient funds in sender's account");
		}

	
				Date dateOfTransfer = new Date();

				receiverAcc.setBalance(receiverAcc.getBalance() + amount);
				double currentBalanceOfReceiver = receiverAcc.getBalance();

				String textReceiver = "$" + amount + "was transferred to your bank account";

				receiverAcc.addTransaction(new Transaction(GenerateId.generateTransId(), dateOfTransfer, textReceiver, amount,
						currentBalanceOfReceiver));
				
			

				senderAcc.setBalance(senderAcc.getBalance() - amount);
				double currentBalanceOfSender = senderAcc.getBalance();

				String textSender = "$" + amount + "was transferred from your bank account";

				senderAcc.addTransaction(new Transaction(GenerateId.generateTransId(), dateOfTransfer, textSender, amount,
						currentBalanceOfSender));
			
		

		return senderAcc;

	}


	public ArrayList<Transaction> printTransactions(int accNo, Date startDate, Date endDate) throws invalidAccountException, incorrectDateRangeException {
		if(accNo == 0){ //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		ArrayList<Transaction> accTransaction = new ArrayList<Transaction>();
		
		if(endDate.before(startDate)) {
			throw new incorrectDateRangeException("End date cannot be before Start date");
		}
		
		Account acc = accR.findAccount(accNo);
		
		for(Transaction trans: acc.getTransaction()) {		
			if ((trans.getDate().after(startDate) && trans.getDate().before(endDate)) ||
					(trans.getDate().equals(startDate) || trans.getDate().equals(endDate)))
					{	
						accTransaction.add(trans); 
						}
			
		}
		return accTransaction;
}

	public ArrayList<Transaction> print10Transactions(int accNo) throws invalidAccountException {
		if(accNo == 0){ //If account number passed is zero
			throw new invalidAccountException("Account number passed is zero");
		}
		
		ArrayList<Transaction> retrievedTransactionArrayList = new ArrayList<Transaction>();
		
		Account acc = accR.findAccount(accNo);
		
		
			if (acc.getTransaction().size() < 10) {
					return acc.getTransaction();
				}
			
				ArrayList<Transaction> accTransaction = acc.getTransaction();
				for (int i = 1; i < 11; i++) {
					retrievedTransactionArrayList.add(accTransaction.get(accTransaction.size() - i));
				}
				
		return retrievedTransactionArrayList;
		}
		
		
	}

