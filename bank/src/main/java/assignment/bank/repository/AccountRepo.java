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

	public boolean addAccount(Account a) {

		accArray.add(a);
		return true;
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
	
