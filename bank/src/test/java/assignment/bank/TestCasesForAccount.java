package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.insufficientBalanceException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.service.Service;
import assignment.bank.utilities.GenerateId;

public class TestCasesForAccount {

	@Test
	public void addAccountSuccess() throws invalidAccountException, insufficientBalanceException {
						
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				
				assertEquals("New account successfully added!", service.createAccount(account1));
			
	}
	
	@Test
	public void depositMoneySuccess() throws invalidAccountException, insufficientBalanceException {
	
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				service.createAccount(account1);
				
				assertEquals(150, service.deposit(acc1BankAccId, 50).getBalance(), 0);
	}
	
	
	
	@Test(expected = insufficientBalanceException.class)
	public void addAccountWithInsufficientAmount() throws insufficientBalanceException, invalidAccountException {
	
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 10);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				
				service.createAccount(account1);
				

	}
	
	@Test
	public void withdrawMoneySuccess() throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientBalanceException {
	
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				service.createAccount(account1);				
				
				assertEquals(90, service.withdraw(acc1BankAccId, 10).getBalance(), 0);
	}

	
	@Test(expected = negativeBalanceException.class)
	public void withdrawMoneyUntilNegativeBalance() throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientBalanceException {
	
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				
				service.createAccount(account1);
				service.withdraw(acc1BankAccId, 110);
				
				
	}


	@Test(expected = exceedWithdrawLimitException.class)
	public void withdrawMoneyExceedLimit() throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientBalanceException {

			//Assign a unique bank ID to a variable to be passed
			int acc1BankAccId = GenerateId.generateBankAccId();
					
				
			//Create a bank account with unique bank ID and beginning balance of zero
			Account account1 = new Account(acc1BankAccId, 10000);
			
			//Create a new Service object to call Service methods upon
			Service service = new Service();
			service.createAccount(account1);
			
			service.withdraw(acc1BankAccId, 1200);
			
			
	}
	
	@Test (expected = invalidAccountException.class)
	public void depositMoneyIntoWrongAcc() throws invalidAccountException, insufficientBalanceException {
	
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				service.createAccount(account1);
				
				service.deposit(acc1BankAccId + 1, 50);
				
	}
	@Test (expected = invalidAccountException.class)
	public void withdrawMoneyFromWrongAcc() throws invalidAccountException, insufficientBalanceException, negativeBalanceException, exceedWithdrawLimitException {
	
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = GenerateId.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				service.createAccount(account1);
				
				service.withdraw(acc1BankAccId + 1, 50);
				
	}

}
