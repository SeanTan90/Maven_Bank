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

	public String addAccount(Account a) {

		accArray.add(a);
		return "New account successfully added!";

	}

	public Account findAccount(int accNo) throws invalidAccountException {

		for (int i = 0; i < accArray.size(); i++) {
			if (accArray.get(i) == null) {
				break;
			}
		}		

		for (int i = 0; i < accArray.size(); i++) {
			if (accNo == (accArray.get(i).getAccountNo())) {
				return accArray.get(i); // return the "found" account
			}
		}

		throw new invalidAccountException("Account not found in AccountRepo");
	}
	

	public Account showBalance(int accNo) throws invalidAccountException {
		for (int i = 0; i < accArray.size(); i++) {
			if (accNo == (accArray.get(i).getAccountNo())) {
				return accArray.get(i); // return the "found" account object
			}
		}

		throw new invalidAccountException("Account not found in AccountRepo");
	}

	public Account deposit(int accNo, double amount) throws invalidAccountException {

		for (int i = 0; i < accArray.size(); i++) {
			if (accArray.get(i) == null) {
				break;
			}
		}

		for (Account acc : accArray) {
			if (accNo != acc.getAccountNo()) {
				throw new invalidAccountException("Account not found in AccountRepo");
			}
		}

		for (Account acc : accArray) {
			if (accNo == acc.getAccountNo()) {

				Date dateOfDeposit = new Date();

				acc.setBalance(acc.getBalance() + amount);
				double currentBalance = acc.getBalance();

				String text = "Deposited $" + amount;

				acc.addTransaction(
						new Transaction(GenerateId.generateTransId(), dateOfDeposit, text, amount, currentBalance));
				return acc;
			} else {
				throw new invalidAccountException("Account not found in AccounRepo");
			}
		}

		throw new invalidAccountException("Account number not found in AccountRepo");
	}

	public Account withdraw(int accNo, double amount)
			throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException {

		for (int i = 0; i < accArray.size(); i++) {
			if (accArray.get(i) == null) {
				break;
			}
		}

		for (Account acc : accArray) {
			if (accNo == acc.getAccountNo()) {
				
				if (acc.getBalance() - amount < 0) {
					throw new negativeBalanceException("Account balance is negative");
				}

				Date dateOfWithdrawal = new Date();

				if (checkWithdrawalLimit(acc, dateOfWithdrawal, amount)) { // Check
																			// if
																			// the
																			// daily
																			// withdrawal
																			// exceed
																			// 1000
					throw new exceedWithdrawLimitException("Withdraw amount exceeded $1000");
				}

				acc.setBalance(acc.getBalance() - amount);
				double currentBalance = acc.getBalance();

				String text = "Withdrawed $" + amount;

				acc.addTransaction(
						new Transaction(GenerateId.generateTransId(), dateOfWithdrawal, text, amount, currentBalance));
				return acc;
			}
		}

		throw new invalidAccountException("Account number not found in AccounRepo");
	}

	private boolean checkWithdrawalLimit(Account acc, Date dateOfWithdrawal, double amount) {

		double withdrawAmount = 0;

		for (Account acc1 : accArray) {
			for (int i = 0; i < acc1.getTransaction().size(); i++) {
				if (acc1.getAccountNo() == acc.getAccountNo()) {
					if (acc1.getTransaction().get(i).getDate().equals(dateOfWithdrawal)
							&& acc1.getTransaction().get(i).getDescription().contains("Withdrawed $")) {
						withdrawAmount = withdrawAmount + acc1.getTransaction().get(i).getAmount();
					}
				}
			}
		}
		if (withdrawAmount + amount > 1000) {
			return true;
		}
		return false;
	}

	public ArrayList<Transaction> printTransactions(int accNo, Date startDate, Date endDate) throws invalidAccountException {
		ArrayList<Transaction> accTransaction = new ArrayList<Transaction>();

		for (Account acc : accArray) {
			if (acc.getAccountNo() == accNo) {
				for (int i = 0; i < acc.getTransaction().size(); i++) {
					if (acc.getTransaction().get(i).getDate().after(startDate)
							&& acc.getTransaction().get(i).getDate().before(endDate)) {
						accTransaction.add(acc.getTransaction().get(i));
					} else if (acc.getTransaction().get(i).getDate().equals(startDate)
							|| acc.getTransaction().get(i).getDate().equals(endDate)) {
						accTransaction.add(acc.getTransaction().get(i));
					}

				}
				return accTransaction;
			}
			
		}
		throw new invalidAccountException("Account number not found in AccountRepo");
	}

	public ArrayList<Transaction> print10Transactions(int accNo) throws invalidAccountException {
		ArrayList<Transaction> retrievedTransactionArrayList = new ArrayList<Transaction>();

		for (Account acc : accArray) {
			if (acc.getAccountNo() == accNo) {
				if (acc.getTransaction().size() < 10) {
					return acc.getTransaction();
				}
				for (int i = 1; i < 11; i++) {
					ArrayList<Transaction> accTransaction = acc.getTransaction();

					retrievedTransactionArrayList.add(accTransaction.get(accTransaction.size() - i));
				}
				return retrievedTransactionArrayList;
			}

		}
		throw new invalidAccountException("Account number not found in AccountRepo");
	}

	// Calender cal2 = Calender.getInstance();
	// cal2.set(Calendar.MONTH,7);
	// cal2.set(Calendar.DATE,26);
	// cal2.set(Calendar.YEAR,2017);
	// cal2.set(Calendar.HOUR,0);
	// cal2.set(Calendar.MINUTE,0);
	// cal2.set(Calendar.SECOND,0);
	//
	// Date fromDate = cal2.getTime();
	//
	// cal2.set(Calendar.HOUR,23);
	// cal2.set(Calendar.MINUTE,59);
	// cal2.set(Calendar.SECOND,59);
	//
	// Date toDate = cal2.getTime();

	public Account findOne(int accNo) throws invalidAccountException {

		for (int i = 0; i < accArray.size(); i++) {
			if (accArray.get(i) == null) {
				break;
			}
			if (accArray.get(i).getAccountNo() == accNo) {
				return accArray.get(i);
			}
		}
		throw new invalidAccountException("Account not found in AccountRepo");

	}

	public Account fundTransfer(int accNoTo, int accNoFrom, double amount) {
		for (Account acc : accArray) {
			if (acc.getAccountNo() == accNoTo) {

				Date dateOfTransfer = new Date();

				acc.setBalance(acc.getBalance() + amount);
				double currentBalanceOfReceiver = acc.getBalance();

				String textReceiver = "$" + amount + "was transferred to your bank account";

				acc.addTransaction(new Transaction(GenerateId.generateTransId(), dateOfTransfer, textReceiver, amount,
						currentBalanceOfReceiver));
			}
		}

		for (Account acc : accArray) {
			if (acc.getAccountNo() == accNoFrom) {

				Date dateOfTransfer = new Date();

				acc.setBalance(acc.getBalance() - amount);
				double currentBalanceOfSender = acc.getBalance();

				String textSender = "$" + amount + "was transferred from your bank account";

				acc.addTransaction(new Transaction(GenerateId.generateTransId(), dateOfTransfer, textSender, amount,
						currentBalanceOfSender));
			}
		}

		return null;

	}

}
