package bankofS.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Bos.Exceptions.TransferLimitExcessException;
import bankofS.entities.Account;
import bankofS.log.TransferLog;

public class TransferManager {
	
	public  static boolean maintainTransaction(Account from, Account to, double transferAmount, String mode) throws TransferLimitExcessException {
		
		boolean istransfer=false;
		
        istransfer=TransferLimitManager.checkTransferLimit(from, transferAmount);
		
		if(istransfer) {
			from.setBalance(from.getBalance()-transferAmount);
			to.setBalance(to.getBalance()+transferAmount);
			istransfer=true;
			
			
			ArrayList<Object> transfer = new ArrayList<Object>();
			
			transfer.add(from.getAccountNo());
			transfer.add(transferAmount);
			transfer.add(to.getAccountNo());
			transfer.add(to.getBalance());
			transfer.add(LocalDate.now());
			transfer.add(mode);
			
			
			TransferLog.setTransferLog(transfer);
		}
		
		return istransfer;
	}
}
