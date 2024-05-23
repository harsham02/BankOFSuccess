package bankofS.entities;

import java.time.LocalDate;

public class Transfer {

	private long fromAcc;
	private long toAcc;
	private int pinNo;
    public long getFromAcc() {
		return fromAcc;
	}
	public void setFromAcc(long fromAcc) {
		this.fromAcc = fromAcc;
	}
	public long getToAcc() {
		return toAcc;
	}
	public void setToAcc(long toAcc) {
		this.toAcc = toAcc;
	}
	public int getPinNo() {
		return pinNo;
	}
	public void setPinNo(int pinNo) {
		this.pinNo = pinNo;
	}
	public double getTransferAmount() {
		return TransferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		TransferAmount = transferAmount;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	private double TransferAmount;
    private String mode;
    private LocalDate transactionDate;
}
