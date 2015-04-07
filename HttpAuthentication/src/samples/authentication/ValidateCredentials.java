package samples.authentication;

public class ValidateCredentials {
	
	static public boolean doValidateCredentials(String username, String password){
		
		System.out.println("### In doValidateCredentials");
		
		// This method validates any user and password but one: john:wrong;
		boolean authenticated = true;
		
		System.out.println("### In doValidateCredentials - Username: " + "[" + username + "]");
		System.out.println("### In doValidateCredentials - Password: " + "[" + password + "]");
		
		if (username.equals("john") && password.equals("wrong") ){
//		if (password.equals(passwordDump) ){
			
			System.out.println("### In doValidateCredentials - authentication error");
			authenticated = false;
		}
		
		return authenticated;
	}

}
