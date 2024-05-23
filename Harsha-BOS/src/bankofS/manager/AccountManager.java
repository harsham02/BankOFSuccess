package bankofS.manager;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import Bos.Exceptions.AccountAlreadyClosedException;
import Bos.Exceptions.AccountDoesNotExistException;
import Bos.Exceptions.InvalidAgeForOpeningAnAccountException;
import Bos.Exceptions.InvalidPinException;
import Bos.Exceptions.TransferLimitExcessException;
import bankofS.Enum.TransferMode;
import bankofS.entities.Account;
import bankofS.entities.Insurance;
import bankofS.entities.Transfer;
import bankofS.impl.AccountImplFactory;
import bankofS.impl.IAccountImpl;
import bankofS.log.AccountLog;
import bankofS.log.TransferLog;

public class AccountManager {
	
	private static long accNo =1000;
	
	private static Map<Long, Account> allAccounts = new HashMap<Long,Account>();
	
	static IAccountImpl accountImpl;
	
	public AccountManager(String accType) {

      accountImpl = AccountImplFactory.create(accType);
	}
	
	public boolean open(Account account, String accType) throws InvalidAgeForOpeningAnAccountException {

        //Declaration
        boolean isOpened = false;

        IAccountImpl accountImpl = AccountImplFactory.create(accType);
        isOpened = accountImpl.open(account);
       // String accno=String.valueOf(account.getAccountNumber());
        
        if (isOpened) {
        	
        	
        	account.setAccountNo(generateAccNo());
        	
        	AccountLog.AddToLog(account, accType);
        
			allAccounts.put(account.getAccountNo(), account);
			System.out.println("your account number is : "+account.getAccountNo());
			
		}

        //Return the status
        return isOpened;
    }
	public static boolean insuranceDetails(Insurance insurance, String accType, long accNo, int pinNum,long sumInsured,String nomineeName,String nomineeRelation) 
			throws AccountDoesNotExistException  {
		// TODO Auto-generated method stub
		var status = false;
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();
		
		Account account = retrieveAccountFromAccNo(accNo);
		if(account.isActive()) {
			insurance.setNominee(nomineeName);
			if(accType.equals("Savings")) {
			insurance.setSumInsured(10000000);
			insurance.setExpiryMonth(month);
			insurance.setExpiryYear(year);
		} else if(accType.equals("Current")) {
			insurance.setSumInsured(20000000);
			insurance.setExpiryMonth(month);
			insurance.setExpiryYear(year);
		}
		insurance.setActive(true);
		status = true;
		}else {
			throw new AccountDoesNotExistException("Account Already Closed");
		}
		return status;
	}
	private long generateAccNo() {
		
		  accNo++;
		return accNo;
	}

	public static  boolean close(int accno,int pin)
			throws InvalidPinException, AccountDoesNotExistException, AccountAlreadyClosedException {

		boolean status = false;

		if (!authenticatePin(accno, pin) ){
			throw new InvalidPinException("Entere pin is invalid");
		}

		try {

			Account closeaccount = retrieveAccountFromAccNo(accno);

			// Checking if the account instance is present in the hashmap
	if (closeaccount != null) {
		status=authenticateStatus(accno);
			if(status) {
			closeaccount.setActive(false);
			closeaccount.setClosedDate(LocalDate.now());
			closeaccount.setBalance(0);
				}
			}		

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 
		return status;
	}

	public static Account retrieveAccountFromAccNo(long accountNumber) throws AccountDoesNotExistException {
		//IAccountImpl accountImpl;
		Account accountImpl;
		// checking if the key is present in the hashmap
		if (allAccounts.containsKey(accountNumber)) {

			accountImpl = allAccounts.get(accountNumber);

		} else {
			throw new AccountDoesNotExistException("Invalid Account Number");
		}

		// Returning the account instance
		return accountImpl;
	}
	public static boolean authenticatePin(long accNo, int pinNo) throws InvalidPinException, AccountDoesNotExistException {
		boolean flag = false;

	   Account a = retrieveAccountFromAccNo(accNo);

		if (a.getPinNo() != pinNo  || a.getPinNo()==0)
			throw new InvalidPinException("Invalid PIN number");

		if (a != null) {
			if (a.getPinNo() == pinNo) {
				flag = true;
			}
		}

		return flag;
	}
	public static boolean authenticateStatus(long accNo) throws AccountDoesNotExistException {
		boolean isActive=false;
		Account accounts=retrieveAccountFromAccNo(accNo);
		if(accounts.isActive()) {
			isActive=true;
		}
		return isActive;
	}
	public static boolean withdraw(long AccNo,int PinNo,double withdrawAmount) throws AccountDoesNotExistException, InvalidPinException, AccountAlreadyClosedException {
		boolean isWithdraw=false;
		
		Account withdrawAccount=retrieveAccountFromAccNo(AccNo);
		if(withdrawAccount.getAccountNo()==AccNo) {
			isWithdraw=authenticatePin(AccNo,PinNo);
			if(isWithdraw) {
				isWithdraw=authenticateStatus(AccNo);
				if(isWithdraw) {
					withdrawAccount.setBalance(withdrawAccount.getBalance()- withdrawAmount);
					isWithdraw=true;
				}else {
					throw new AccountAlreadyClosedException("Account is not in active");
				}
			}
		}
		return isWithdraw;
	}
	public static boolean deposit(long Accno,double Damount) throws AccountDoesNotExistException, AccountAlreadyClosedException {
		boolean isdeposit=false;
		
		Account depositAccount=retrieveAccountFromAccNo(Accno);
		if(depositAccount.getAccountNo()==Accno) {
			isdeposit=authenticateStatus(Accno);
			if(isdeposit) {
			depositAccount.setBalance(depositAccount.getBalance()+Damount);
			isdeposit=true;
			}else {
				throw new AccountAlreadyClosedException("Account was not in active");
			}
		}
			
		return isdeposit;
	}
	public static boolean transfer(long fromAccno, int PinNo, long toAccno, double transferamount, String  mode) throws AccountDoesNotExistException, InvalidPinException, TransferLimitExcessException {
		boolean istransfer=false;
		
		Account fromAccount=retrieveAccountFromAccNo(fromAccno);
		Account toAccount=retrieveAccountFromAccNo(toAccno);
		
		istransfer=authenticateStatus(fromAccno);
		istransfer=authenticateStatus(toAccno);
		
		istransfer=authenticatePin(fromAccno,PinNo);
		
		istransfer=modeValidity(mode);
		
		istransfer=TransferManager.maintainTransaction(fromAccount, toAccount, transferamount, mode);
		
		
		return istransfer;
	}
	public static boolean modeValidity(String mode) {

		for (TransferMode m : TransferMode.values()) {
			if (m.name().equals(mode)) {
				return true;
			}
		}

		return false;
	}
	
	
    public static List<ArrayList<Transfer>> showTransferLog()
    {
    	List<ArrayList<Transfer>> l = TransferLog.getTransferLog();
    	
    	return l;
    }
}
