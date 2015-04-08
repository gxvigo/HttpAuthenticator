package samples.authentication;

import java.io.IOException;
import samples.authentication.ValidateCredentials;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

/**
 * Servlet implementation class HttpBasicAuthenticator
 */
@WebServlet("/HttpBasicAuthenticator")
public class HttpBasicAuthenticator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpBasicAuthenticator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username;
		String password;
		Boolean authenticated = false;
		
		System.out.println("### In HttpBasicAuthenticator");
		System.out.println("### In HttpBasicAuthenticator -  doGet");

		// Will contain "Basic Y3243mr43r3=="
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null) {
			System.out.println("### In HttpBasicAuthenticator - authorizationHeader missing");
			response.setStatus(403);
		} else { 
			System.out.println("### In HttpBasicAuthenticator - authorizationHeader: " + authorizationHeader);
			assert authorizationHeader.substring(0, 6).equals("Basic ");
			
			// Will contain "Y3243mr43r3=="
			String credentialsEncoded = authorizationHeader.substring(6);

			// Will contain "bob:password"
			
			byte[] decoded = Base64.decodeBase64(credentialsEncoded);
			String credentialsDencoded = new String(decoded, "UTF-8");
//			System.out.println(new String(decoded, "UTF-8") + "\n");
			
			System.out.println("### In HttpBasicAuthenticator - Decoded credentials: " + credentialsDencoded);

			// Will split the Authorization header in id and pwd
			String[] credentialDecodedData = credentialsDencoded.split("\\:");
			username = credentialDecodedData[0];
			password = credentialDecodedData[1];
			System.out.println("### In HttpBasicAuthenticator - Username: " + username);
			System.out.println("### In HttpBasicAuthenticator - Password: " + password);
			
			// invoke service to validate user
			authenticated = ValidateCredentials.doValidateCredentials(username, password);
			if (authenticated == false) {
				System.out.println("### In HttpBasicAuthenticator - Wrong name or password for user : " + username);
				response.setStatus(403);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
