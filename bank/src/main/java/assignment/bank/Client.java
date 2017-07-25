package assignment.bank;

import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.incorrectDateRangeException;
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

	public static void main(String[] args) throws negativeBalanceException, exceedWithdrawLimitException, invalidAccountException, insufficientFundException, insufficientBalanceException, ParseException, incorrectDateRangeException {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse("2017-07-25");
		Date date2 = sdf.parse("2017-07-26");
			
		//Create 5 customer objects
		Customer Ironman = new Customer("Ironman");
		Customer CaptainAmerica = new Customer("CaptainAmerica");
		Customer Thor = new Customer("Thor");
		Customer Hulk = new Customer("Hulk");
		Customer Hawkeye = new Customer("Hawkeye");
				
		
		//Create a new Service object to call Service methods upon
		Service service = new Service();
		
//		//Create 5 new accounts
//		Account ironmanAcc = new Account(Ironman, 100);
//		Account captainAmericaAcc = new Account (CaptainAmerica, 120);
//		Account thorAcc = new Account (Thor, 140);
//		Account hulkAcc = new Account (Hulk, 160);
//		Account hawkeyeAcc = new Account (Hawkeye, 180);
		
		
//		Account ironmanAcc = service.createAccount(Ironman, 100);
//		Account captainAmericaAcc =  service.createAccount(CaptainAmerica, 120);
//		Account thorAcc = service.createAccount(Thor, 140);
//		Account hulkAcc = service.createAccount(Hulk, 160);
//		Account hawkeyeAcc =service.createAccount(Hawkeye, 180);
//		
//		
//		
//		
//		
//		// Deposit 50 dollars into account1
//		service.deposit(ironmanAcc.getAccountNo(), 50);
//		
//		//Withdraw 75 dollars from account1
//		service.withdraw(ironmanAcc.getAccountNo(), 75);
//		
//		//Fund transfer from account1 to account2
//		service.fundTransfer(ironmanAcc.getAccountNo(), captainAmericaAcc.getAccountNo(), 50);
//		
//		//Retrieve the last 10 transactions for an account
//		service.print10Transactions(ironmanAcc.getAccountNo());
//		
//		//Retrieve the transactions between 2 dates
//		service.printTransactions(ironmanAcc.getAccountNo(), date1, date2);
		
	}

}
