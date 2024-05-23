package bankofS.log;

import java.util.ArrayList;
import java.util.List;

import bankofS.entities.Transfer;

public class TransferLog {
	
	public static List<ArrayList<Transfer>> transferLog = new ArrayList<ArrayList<Transfer>>();
	
	public static void setTransferLog(ArrayList list)
	{
		 transferLog.addAll(list);
	}
	public static List<ArrayList<Transfer>> getTransferLog()
	{
		System.out.println(transferLog.size());
		return  transferLog;
	}
	
	public static void printLog() {
		
	}

}
