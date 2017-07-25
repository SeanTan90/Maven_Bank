package assignment.bank.exceptions;

public class insufficientBalanceException extends Exception {

	
	public insufficientBalanceException(String reason) {
		super(reason);
	}

}
