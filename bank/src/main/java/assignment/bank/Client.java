package assignment.bank;

import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.service.Service;
import assignment.bank.utilities.GenerateId;

public class Client {

	public static void main(String[] args) throws negativeBalanceException, exceedWithdrawLimitException {
		
		//Create GenerateId object to call method to generate unique bank ID
		GenerateId generator = new GenerateId();
		
		//Assign 5 unique bank ID to 5 variables to be passed
		int acc1BankAccId = generator.generateBankAccId();
		int acc2BankAccId = generator.generateBankAccId();
		int acc3BankAccId = generator.generateBankAccId();
		int acc4BankAccId = generator.generateBankAccId();
		int acc5BankAccId = generator.generateBankAccId();
		
			
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
		service.deposit(account1, 50);
		
		//Withdraw 75 dollars from account1
		service.withdraw(account1, 75);
		
	}

}
