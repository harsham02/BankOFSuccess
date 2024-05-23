 package bankofS.entities;

import java.time.LocalDate;

import bankofS.Enum.Privilege;
import bankofS.impl.IAccountImpl;



public class Account implements IAccountImpl{
	
	    private long accountNo;
	    private String accountHolderName;
	    private int pinNo;
	    private double balance;
	    private boolean isActive;
	    private LocalDate activatedDate;
	    private LocalDate closedDate;
	    private Privilege privilege;
	    
	    
	    public long getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(long accountNo) {
			this.accountNo = accountNo;
		}
		public String getAccountHolderName() {
			return accountHolderName;
		}
		public void setAccountHolderName(String accountHolderName) {
			this.accountHolderName = accountHolderName;
		}
		public int getPinNo() {
			return pinNo;
		}
		public void setPinNo(int pinNo) {
			this.pinNo = pinNo;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public boolean isActive() {
			return isActive;
		}
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
		public LocalDate getActivatedDate() {
			return activatedDate;
		}
		public void setActivatedDate(LocalDate activatedDate) {
			this.activatedDate = activatedDate;
		}
		public LocalDate getClosedDate() {
			return closedDate;
		}
		public void setClosedDate(LocalDate closedDate) {
			this.closedDate = closedDate;
		}
		public Privilege getPrivilege() {
			return privilege;
		}
		public void setPrivilege(Privilege privilege) {
			this.privilege = privilege;
		}
		
		@Override
		public boolean open(Account account) {
			// TODO Auto-generated method stub
			return false;
		}

	   
	  
}
