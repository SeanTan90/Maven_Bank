package assignment.bank.exceptions;

public class negativeBalanceException extends Exception {
	
	public negativeBalanceException(String reason) {
		super(reason);
	}

}
