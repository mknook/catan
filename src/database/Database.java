package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.mysql.jdbc.Statement;

import view.LoginFrame;

public class Database {

	private String url;
	private String username;
	private String password;

	public Database() {
		url = "jdbc:mysql://databases.aii.avans.nl:3306/yson_db?allowMultiQueries=true";
		username = "yson";
		password = "Ab12345";
	}

	public void createAccount(String loginName, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			Statement st = (Statement) conn.createStatement();

			st.execute("INSERT INTO account (username, wachtwoord) VALUES ('" + loginName + "', '" + pass + "')");

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void basicExecuteQuery(String query) { // geef query mee
		System.out.println("Connecting database...");
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString(
						""/* zet hier de column die je wilt krijgen */)); // kan
																			// getString
																			// aanpassen
																			// naar
																			// bijv.
																			// getInt()
				// in deze while alles doen wat je met de info wilt doen (kan
				// ook while vervangen)
			}

			st.close();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public boolean login(String loginName, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT username, wachtwoord FROM account WHERE username=\"" + loginName
					+ "\" AND wachtwoord = \"" + pass + "\" LIMIT 1");
			while (rs.next()) {
				return true;
			}
			st.close();
			return false;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public int getInvites() {
		String user = LoginFrame.username;
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT COUNT(username) as aantal FROM speler WHERE username='" + user
					+ "' AND speelstatus = 'uitgedaagde'");
			while (rs.next()) {
				return rs.getInt("aantal");
			}
			st.close();
			return 0;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public boolean playerIsInGame() {
		String user = LoginFrame.username;
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT username FROM speler WHERE username = '" + user + "' AND speelstatus = 'geaccepteerd'");
			while (rs.next()) {
				if (rs.getString("username").equals(user)) {
					return true;
				}
			}
			st.close();
			return false;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void accept(String challenger) {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

			Statement st1 = (Statement) conn.createStatement();
			ResultSet rs = st1
					.executeQuery("SELECT idspel FROM speler WHERE speelstatus = 'uitgedaagde' AND username ='"
							+ LoginFrame.username + "'");
			int spelid = 0;
			if (rs.next()) {
				spelid = rs.getInt("idspel");
			}
			Statement st = (Statement) conn.createStatement();

			st.execute("UPDATE speler SET speelstatus = 'geaccepteerd' WHERE username = '" + LoginFrame.username
					+ "' AND idspel = " + spelid);

			st.close();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void deny(String challenger) {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

			Statement st1 = (Statement) conn.createStatement();
			ResultSet rs = st1
					.executeQuery("SELECT idspel FROM speler WHERE speelstatus = 'uitgedaagde' AND username ='"
							+ LoginFrame.username + "'");
			int spelid = 0;
			if (rs.next()) {
				spelid = rs.getInt("idspel");
			}
			Statement st = (Statement) conn.createStatement();

			st.execute("UPDATE speler SET speelstatus = 'geweigerd' WHERE username = '" + LoginFrame.username
					+ "' AND idspel = " + spelid);

			st.close();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public ResultSet getChallengers() {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT username FROM speler WHERE volgnr = 1 AND idspel IN (SELECT idspel FROM speler WHERE speelstatus = 'uitgedaagde' AND username ='"
							+ LoginFrame.username + "')");
			return rs;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	

	public ResultSet getChat(){
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Fetching chat!");

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT bericht FROM chatregel");
			return rs;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public int getRowCount(ResultSet resultSet) {
	    if (resultSet == null) {
	        return 0;
	    }
	    try {
	        resultSet.last();
	        return resultSet.getRow();
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        try {
	            resultSet.beforeFirst();
	        } catch (SQLException exp) {
	            exp.printStackTrace();
	        }
	    }
	    return 0;
	}

	public void addChatRow(String message){
		try {
			Connection conn = DriverManager.getConnection(url, username, password);

			Statement st = (Statement) conn.createStatement();

			st.execute("INSERT INTO `yson_db`.`chatregel` (`idspel`, `username`, `tijdstip`, `bericht`) VALUES ('771', 'dummy', '" + LocalDateTime.now() + "', '" + message +"')");

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
