/**
 * 
 */
package fr.epita.iam.business;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
import fr.epita.iam.services.SOut;

/**
 * @author tbrou
 *
 */
public class CreateActivity {
	
	private CreateActivity() {
	    throw new IllegalAccessError("Utility class");
	  }
	
	public static void execute(Scanner scanner) throws SQLException{
		SOut sout = new SOut();
		sout.output("Identity Creation");
		sout.output("please input the displayName");
		String displayName = scanner.nextLine();
		sout.output("please input the email address");
		String email = scanner.nextLine();
		sout.output("please input the birth date(YYYY-MM-DD)");
		String birthDate = scanner.nextLine();		
		Identity identity = new Identity("",displayName, email, birthDate);
		
	
		//persist the identity somewhere
		sout.output("this is the identity you created");
		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		identityWriter.write(identity);
		sout.output("creation Done");
		
	}
	

}
