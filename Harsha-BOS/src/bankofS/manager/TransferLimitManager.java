 package bankofS.manager;

import Bos.Exceptions.TransferLimitExcessException;
import bankofS.Enum.Privilege;
import bankofS.entities.Account;

public class TransferLimitManager {
	
	static  double totalSumAmount =0;
	public static boolean checkTransferLimit(Account from,double transferAmount) throws TransferLimitExcessException {
		
		boolean istransferlimit=false;
		
		double transferLimit = 0;

		 if(from.getPrivilege().equals(Privilege.SILVER))
			transferLimit = 25000;
		 else if(from.getPrivilege().equals(Privilege.GOLD))
			transferLimit = 50000;
		 else if(from.getPrivilege().equals(Privilege.PREMIUM))
			transferLimit = 100000;
		
		totalSumAmount  +=transferAmount ; 
		
		if(totalSumAmount > transferLimit) {
			throw new TransferLimitExcessException("Transfer Amount is greater than TransferLimit");
		}else {
			istransferlimit=true;
		}
		return istransferlimit;
	}
}
