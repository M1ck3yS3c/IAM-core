/**
 * 
 */
package fr.epita.iam.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public class FileIdentityDAO {
	static SOut sout = new SOut();
	private PrintWriter writer;
	private Scanner scanner;

	/**
	 * @throws IOException 
	 * 
	 */

	public FileIdentityDAO(String fileName) throws IOException {
		this.writer = initWriter(new File(fileName));
		try {
			this.scanner = new Scanner(new File("tests.txt"));
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	public void write(Identity identity) {
		writer.println("---------IDENTITY:BEGIN-------");
		writer.println(identity.getDisplayName());
		writer.println(identity.getEmail());
		writer.println(identity.getUid());
		writer.println("---------IDENTITY:END---------");
		writer.flush();

	}
	
	public List<Identity> readAllIdentities(){
		List<Identity> readIdentities = new ArrayList<>();


		while (scanner.hasNextLine()) {
			// delimiter
			scanner.nextLine();
			String displayName = scanner.nextLine();
			String email = scanner.nextLine();
			String uid = scanner.nextLine();
			// delimiter
			scanner.nextLine();
			Identity readIdentity = new Identity(uid, displayName, email,"");
			readIdentities.add(readIdentity);
		}
		
		return readIdentities;

	}

	/**
	 * @param file
	 * @throws FileNotFoundException 
	 */
	private static PrintWriter initWriter(File file) throws IOException {
		if (!file.exists()) {
			try {
				// "risky" operations
				File parentFile = file.getParentFile();
				if (parentFile != null) {
					parentFile.mkdirs();
				}
				boolean created = file.createNewFile();
				if(created){
					sout.output("File Created!");
				}
			} catch (IOException e) {
				throw e;
			} finally {
				// always executed
			}
		}
		PrintWriter writer;
			writer = new PrintWriter(file);
		return writer;
	}

}
