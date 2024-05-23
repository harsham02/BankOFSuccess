package bankofS.impl;

import java.time.LocalDate;

import bankofS.entities.Account;
import bankofS.entities.Current;

public class CurrentImpl implements IAccountImpl{
	 public boolean open(Account account) {

	        //Declaration
	        boolean isOpened = false;

	        //Logic
	        var current = (Current)account;
	   
	        account.setActivatedDate(LocalDate.now());
	        current.setActive(true);
	        isOpened = true;

	        //Return status
	        return isOpened;
	    }
}
