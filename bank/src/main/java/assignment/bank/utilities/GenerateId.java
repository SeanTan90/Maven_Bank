package assignment.bank.utilities;

public class GenerateId {
	
	private static int bankAccId = 10000;
	private static int transId = 10000;
	
	public int generateBankAccId() {
		return bankAccId++;
	}
	
	public int generateTransId() {
		return transId++;
	}

}
