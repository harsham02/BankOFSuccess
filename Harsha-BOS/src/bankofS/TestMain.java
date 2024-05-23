package bankofS;

import Bos.Exceptions.AccountAlreadyClosedException;
import Bos.Exceptions.AccountDoesNotExistException;
import Bos.Exceptions.InvalidPinException;
import Bos.Exceptions.PinCheckException;
import Bos.Exceptions.TransferLimitExcessException;
import Bos.Exceptions.WrongPrivilegeException;
import Bos.Exceptions.mobileNumberNotValidException;
import Bos.Exceptions.wrongInsuranceTypeException;

public class TestMain {

	public static void main(String[] args) throws PinCheckException, InvalidPinException, AccountDoesNotExistException, AccountAlreadyClosedException, TransferLimitExcessException, WrongPrivilegeException, mobileNumberNotValidException, wrongInsuranceTypeException {
		// TODO Auto-generated method stub
		AccountForm  form = new AccountForm();
	       form.loadForm();
	}

}
