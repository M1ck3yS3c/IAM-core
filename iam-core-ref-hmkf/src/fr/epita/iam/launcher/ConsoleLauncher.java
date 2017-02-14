/**
 * 
 */
package fr.epita.iam.launcher;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.business.CreateActivity;
import fr.epita.iam.business.DeleteActivity;
import fr.epita.iam.business.UpdateActivity;
import fr.epita.iam.services.SOut;

/**
 * @author tbrou
 *
 */
public class ConsoleLauncher {
	
	 private ConsoleLauncher() {
		    throw new IllegalAccessError("Utility class");
		  }

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		SOut sout = new SOut();
		String choice;
		sout.output("Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);
		
		//authentication
		if (!authenticate(scanner)){
			end(scanner);
			return;
		}
		
		//menu
		do{
			sout.output("Please select an action :");
			sout.output("a. create an Identity");
			sout.output("b. modify an Identity");
			sout.output("c. delete an Identity");
			sout.output("d. quit");
			choice = scanner.nextLine();
			switch (choice) {
			case "a":
				//Create
				CreateActivity.execute(scanner);
				break;
			case "b":
				//Modify
				UpdateActivity.execute(scanner);
				break;
				
			case "c":
				//Delete
				DeleteActivity.execute(scanner);
				break;
				
			case "d":
				//Quit
				break;
				
			default:
				sout.output("Your choice is not recognized");
				break;
			}
	
			
			
		}while(!choice.equals(String.valueOf('d')));
		end(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		SOut sout = new SOut();
		sout.output("Thanks for using this application, good bye!");
		scanner.close();
	}

	/**
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		SOut sout = new SOut();
		sout.output("Please type your login : ");
		String login = scanner.nextLine();
		
		sout.output("Please type your password : ");
		String password = scanner.nextLine();
		
		if ("adm".equals(login) && "pwd".equals(password)){
			sout.output("Athentication was successful");
			return true;
		}else{
			sout.output("Athentication failed");
			return false;
		}
	}

}
