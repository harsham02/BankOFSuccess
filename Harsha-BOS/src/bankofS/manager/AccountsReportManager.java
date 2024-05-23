package bankofS.manager;

import java.time.LocalDate;
import java.util.*;

import bankofS.entities.Account;
import bankofS.log.AccountLog;


public class AccountsReportManager {
	private static List<Account> list=new ArrayList<Account>();
	
	public static List<Account>GetAccountsOpenedBetweenDates(LocalDate fromDate,LocalDate toDate){
		
		list=AccountLog.GetAccountsBetweenDates(fromDate, toDate);
		
		
		return list;
	}
}
