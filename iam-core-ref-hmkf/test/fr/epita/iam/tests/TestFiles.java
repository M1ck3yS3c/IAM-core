/**
 * 
 */
package fr.epita.iam.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.FileIdentityDAO;

/**
 * @author tbrou
 *
 */
public class TestFiles {

	public static void main(String[] args) throws IOException {

		FileIdentityDAO identityDAO = new FileIdentityDAO("tests.txt");
		identityDAO.write(new Identity("123", "Thomas Broussard", "thomas.broussard@gmail.com","2016-02-14"));
		identityDAO.write(new Identity("456", "Clément Serrano", "clement.serrano@natsystem.fr","2016-01-01"));

		
		List<Identity> list = identityDAO.readAllIdentities();
		System.out.println(list);

	}

}
