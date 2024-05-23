package bankofS.log;

import java.util.ArrayList;

import bankofS.entities.Insurance;

public class InsuranceLog {
	
	private static ArrayList<Insurance> insurances = new ArrayList<Insurance>();

	public void addInsurance(Insurance insurance) {
		insurances.add(insurance);
	}

	public ArrayList<Insurance> getInsurances() {
		return insurances;
	}
}
