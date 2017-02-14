package fr.epita.iam.business;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
import fr.epita.iam.services.SOut;

public class UpdateActivity {
	
	 private UpdateActivity() {
		    throw new IllegalAccessError("Utility class");
		  }


	
	public static void execute(Scanner scanner) throws SQLException{
		SOut sout = new SOut();
		sout.output("Identity Update");
		sout.output("Here are the available entries:");
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		List<Identity> list = identityJDBCDAO.readAllIdentities();
		for(Identity element : list){
			sout.output(element.toString());
		}
		sout.output("Which element would you like to update?");
		String uid = scanner.nextLine();
		int found = 0;
		for(Identity element : list){
			sout.output(element.getUid());
			if (uid.equals(element.getUid()))
			{
				found = 1;
			}
		}
		if(found == 0){
			sout.output("The UID is not found please try again!!");
			return;
		}
			
				sout.output("Enter the Display Name");
				String displayName = scanner.nextLine();
				sout.output("Please input the email address");
				String email = scanner.nextLine();
				sout.output("Please input the birth date(YYYY-MM-DD)");
				String birthDate = scanner.nextLine();		
				Identity identity = new Identity(uid,displayName, email, birthDate);
				
			
				//persist the identity somewhere
				sout.output("This is the identity you updated");
				
				IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
				
					identityWriter.update(identity);
				
				sout.output("Successfully Updated!");
		}
	}


