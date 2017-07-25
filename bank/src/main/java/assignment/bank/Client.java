package assignment.bank;

import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.insufficientBalanceException;
import assignment.bank.exceptions.insufficientFundException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.service.Service;
import assignment.bank.utilities.GenerateId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	public static void main(String[] args) throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientFundException, insufficientBalanceException, ParseException {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse("2017-07-25");
		Date date2 = sdf.parse("2017-07-26");
			
		//Assign 5 unique bank ID to 5 variables to be passed
		int acc1BankAccId = GenerateId.generateBankAccId();
		int acc2BankAccId = GenerateId.generateBankAccId();
		int acc3BankAccId = GenerateId.generateBankAccId();
		int acc4BankAccId = GenerateId.generateBankAccId();
		int acc5BankAccId = GenerateId.generateBankAccId();
		
			
		//Create 5 bank accounts with unique bank ID and beginning balance of zero
		Account account1 = new Account(acc1BankAccId, 120);
		Account account2 = new Account(acc2BankAccId, 150);
		Account account3 = new Account(acc3BankAccId, 180);
		Account account4 = new Account(acc4BankAccId, 200);
		Account account5 = new Account(acc5BankAccId, 240);
		
		//Create a new Service object to call Service methods upon
		Service service = new Service();
		
		//Create 5 new accounts
		service.createAccount(account1);
		service.createAccount(account2);
		service.createAccount(account3);
		service.createAccount(account4);
		service.createAccount(account5);
		
		// Deposit 50 dollars into account1
		service.deposit(acc1BankAccId, 50);
		
		//Withdraw 75 dollars from account1
		service.withdraw(acc1BankAccId, 75);
		
		//Fund transfer from account1 to account2
		service.fundTransfer(acc2BankAccId, acc1BankAccId, 50);
		
		//Retrieve the last 10 transactions for an account
		service.print10Transactions(acc1BankAccId);
		
		//Retrieve the transactions between 2 dates
		service.printTransactions(acc1BankAccId, date1, date2);
		
	}

}
