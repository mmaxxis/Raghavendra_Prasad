package fr.epita.iamcore.launcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.iamcore.datamodel.Identity;
import fr.epita.iamcore.services.JDBCIdentityDAO;


public class ConsoleLauncher {
	
	private static JDBCIdentityDAO dao;

	
	public static void main(String[] args) throws IOException, SQLException {
		System.out.println("Hello, welcome to the IAM application");
		Scanner scanner = new Scanner(System.in);
		dao = new JDBCIdentityDAO();
		
		
		System.out.println("Please enter your login");
		String login = scanner.nextLine();
		System.out.println("Please enter your password");
		String password = scanner.nextLine();
		
		if(!authenticate(login, password)){
			scanner.close();
			return;
		}
		
		// menu
		String answer = menu(scanner);
		
		switch (answer) {
		case "a":
			// creation
			createIdentity(scanner);
			break;
		case "b":
			
			break;
		case "c":
			
			break;
		case "d":
			listIdentities();
			break;
		default:
			System.out.println("This option is not recognized ("+ answer + ")");
			break;
		}
		
		scanner.close();

	}

	/**
	 * @throws SQLException 
	 * 
	 */
	private static void listIdentities() throws SQLException {
		System.out.println("This is the list of all identities in the system");
		List<Identity> list = dao.readAll();
		int size = list.size();
		for(int i = 0; i < size; i++){
			System.out.println( i+ "." + list.get(i));
		}
		
	}

	/**
	 * @param scanner
	 * @throws SQLException 
	 */
	private static void createIdentity(Scanner scanner) throws SQLException {
		System.out.println("You've selected : Identity Creation");
		System.out.println("Please enter the Identity display name");
		String displayName = scanner.nextLine();
		System.out.println("Please enter the Identity email");
		String email = scanner.nextLine();
		Identity newIdentity = new Identity(null, displayName, email);
		dao.writeIdentity(newIdentity);
		System.out.println("you succesfully created this identity :" + newIdentity);
	}

	/**
	 * @param scanner
	 * @return
	 */
	private static String menu(Scanner scanner) {
		System.out.println("You're authenticated");
		System.out.println("Here are the actions you can perform :");
		System.out.println("a. Create an Identity");
		System.out.println("b. Modify an Identity");
		System.out.println("c. Delete an Identity");
		System.out.println("d. List Identities");
		System.out.println("e. quit");
		System.out.println("your choice (a|b|c|d|e) ? : ");
		String answer = scanner.nextLine();
		return answer;
	}

	/**
	 * @param login
	 * @param password
	 */
	private static boolean authenticate(String login, String password) {

		return "adm".equals(login) && "pwd".equals(password);
	}


	
	}


