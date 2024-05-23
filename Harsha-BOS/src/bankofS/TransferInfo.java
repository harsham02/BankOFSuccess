package bankofS;
import java.time.LocalDate;
import java.util.Scanner;

import Bos.Exceptions.AccountDoesNotExistException;
import Bos.Exceptions.InvalidPinException;
import Bos.Exceptions.PinCheckException;
import Bos.Exceptions.TransferLimitExcessException;
import bankofS.entities.Transfer;
public class TransferInfo {
	
	Scanner sc = new Scanner(System.in);
	
	Transfer tf = new Transfer();
	 
	protected void AccountInfromationToTransfer() throws PinCheckException, AccountDoesNotExistException, InvalidPinException, TransferLimitExcessException {
		
		System.out.println("Enter From Account Number: ");
		int facc=sc.nextInt();
		tf.setFromAcc(facc);
		
		System.out.println("Enter To Account Number: ");
		int tacc=sc.nextInt();
		tf.setToAcc(tacc);
		
		System.out.println("Enter From Account Pin Number");
		int pin=sc.nextInt();
		tf.setPinNo(pin);
		
		System.out.println("Enter Transfer amount: ");
		double amount=sc.nextDouble();
		tf.setTransferAmount(amount);
		
		System.out.println("Choose Transfer Mode: ");
		System.out.println("1.NEFT, 2.IMPS, 3.RTGS");
		String mode=sc.next();
		tf.setMode(mode);
		
		
		tf.setTransactionDate(LocalDate.now());
		
		AccountForm accForm = new AccountForm();
		accForm.transfer(tf);
		
	}
	

}
