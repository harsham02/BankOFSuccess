package bankofS.entities;

import java.time.LocalDate;

public class Insurance {

	private int insuranceNo;
	private String name;
	
	// Based on savings or current account
	private long accountNumber;
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	private String insuranceName;
	private double sumInsured;
	private String nominee;
	private LocalDate issueDate;
	private int expiryMonth;
	private int expiryYear;
	private boolean isActive;
	private LocalDate dateIssued;
	
	private static int insuranceNoGenerator = 100;
	
	public Insurance() {
		
	}
	
	
	public LocalDate getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(LocalDate dateIssued) {
		this.dateIssued = dateIssued;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public int getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo() {
		this.insuranceNo = insuranceNoGenerator;
		insuranceNoGenerator++;
	}
	public String getNameOfInsurance() {
		return name;
	}
	public void setNameOfInsurance(String name) {
		this.name = name;
	}
	public double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nomineeName) {
		this.nominee = nomineeName;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public int getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public int getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "\n Insurance Details \n Account Number ="+accountNumber+" \n Insurance No = "+insuranceNo+"\n Name = "+insuranceName+"\n Sum insured = "+sumInsured+"\n Expiry Month = "+expiryMonth+
				"\n Expiry Year = "+expiryYear+"\n Date Of Issued ="+dateIssued+"\n isActive = "+isActive+" ";
	}
	
	
	
	
}
