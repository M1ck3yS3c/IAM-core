package fr.epita.iam.business;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
import fr.epita.iam.services.SOut;

public class DeleteActivity {
	static String uid;
	static String toDelete;
	
	private DeleteActivity() {
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
		sout.output("Which element would you like to delete?");
		uid = scanner.nextLine();
		int found = 0;
		for(Identity element : list){
			sout.output(element.getUid());
			if (uid.equals(element.getUid()))
			{
				found = 1;
				toDelete = element.getUid();
				
			}
		}
		if(found == 0){
			sout.output("The UID is not found please try again!!");
			return;
		}
		Identity identity = new Identity(toDelete,"","","");
		
		//persist the identity somewhere

		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		
			identityWriter.delete(identity);
		
		sout.output("Successfully Deleted!");
	}
}
