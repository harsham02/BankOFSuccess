package bankofS.impl;

import java.time.LocalDate;

import Bos.Exceptions.InvalidAgeForOpeningAnAccountException;
import bankofS.entities.Account;
import bankofS.entities.Savings;

public class SavingsImpl implements IAccountImpl{
	 public boolean open(Account account) {

	        //Declaration
	        boolean isOpened = false;

	        //Logic
	        //Typecast into savings object. I need data from the object
	        var savings = (Savings)account;
	        LocalDate date = LocalDate.now();

	        try {
	            if((date.getYear() - savings.getDateOfBirth().getYear()) < 18 ) {
	                throw new InvalidAgeForOpeningAnAccountException("Your Age is Not Valid To create Account");
	            }
	            else {
	               
	                savings.setActive(true);
	                isOpened = true;
	            }

	        } catch (InvalidAgeForOpeningAnAccountException e) {
	            e.printStackTrace();
	        }

	        //Return status
	        return isOpened;
	    }
}
