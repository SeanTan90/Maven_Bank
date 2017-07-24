package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.service.Service;
import assignment.bank.utilities.GenerateId;

public class TestCasesForAccount {

	@Test
	public void addAccountSuccess() {
				//Create GenerateId object to call method to generate unique bank ID
				GenerateId generator = new GenerateId();
				
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = generator.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				
				assertEquals("New account successfully added!", service.createAccount(account1));
			
	}
	
	@Test
	public void depositMoneySuccess() {
				//Create GenerateId object to call method to generate unique bank ID
				GenerateId generator = new GenerateId();
				//Assign a unique bank ID to a variable to be passed
				int acc1BankAccId = generator.generateBankAccId();
						
					
				//Create a bank account with unique bank ID and beginning balance of zero
				Account account1 = new Account(acc1BankAccId, 100);
				
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				
				//Create a new account with 100 initial balance
				service.createAccount(account1);
				
				assertEquals(150, service.deposit(account1, 50).getBalance(), 0);
	}

}
