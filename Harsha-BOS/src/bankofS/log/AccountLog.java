package bankofS.log;

import java.time.LocalDate;
import java.util.*;

import bankofS.entities.Account;


public class AccountLog {
	private static List<Account> Accounts= new ArrayList<Account>();
	
	public static void AddToLog(Account account,String accType) {
		Accounts.add(account);
	}
	
	public static List<Account> GetAccountsFromLog(){
		return Accounts;
	}
	public static List<Account> GetAccountsBetweenDates(LocalDate fromDate, LocalDate toDate){
		List<Account> list = new ArrayList<Account>();
		for(Account account :Accounts) {
			
		 LocalDate activatedDate = account.getActivatedDate();
		 
		 boolean checkDate = checkActivatedDateBetweenToDates(fromDate, toDate, activatedDate);
		 
		 if(checkDate)
		 {
			 list.add(account);
		 }
		}
		
		return list;
	}

	private static boolean checkActivatedDateBetweenToDates(LocalDate fromDate, LocalDate toDate, LocalDate activatedDate) {
		boolean isBetween = false;
		
		if(activatedDate.compareTo(fromDate) >=0 && activatedDate.compareTo(toDate) <=0)
		{
			isBetween = true;
		}
		return isBetween;
	}
}
