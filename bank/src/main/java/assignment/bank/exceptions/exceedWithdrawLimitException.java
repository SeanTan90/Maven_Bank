package assignment.bank.exceptions;

public class exceedWithdrawLimitException extends Exception {
	
	public exceedWithdrawLimitException(String reason) {
		super(reason);
	}

}
