/**
 * 
 */
package fr.epita.iam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoInitializationException;

/**
 * @author tbrou
 *
 */
public class IdentityJDBCDAO {

	private Connection currentConnection;

	/**
	 * 
	 */
	public IdentityJDBCDAO() {
			
		try {
			getConnection();
		} catch (SQLException e) {
			DaoInitializationException die = new DaoInitializationException();
			die.initCause(e);
			throw die;
		}
	}

	/**
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		try {
			this.currentConnection.getSchema();
		} catch (Exception e) {
			String user = "hmkf";
			String pkey = "epita";
			String connectionString = "jdbc:derby://localhost:1527/IAM;create=true";
			this.currentConnection = DriverManager.getConnection(connectionString, user, pkey);
			
		}
		return this.currentConnection;
	}

	
	
	/**
	 * Read all the identities from the database
	 * @return
	 * @throws SQLException
	 */
	public List<Identity> readAllIdentities() throws SQLException {
		List<Identity> identities = new ArrayList<>();

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITIES_UID");
			String displayName = rs.getString("IDENTITIES_DISPLAYNAME");
			String email = rs.getString("IDENTITIES_EMAIL");
			String dateOfBirth = rs.getString("IDENTITIES_BIRTHDATE");
			Identity identity = new Identity(String.valueOf(uid), displayName, email, dateOfBirth);
			identities.add(identity);
		}
		statement.close();
		return identities;
	}

	/**
	 * write an identity in the database
	 * @param identity
	 * @throws SQLException
	 */
	public void write(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITIES_DISPLAYNAME, IDENTITIES_EMAIL, IDENTITIES_BIRTHDATE) VALUES(?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getDateOfBirth());		
		pstmt.execute();
		pstmt.close();
	}
	
	public void update(Identity identity) throws SQLException {
		Connection connection = getConnection();
		
		String sqlInstruction = "UPDATE IDENTITIES SET IDENTITIES_DISPLAYNAME = ? , IDENTITIES_EMAIL = ? , IDENTITIES_BIRTHDATE = ? WHERE IDENTITIES_UID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getDateOfBirth());		
		pstmt.setString(4, identity.getUid());
		pstmt.execute();
		pstmt.close();
	}
	
	public void delete(Identity identity) throws SQLException{
		Connection connection = getConnection();
		String sqlInstruction = "DELETE FROM IDENTITIES WHERE IDENTITIES_UID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getUid());
		pstmt.execute();
		pstmt.close();
	}

}
