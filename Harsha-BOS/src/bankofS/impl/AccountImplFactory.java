package bankofS.impl;

public class AccountImplFactory {
	 public static IAccountImpl create(String accType){

	        IAccountImpl accountImpl = null;

	        if(accType.equals("Savings")){
	            accountImpl = new SavingsImpl();
	        }
	        else if(accType.equals("Current")){
	            accountImpl = new CurrentImpl();
	        }

	        return accountImpl;
	    }
}
