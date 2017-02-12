package fr.epita.iamcore.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epita.iamcore.datamodel.Identity;


public class JDBCIdentityDAO {

	private Connection connection;
	
	public JDBCIdentityDAO() throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/IAMCORE;create=true","PRASAD","PRASAD");
		System.out.println(connection.getSchema());
	}
	
	public void writeIdentity(Identity identity) throws SQLException {
		String insertStatement = "insert into Identities (IDENTITIES_DISPLAYNAME, IDENTITIES_EMAIL, IDENTITIES_BIRTHDATE) "
				 + "values(?, ?, ?)";
		PreparedStatement ptsmt_insert = connection.prepareStatement(insertStatement);
		ptsmt_insert.setString(1, identity.getDisplayName());
		ptsmt_insert.setString(2, identity.getEmail());
		Date now = new Date();
		ptsmt_insert.setDate(3, new java.sql.Date(now.getTime()));
		
		ptsmt_insert.execute();
	}
	
	public List<Identity> readAll() throws SQLException {
		List<Identity> identities = new ArrayList<Identity>();
		
		PreparedStatement ptsmt_select = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs =ptsmt_select.executeQuery();
		while (rs.next()) {
			String displayName = rs.getString("IDENTITIES_DISPLAYNAME");
			String uid = String.valueOf(rs.getString("IDENTITIES_UID"));
			String email = rs.getString("IDENTITIES_EMAIL");
			Identity identity = new Identity(uid, displayName, email);
			identities.add(identity);
		}
		return identities;
		
	}	
	}
	

