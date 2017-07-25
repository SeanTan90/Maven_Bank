package assignment.bank;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.incorrectDateRangeException;
import assignment.bank.exceptions.insufficientBalanceException;
import assignment.bank.exceptions.insufficientFundException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;
import assignment.bank.service.Service;
import assignment.bank.utilities.GenerateId;

public class TestCasesForAccount {

	@Test
	public void addAccountSuccess() throws insufficientBalanceException {
						
				Customer tester = new Customer("tester");
							
			
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				service.createAccount(tester, 100);
				try {
					assertEquals(10015, service.createAccount(tester, 100).getAccountNo());
				} catch (insufficientBalanceException e) {
						System.out.println(e.getMessage());
				}
	}
	
	@Test
	public void depositMoneySuccess() throws invalidAccountException, insufficientBalanceException {
	
				
				Customer tester1 = new Customer("tester1");
					
								
				//Create a new Service object to call Service methods upon
				Service service = new Service();
				
				try {
				Account testerAcc = service.createAccount(tester1, 100);
				assertEquals(150, service.deposit(testerAcc.getAccountNo(), 50).getBalance(), 0);
				} catch (invalidAccountException | insufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
	}
	
	
	
	@Test(expected = insufficientBalanceException.class) 
	public void addAccountWithInsufficientAmount() throws insufficientBalanceException, invalidAccountException {
	
				Customer tester2 = new Customer("tester2");
				
				Service service = new Service();
				service.createAccount(tester2, 10);
				

	}
	
	@Test 
	public void withdrawMoneySuccess() throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientBalanceException {
	
				Customer tester3 = new Customer("tester3");
						
				Service service = new Service();
				
				try{
				Account testerAcc1 = service.createAccount(tester3,  100);
				assertEquals(90, service.withdraw(testerAcc1.getAccountNo(), 10).getBalance(), 0);
				} catch (negativeBalanceException | exceedWithdrawLimitException | invalidAccountException | insufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
	}

	
	@Test(expected = negativeBalanceException.class)
	public void withdrawMoneyUntilNegativeBalance() throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientBalanceException {
	
				Customer tester4 = new Customer("tester4");
				
				Service service = new Service();
				Account testerAcc2 = service.createAccount(tester4,  100);
				
				service.withdraw(testerAcc2.getAccountNo(), 110);

				
	}


	@Test(expected = exceedWithdrawLimitException.class)
	public void withdrawMoneyExceedLimit() throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientBalanceException {

			Customer tester5 = new Customer("tester5");
					
			Service service = new Service();
			Account testerAcc3 = service.createAccount(tester5,  10000);
								
			service.withdraw(testerAcc3.getAccountNo(), 1200);
			
			
	}
	
	@Test (expected = invalidAccountException.class)
	public void depositMoneyIntoWrongAcc() throws invalidAccountException, insufficientBalanceException {
	
				Customer tester6 = new Customer("tester6");
						
					
				Service service = new Service();
				Account testerAcc4 = service.createAccount(tester6, 100);
				
				service.deposit(testerAcc4.getAccountNo() + 1, 50);
				
	}
	
	@Test (expected = invalidAccountException.class)
	public void withdrawMoneyFromWrongAcc() throws invalidAccountException, insufficientBalanceException, negativeBalanceException, exceedWithdrawLimitException {
	
			Customer tester7 = new Customer("tester7");
		
		
			Service service = new Service();
			Account testerAcc5= service.createAccount(tester7, 100);
		
			service.withdraw(testerAcc5.getAccountNo() + 1, 50);
				
	}
	
	
	@Test
	public void transferMoneySuccess() throws invalidAccountException, insufficientFundException, insufficientBalanceException {
			
			Customer tester8 = new Customer("tester8");
			Customer tester9 = new Customer("tester9");
			
			Service service = new Service();			
			
			
			try {
			Account testerAcc6= service.createAccount(tester8, 1000);
			Account testerAcc7= service.createAccount(tester9, 1000);
			assertEquals(900, service.fundTransfer(testerAcc6.getAccountNo(), testerAcc7.getAccountNo(), 100).getBalance(), 0);
			} catch(invalidAccountException | insufficientFundException | insufficientBalanceException e) {
				System.out.println(e.getMessage());
			}
			

	}
	
	
	@Test (expected = insufficientFundException.class)
	public void transferMoneyFailWithInsufficientFunds() throws invalidAccountException, insufficientFundException, insufficientBalanceException {
			
			Customer tester10 = new Customer("tester10");
			Customer tester11 = new Customer("tester11");
			
			Service service = new Service();			
			
			
			Account testerAcc8= service.createAccount(tester10, 1000);
			Account testerAcc9= service.createAccount(tester11, 1000);
			
			service.fundTransfer(testerAcc8.getAccountNo(), testerAcc9.getAccountNo(), 2000);
			
	}
	
	
	
	@Test (expected = invalidAccountException.class)
	public void transferMoneyFailInvalidAccount() throws invalidAccountException, insufficientFundException, insufficientBalanceException {
			
			Customer tester12 = new Customer("tester12");
			Customer tester13 = new Customer("tester13");
			
			Service service = new Service();			
			
			
			Account testerAcc10= service.createAccount(tester12, 1000);
			Account testerAcc11= service.createAccount(tester13, 1000);
			
			service.fundTransfer(testerAcc10.getAccountNo() + 1, testerAcc11.getAccountNo() + 1, 100);
			
	}
	
	@Test
	public void showAccountBalanceSuccess() throws invalidAccountException, insufficientBalanceException {
			
			Customer tester14 = new Customer("tester14");
			
			Service service = new Service();			
			
			try{
			Account testerAcc12= service.createAccount(tester14, 100);
			assertEquals(100 ,service.showBalance(testerAcc12.getAccountNo()).getBalance(), 0);
			} catch(invalidAccountException | insufficientBalanceException e) {
				System.out.println(e.getMessage());
			}
			
			
	}
	
	
	@Test (expected = invalidAccountException.class)
	public void showAccountBalanceFail() throws invalidAccountException, insufficientBalanceException {
			
			Customer tester15 = new Customer("tester15");
			
			Service service = new Service();			
			
			
			Account testerAcc13= service.createAccount(tester15, 100);
			int testerAcc13NoMinus1 = testerAcc13.getAccountNo() - 1;
			service.showBalance(testerAcc13NoMinus1).getBalance();
			
	}
	
	@Test
	public void print10TransactionsSuccess() throws invalidAccountException, insufficientBalanceException, negativeBalanceException, exceedWithdrawLimitException {
			
			Customer tester16 = new Customer("tester16");
			
			Service service = new Service();			
			
			Account testerAcc14= service.createAccount(tester16, 100);
			
			//14 transactions
			service.deposit(testerAcc14.getAccountNo(), 20);
			service.deposit(testerAcc14.getAccountNo(), 30);
			service.deposit(testerAcc14.getAccountNo(), 50);
			service.withdraw(testerAcc14.getAccountNo(), 70);
			service.withdraw(testerAcc14.getAccountNo(), 40);
			service.deposit(testerAcc14.getAccountNo(), 30);
			service.deposit(testerAcc14.getAccountNo(), 10);
			service.withdraw(testerAcc14.getAccountNo(), 10);
			service.withdraw(testerAcc14.getAccountNo(), 20);
			service.withdraw(testerAcc14.getAccountNo(), 50);
			service.deposit(testerAcc14.getAccountNo(), 10);
			service.deposit(testerAcc14.getAccountNo(), 20);
			service.deposit(testerAcc14.getAccountNo(), 50);
			service.withdraw(testerAcc14.getAccountNo(), 60);
			
			
			try{
			assertEquals(10, service.print10Transactions(testerAcc14.getAccountNo()).size());
			} catch (invalidAccountException e) {
				System.out.println(e.getMessage());
			}
			
	}
	
	@Test (expected = invalidAccountException.class)
	public void print10TransactionsFail() throws invalidAccountException, insufficientBalanceException, negativeBalanceException, exceedWithdrawLimitException {
			
			Customer tester17 = new Customer("tester17");
			
			Service service = new Service();			
			
			Account testerAcc15= service.createAccount(tester17, 100);
			
			//14 transactions
			service.deposit(testerAcc15.getAccountNo(), 20);
			service.deposit(testerAcc15.getAccountNo(), 30);
			service.deposit(testerAcc15.getAccountNo(), 50);
			service.withdraw(testerAcc15.getAccountNo(), 70);
			service.withdraw(testerAcc15.getAccountNo(), 40);
			service.deposit(testerAcc15.getAccountNo(), 30);
			service.deposit(testerAcc15.getAccountNo(), 10);
			service.withdraw(testerAcc15.getAccountNo(), 10);
			service.withdraw(testerAcc15.getAccountNo(), 20);
			service.withdraw(testerAcc15.getAccountNo(), 50);
			service.deposit(testerAcc15.getAccountNo(), 10);
			service.deposit(testerAcc15.getAccountNo(), 20);
			service.deposit(testerAcc15.getAccountNo(), 50);
			service.withdraw(testerAcc15.getAccountNo(), 60);
			
			service.print10Transactions(testerAcc15.getAccountNo()-1);
	}
	
	
	@Test
	public void printTransactionsSuccessForDates() throws invalidAccountException, insufficientBalanceException, negativeBalanceException, exceedWithdrawLimitException, incorrectDateRangeException {
			
		Calendar cal2 = Calendar.getInstance();
		 cal2.set(Calendar.MONTH,6);
		 cal2.set(Calendar.DATE,25);
		 cal2.set(Calendar.YEAR,2017);
		 cal2.set(Calendar.HOUR_OF_DAY,0);
		 cal2.set(Calendar.MINUTE,0);
		 cal2.set(Calendar.SECOND,0);
		
		 Date fromDate = cal2.getTime();
		
		 cal2.set(Calendar.MONTH,6);
		 cal2.set(Calendar.DATE,25);
		 cal2.set(Calendar.YEAR,2017);
		 cal2.set(Calendar.HOUR_OF_DAY,23);
		 cal2.set(Calendar.MINUTE,59);
		 cal2.set(Calendar.SECOND,59);
		 
		 Date toDate = cal2.getTime();		
		 		 
		
			Customer tester18 = new Customer("tester18");
			
			Service service = new Service();			
			
			Account testerAcc16= service.createAccount(tester18, 100);
			
			//14 transactions
			service.deposit(testerAcc16.getAccountNo(), 20);
			service.deposit(testerAcc16.getAccountNo(), 30);
			service.deposit(testerAcc16.getAccountNo(), 50);
			service.withdraw(testerAcc16.getAccountNo(), 70);
			service.withdraw(testerAcc16.getAccountNo(), 40);
			service.deposit(testerAcc16.getAccountNo(), 30);
			service.deposit(testerAcc16.getAccountNo(), 10);
			service.withdraw(testerAcc16.getAccountNo(), 10);
			service.withdraw(testerAcc16.getAccountNo(), 20);
			service.withdraw(testerAcc16.getAccountNo(), 50);
			service.deposit(testerAcc16.getAccountNo(), 10);
			service.deposit(testerAcc16.getAccountNo(), 20);
			service.deposit(testerAcc16.getAccountNo(), 50);
			service.withdraw(testerAcc16.getAccountNo(), 60);
			
			
			try {
				assertEquals(14, service.printTransactions(testerAcc16.getAccountNo(), fromDate, toDate).size());
				} catch (invalidAccountException e) {
					System.out.println(e.getMessage());
				}
			
		
	}
	
	@Test (expected = incorrectDateRangeException.class) 
	public void printTransactionsFailForDates() throws invalidAccountException, insufficientBalanceException, negativeBalanceException, exceedWithdrawLimitException, incorrectDateRangeException {
			
		Calendar cal2 = Calendar.getInstance();
		 cal2.set(Calendar.MONTH,6);
		 cal2.set(Calendar.DATE,25);
		 cal2.set(Calendar.YEAR,2017);
		 cal2.set(Calendar.HOUR_OF_DAY,0);
		 cal2.set(Calendar.MINUTE,0);
		 cal2.set(Calendar.SECOND,0);
		
		 Date fromDate = cal2.getTime();
		
		 cal2.set(Calendar.MONTH,6);
		 cal2.set(Calendar.DATE,20);
		 cal2.set(Calendar.YEAR,2017);
		 cal2.set(Calendar.HOUR_OF_DAY,23);
		 cal2.set(Calendar.MINUTE,59);
		 cal2.set(Calendar.SECOND,59);
		 
		 Date toDate = cal2.getTime();		
		 		 
		
			Customer tester19 = new Customer("tester18");
			
			Service service = new Service();			
			
			Account testerAcc17= service.createAccount(tester19, 100);
			
			//14 transactions
			service.deposit(testerAcc17.getAccountNo(), 20);
			service.deposit(testerAcc17.getAccountNo(), 30);
			service.deposit(testerAcc17.getAccountNo(), 50);
			service.withdraw(testerAcc17.getAccountNo(), 70);
			service.withdraw(testerAcc17.getAccountNo(), 40);
			service.deposit(testerAcc17.getAccountNo(), 30);
			service.deposit(testerAcc17.getAccountNo(), 10);
			service.withdraw(testerAcc17.getAccountNo(), 10);
			service.withdraw(testerAcc17.getAccountNo(), 20);
			service.withdraw(testerAcc17.getAccountNo(), 50);
			service.deposit(testerAcc17.getAccountNo(), 10);
			service.deposit(testerAcc17.getAccountNo(), 20);
			service.deposit(testerAcc17.getAccountNo(), 50);
			service.withdraw(testerAcc17.getAccountNo(), 60);
			
			service.printTransactions(testerAcc17.getAccountNo(), fromDate, toDate);			
				
				
			
		
	}

	
	

}
