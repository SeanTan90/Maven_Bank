package assignment.bank.utilities;

public class GenerateId {
	
	private static int bankAccId = 10000;
	private static int transId = 10000;
	
	public static int generateBankAccId() {
		return bankAccId++;
	}
	
	public static int generateTransId() {
		return transId++;
	}

}
