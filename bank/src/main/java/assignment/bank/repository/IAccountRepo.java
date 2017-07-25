package assignment.bank.repository;

import java.util.ArrayList;
import java.util.Date;

import assignment.bank.Account;
import assignment.bank.Transaction;
import assignment.bank.exceptions.exceedWithdrawLimitException;
import assignment.bank.exceptions.invalidAccountException;
import assignment.bank.exceptions.negativeBalanceException;

public interface IAccountRepo {
		
	public boolean addAccount(Account a);
	
	public Account findAccount(int accNo) throws invalidAccountException;

}


