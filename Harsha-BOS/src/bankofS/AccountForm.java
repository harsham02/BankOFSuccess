package bankofS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Bos.Exceptions.AccountAlreadyClosedException;
import Bos.Exceptions.AccountDoesNotExistException;
import Bos.Exceptions.InvalidAgeForOpeningAnAccountException;
import Bos.Exceptions.InvalidPinException;
import Bos.Exceptions.PinCheckException;
import Bos.Exceptions.TransferLimitExcessException;
import Bos.Exceptions.WrongPrivilegeException;
import Bos.Exceptions.mobileNumberNotValidException;
import Bos.Exceptions.wrongInsuranceTypeException;
import bankofS.Enum.Privilege;
import bankofS.entities.Account;
import bankofS.entities.Current;
import bankofS.entities.Insurance;
import bankofS.entities.Savings;
import bankofS.entities.Transfer;
import bankofS.log.AccountLog;
import bankofS.manager.AccountManager;
import bankofS.manager.AccountsReportManager;



public class AccountForm {
	private static String accType;
	Account account;
	Scanner sc=new Scanner(System.in);
	public void loadForm() throws PinCheckException, InvalidPinException, AccountDoesNotExistException, AccountAlreadyClosedException, TransferLimitExcessException, WrongPrivilegeException, mobileNumberNotValidException, wrongInsuranceTypeException {
		char repeat = 'y';
		int ch;
		do {
			
		    System.out.println("________Welcome to Bank Of Success _________");
			System.out.println();
			System.out.println("****=========================================================****");
			System.out.println("1. Open An New Account");
			System.out.println("2. Close Existing Account");
			System.out.println("3. Withdraw Funds");
			System.out.println("4. Deposit Funds");
			System.out.println("5. Transfer Funds");
			System.out.println("6. Show Account Details");
			System.out.println("7. Show Account Logs");
			System.out.println("8. Show Account Between Dates");
			System.out.println("9. Transaction details");
		    System.out.println("10. Apply Insurance");
			System.out.println("11.Exit");
			System.out.println("****=========================================================****");
			
			System.out.print("Enter choice: ");
			ch = sc.nextInt();

			switch (ch) {
			case 1:
				open();
				break;

			case 2:
				close();
				break;

			case 3:
				withdraw();
				break;

			case 4:
				deposit();
				break;

			case 5:
				transferFunds();
				break;
			case 6:
				showDetail();
				break;
			case 7:
				showAccountLogs();
				break;
			case 8:
				showAccountBetweenDates();
				break;
				
			case 9: 
				showTransactiondetails();
				break;
			case 10: 
			   Applyinsurance();
				break;	
				
			case 11:
				repeat = 'n';
				break;

			default:
				System.out.println("Wrong input!");

			}
		} while (repeat == 'y' || repeat == 'Y');

		System.out.println("Thank you!");

	}
	
	private void Applyinsurance() throws wrongInsuranceTypeException, AccountDoesNotExistException, InvalidPinException {
		
		informationForApplyinsurance();
		
	}
		
	protected void DisplayInsuranceStatus(boolean isActive) {
		System.out.println("Insurance Status is: "+isActive);
		if( isActive == true )
		{
			System.out.println("Insurance is Actvie Successfully");
		}
	}
	private void informationForApplyinsurance() throws wrongInsuranceTypeException, AccountDoesNotExistException, InvalidPinException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the Account Type (1.Savings, 2.Current)");
		String accType = sc.next();
		sc.nextLine();
		System.out.println("Enter the Account Number : ");
		long accNo = sc.nextInt();
		System.out.println("Enter the Pin Number : ");
		int pinNum = sc.nextInt();
		System.out.println("Enter Sum Insured (\n Savings Account Limit upto 1Cr \n Current Account Limit Upto 2Cr) : ");
		long sumInsured = sc.nextLong();
		System.out.println("\n Enter the Nominee Details ");
		System.out.println("\n Enter the Nominee Name : ");
		var nomineeName=sc.next();
		System.out.println("Enter the Nominee Relationship : ");
		String nomineeRelation = sc.next();
		sc.nextLine();
		
		insurance(accType,accNo,pinNum,sumInsured,nomineeName,nomineeRelation);
	}
	private void insurance(String accType, long accNo, int pinNum, long sumInsured, String nomineeName,
			String nomineeRelation) throws AccountDoesNotExistException, InvalidPinException {
		// TODO Auto-generated method stub
		boolean status = false;
		Insurance insurance = new Insurance();
		Account accounts = AccountManager.retrieveAccountFromAccNo(accNo);
		if(AccountManager.authenticatePin(accNo, pinNum)) {
			
			insurance.setNameOfInsurance(accounts.getAccountHolderName());
			status = AccountManager.insuranceDetails(insurance,accType,accNo,pinNum,sumInsured,nomineeName,nomineeRelation);
		} 
		if(status){
			System.out.println("Applied Successfully!!!");
			
		}
		
	}

       
        
 
	private void showTransactiondetails() {
	List<ArrayList<Transfer>> transferLog = AccountManager.showTransferLog();
	System.out.println(transferLog .size());
	for(int i=0; i<transferLog .size();i++)
	{
		System.out.println(transferLog .get(i));
	}
		
	}

	protected void open() throws WrongPrivilegeException, mobileNumberNotValidException{

	        //Declaration and Initialization
	        var sc = new Scanner(System.in);

	        //1. Get the type of account user wants to open
	        System.out.println("Which account do you want to open?");
	        System.out.println("Savings");
	        System.out.println("Current");
	            accType = sc.nextLine();

	        //2. Get data from the user - Savings or Current
	        if(accType.equals("Savings")){

	            //Get savings account information from the user
	            var savings = getSavingsAccountInformation();

	            //Validate the boundary rules here
	            if(validateSavingsAccountInfo(savings)){

	                //3. Open the account according to the account type given
	                //Call the manager to open the account
	                boolean isOpened = openAccount(savings, accType);

	                //4. Communicate the status back to the user
	                //Display the account opening status
	                displayAccountOpenStatus(isOpened);
	            }

	        }
	        else if(accType.equals("Current")){

	            //Get current account information from the user
	            var current = getCurrentAccountInformation();

	            //Validate the boundary rules here
	            if(validateCurrentAccountInfo(current)){


	                //3. Open the account according to the account type given
	                //Call the manager to open the account
	                boolean isOpened = openAccount(current, accType);

	                //4. Communicate the status back to the user
	                //Display the account opening status
	                displayAccountOpenStatus(isOpened);
	            }
	        }
	    }
	 protected Current getCurrentAccountInformation() throws WrongPrivilegeException{

	        //Declaration and Initialization
	        var scanner = new Scanner(System.in);

	        //Store data into the object
	        System.out.println("Enter Your Name : ");
	        String name = scanner.nextLine();

	        System.out.println("Enter Company Name : ");
	        String companyName = scanner.nextLine();

	        System.out.println("Enter Website URL : ");
	        String websiteURL = scanner.nextLine();

	        System.out.println("Enter Registration Number  : ");
	        String registrationNo = scanner.nextLine();

	        System.out.println("Enter Pin Number : ");
	        int pinNumber = scanner.nextInt();
	        
	        System.out.println("Choose Previlege");
	        System.out.println("1.PREMIUM, 2.GOLD, 3.SILVER");
	        String privilege=sc.next();

	        //Create the object and store data
	        var current = new Current();
	        current.setAccountHolderName(name);
	        current.setCompanyName(companyName);
	        current.setRegistrationNo(registrationNo);
	        current.setWebsiteURL(websiteURL);
	        current.setPinNo(pinNumber);
	        
	        if(privilege.equals("PREMIUM")) {
	        	current.setPrivilege(Privilege.PREMIUM);
	        }else if(privilege.equals("GOLD")) {
	        	current.setPrivilege(Privilege.GOLD);
	        }else if(privilege.equals("SILVER")) {
	        	current.setPrivilege(Privilege.SILVER);
	        }else {
	        	throw new WrongPrivilegeException("What You Choose As Privilege Is not exist");
	        }

	        //Returning the current object created
	        return current;
	    }

	    protected Savings getSavingsAccountInformation() throws WrongPrivilegeException{

	        //Declaration and Initialization
	        var scanner = new Scanner(System.in);

	        //Store data into the object
	        System.out.println("Enter Your Name : ");
	        String name = scanner.nextLine();

	        System.out.println("Enter Gender : ");
	        String gender = scanner.nextLine();
	        
	        System.out.println("Enter DOB: ");
	        String dob=scanner.nextLine();
	   
	        System.out.println("Enter Phone Number : ");
	        String phoneNumber = scanner.nextLine();

	        System.out.println("Enter Pin Number : ");
	        int pinNumber = scanner.nextInt();
	        
	        System.out.println("Choose Previlege");
	        System.out.println("1.PREMIUM, 2.GOLD, 3.SILVER");
	        String privilege=sc.next();

	        //Create the object and store data
	        var savings = new Savings();
	        savings.setAccountHolderName(name);
	        savings.setGender(gender);
	        savings.setPhoneNumber(phoneNumber);
	        savings.setPinNo(pinNumber);
	        savings.setActivatedDate(null);
	        
	        LocalDate  activated = LocalDate.now();
	        savings.setActivatedDate(activated);
	        
	        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");

			LocalDate localDate1=LocalDate.parse(dob, formatter);
			savings.setDateOfBirth(localDate1);
			
			 if(privilege.equals("PREMIUM")) {
		        	savings.setPrivilege(Privilege.PREMIUM);
		        }else if(privilege.equals("GOLD")) {
		        	savings.setPrivilege(Privilege.GOLD);
		        }else if(privilege.equals("SILVER")) {
		        	savings.setPrivilege(Privilege.SILVER);
		        }else {
		        	throw new WrongPrivilegeException("What You Choose As Privilege Is not exist");
		        }

	        //Returning the savings object created
	        return savings;
	    }

	    protected boolean validateSavingsAccountInfo(Savings savings) throws mobileNumberNotValidException{

	        //Declaration
	        boolean isValid = false;
             if(accType.equalsIgnoreCase("Savings"))
             {
            	 isValid = checkName(savings.getAccountHolderName());
            	 isValid =mobileNumber(savings.getPhoneNumber());
             }
//	        //Return the status
	        return isValid;
	    }

	    private boolean mobileNumber(String phoneNumber) throws mobileNumberNotValidException {
	    	boolean isValid = false;

			// Here you validate the number of digits
			String phoneNo = phoneNumber;
			String regex = "[0-9]{10}";
			if (phoneNo.matches(regex)) {
				isValid = true;
			} else {
				throw new mobileNumberNotValidException("PLEASE ENTER VALID MOBILE NUMBER");
			}
			return isValid;
		}

		protected boolean checkName(String name) {
			boolean isValid = false;
			
		     if(name != null)
		     {
		    	 isValid = true;
		     }
			return isValid;
		}
	  

		protected boolean validateCurrentAccountInfo(Current current){

	        //Declaration
	        boolean isValid = false;
	        if(accType.equalsIgnoreCase("Current"))
            {
           	 isValid = checkName(current.getAccountHolderName());
           	 isValid = companyName(current.getCompanyName());
           	 isValid = registrationNumber(current.getRegistrationNo());
          
           	 
           	 isValid = true;
           	 
            }  	       
	        //Return the status
	        return isValid;
	    }

	    private boolean registrationNumber(String registrationNo) {
	    	  boolean isValid = false;
			
	    	  
	    	  
	    	  
	    	  return isValid;
		}

		private boolean companyName(String companyName) {
		boolean isValid = false;
		
		if(companyName != null)
		{
			isValid = true;
		}
			return isValid;
		}

		protected void displayAccountOpenStatus(boolean isOpened){

	        if(isOpened){
	            System.out.println("Account Opened Successfully");
	        }
	        else
	            System.out.println("Sorry..... Couldn't open account");
	    }

	    protected boolean openAccount(Account account, String accType){

	        //Declaration
	        boolean isOpened = false;

	        //Logic
	        //Execute the logic of Opening the account;
	        var accountManager = new AccountManager(accType);
	        try {
	            isOpened = accountManager.open(account, accType);
	        } catch (InvalidAgeForOpeningAnAccountException e) {
	            e.printStackTrace();
	        }

	        //Return the status
	        return isOpened;
	    }
	    public void close() throws PinCheckException, InvalidPinException, AccountDoesNotExistException, AccountAlreadyClosedException {
	    	
			AcceptAccountInformationToCloseAccount();
			
		}
		protected void AcceptAccountInformationToCloseAccount() throws PinCheckException, InvalidPinException, AccountDoesNotExistException, AccountAlreadyClosedException {
			System.out.println("Enter Account number:");
			int accno=sc.nextInt();
			System.out.println("Enter Pin Number: ");
			int pin=sc.nextInt();
			close(accno,pin);
		}
		protected void DisplayAccountClosedStatus(boolean isClose) {
			System.out.println("isAccount closed ? "+isClose);
			if(isClose== true)
			{
				System.out.println("thank You, Your account closed.");
			} else {
			System.out.println("Sorry your account not closed");
			}
		}
		protected boolean CheckPinNumberLength(int pinno) throws PinCheckException {
			
			boolean isvalid=false;
			int length=String.valueOf(pinno).length();
			if(length==4) {
				isvalid=true;
			}else {
				throw new PinCheckException("Enter Pin With length 4");
			}
		
			return isvalid;
		}
		protected void close(int accno,int pin) throws PinCheckException, InvalidPinException, AccountDoesNotExistException, AccountAlreadyClosedException {
			boolean isClosed=true;
			boolean pincheck=CheckPinNumberLength(pin);
		    isClosed=AccountManager.close(accno,pin);
		    
		    DisplayAccountClosedStatus(isClosed);
		}
		protected void withdraw() throws PinCheckException, AccountDoesNotExistException, InvalidPinException, AccountAlreadyClosedException {
			AcceptAccountInformationtoWithdraw();
		}
		protected void AcceptAccountInformationtoWithdraw() throws PinCheckException, AccountDoesNotExistException, InvalidPinException, AccountAlreadyClosedException {
			System.out.println("Enter Account Number: ");
			int Accno=sc.nextInt();
			System.out.println("Enter Pin Number: ");
			int PinNo=sc.nextInt();
			System.out.println("Enter Withdraw Amount: ");
			double withdrawAmount=sc.nextDouble();
			withdraw(Accno,PinNo,withdrawAmount);
			
		}
		protected void withdraw(int Accno, int pinNo, double withdrawAmount) throws PinCheckException, AccountDoesNotExistException, InvalidPinException, AccountAlreadyClosedException {
			boolean iswithdraw=false;
			iswithdraw=CheckPinNumberLength(pinNo);
			if(iswithdraw) {
			iswithdraw=AccountManager.withdraw(Accno,pinNo,withdrawAmount);
			displayAccountwithdrawStatus(iswithdraw);
			}
		}
		protected void displayAccountwithdrawStatus(boolean iswithdraw) {
			System.out.println("Amount Withdraw Status is : "+iswithdraw);
			if(iswithdraw == true)
			{
				System.out.println("Amount Withdraw Sucessfully , check Your Details....");
			}
		}
		protected void deposit() throws AccountDoesNotExistException, AccountAlreadyClosedException {
			AcceptAccountInformationToDeposit();
		}
		protected void AcceptAccountInformationToDeposit() throws AccountDoesNotExistException, AccountAlreadyClosedException {
			System.out.println("Enter Account Number: ");
			int Accno=sc.nextInt();
			System.out.println("Enter Deposit Amount: ");
			double Damount=sc.nextDouble();
			deposit(Accno,Damount);
			
		}
		protected void deposit(int Accno, double Damount) throws AccountDoesNotExistException, AccountAlreadyClosedException {
			boolean isdeposit=false;
			isdeposit=AccountManager.deposit(Accno,Damount);
			displayAccountDepositStatus(isdeposit);
		}
		protected void displayAccountDepositStatus(boolean isdeposit) {
			System.out.println("Amount Deposited status : "+isdeposit);
			
			if(isdeposit == true)
			{
				System.out.println("Amount Deposited, Check Your Details");
			}
		}
		protected void transferFunds() throws PinCheckException, AccountDoesNotExistException, InvalidPinException, TransferLimitExcessException {
			 AcceptAccountInfromationToTransfer();
		}
		protected void AcceptAccountInfromationToTransfer() throws PinCheckException, AccountDoesNotExistException, InvalidPinException, TransferLimitExcessException {
		TransferInfo info = new TransferInfo();
		info.AccountInfromationToTransfer();
			
		}
		protected void transfer(Transfer tf) throws PinCheckException, AccountDoesNotExistException, InvalidPinException, TransferLimitExcessException {
			boolean istransfer=false;
			
			istransfer=CheckPinNumberLength(tf.getPinNo());
		
			istransfer=AccountManager.transfer(tf.getFromAcc(),tf.getPinNo(), tf.getToAcc(), tf.getTransferAmount(), tf.getMode());
			
			DisplayTransferStatus(istransfer);
			
		}
		protected void DisplayTransferStatus(boolean istransfer) {
			System.out.println("Amount Transfered Status is : "+istransfer);
			if( istransfer == true )
			{
				System.out.println("Thank  you for Using, Your Amount Transfered Successfully");
			}
		}
		
		 protected void showDetail() throws AccountDoesNotExistException {
			 AcceptAccountInformationToShowDetail();
				
			}
		
		protected void AcceptAccountInformationToShowDetail() throws AccountDoesNotExistException {
			System.out.println("Enter Account number:");
			int accno=sc.nextInt();
			System.out.println("Enter Account Pin Number: ");
			int pin=sc.nextInt();
			showDetails(accno,pin);
			
		}
	
		protected void showDetails(long accno,int pin) throws AccountDoesNotExistException {
		
			Account account=AccountManager.retrieveAccountFromAccNo(accno);
			if(account.getPinNo()==pin) {
			System.out.println("Name: "+account.getAccountHolderName());
			System.out.println("Account status is : "+account.isActive());
			System.out.println("Balance: "+account.getBalance());
			}
		}
		protected void showAccountLogs() {
			List<Account> accountsFromLog = AccountLog.GetAccountsFromLog();
			for(Account acc : accountsFromLog) {
				System.out.println("Account Holder Name : "+acc.getAccountHolderName());
		    	System.out.println("Account Number : "+acc.getAccountNo());
		    	System.out.println("Account Activated Date : "+acc.getActivatedDate());
		    	System.out.println("Account Status : "+acc.isActive());
		    	System.out.println("Current Balance : "+acc.getBalance());
			}
			
		}
		protected void showAccountBetweenDates() {
			AcceptFromToDates();
		}
		public void AcceptFromToDates() {
			System.out.println("Enter From Date: ");
			String fromdate=sc.next();
			System.out.println("Enter To Date: ");
			String todate=sc.next();
			showAccountBetweenDates(fromdate,todate);
			
		} 
		protected void showAccountBetweenDates(String fromDate, String toDate) {
		    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");

		    LocalDate from=LocalDate.parse(fromDate, formatter);
		    LocalDate to=LocalDate.parse(toDate, formatter);
		    
		    
		    List<Account> list = AccountsReportManager.GetAccountsOpenedBetweenDates(from, to);
	
		    for(Account acc : list)
		    {
		    	System.out.println("Account Holder Name : "+acc.getAccountHolderName());
		    	System.out.println("Account Number : "+acc.getAccountNo());
		
		    	System.out.println("Account Status : "+acc.isActive());
		    	System.out.println("Current Balance : "+acc.getBalance());
		    	
		    }
		  
		    int size = list.size(); 
		    System.out.println("Numbers of Account provided from " +fromDate+ "to " +toDate+ "are : "+size);
		     

		}
       
}
