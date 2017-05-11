package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.Village;
import view.HomeFrame;
import view.Location;
import view.LoginFrame;
import view.NewGameFrame;
import view.Tile;

public class Database {

	private String url;
	private String username;
	private String password;
	private Connection conn;

	public Database() {
		url = "jdbc:mysql://databases.aii.avans.nl:3306/yson_db?allowMultiQueries=true";
		username = "yson";
		password = "Ab12345";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createAccount(String loginName, String pass) {
		try {

			Statement st = (Statement) conn.createStatement();

			st.execute("INSERT INTO account (username, wachtwoord) VALUES ('" + loginName + "', '" + pass + "')");

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void basicExecuteQuery(String query) { // geef query mee
		System.out.println("Connecting database...");
		try {

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
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT username FROM speler WHERE username = '" + LoginFrame.username
					+ "' AND (speelstatus = 'geaccepteerd' OR speelstatus = 'uitdager')");

			while (rs.next()) {
				return true;
			}
			st.close();
			return false;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void accept(String challenger) {
		try {

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

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT username FROM speler WHERE volgnr = 1 AND idspel IN (SELECT idspel FROM speler WHERE speelstatus = 'uitgedaagde' AND username ='"
							+ LoginFrame.username + "')");
			return rs;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public ArrayList<String> getPlayersNotInAGame() {
		ArrayList<String> returnArray = new ArrayList<>();
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT username FROM account WHERE username NOT IN (SELECT username FROM speler WHERE speelstatus != 'uitgespeeld' AND speelstatus != 'geweigerd') AND username NOT LIKE '"
							+ LoginFrame.username + "'");

			while (rs.next()) {
				returnArray.add(rs.getString("username"));
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return returnArray;
	}

	public String getInviteStatus(String text) {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT speelstatus FROM speler WHERE username LIKE '" + text
					+ "' AND speelstatus != 'uitgespeeld' AND speelstatus != 'geweigerd'");

			while (rs.next()) {
				return rs.getString("speelstatus");
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return null;
	}

	public void invite(int gameNr, String user, int volgnr) {
		try {

			Statement st = (Statement) conn.createStatement();
			if (volgnr == 2) {
				st.execute("INSERT INTO speler VALUES (" + gameNr + ",'" + user + "','wit','uitgedaagde',0," + volgnr
						+ ")");
			} else if (volgnr == 3) {
				st.execute("INSERT INTO speler VALUES (" + gameNr + ",'" + user + "','blauw','uitgedaagde',0," + volgnr
						+ ")");
			} else if (volgnr == 4) {
				st.execute("INSERT INTO speler VALUES (" + gameNr + ",'" + user + "','oranje','uitgedaagde',0," + volgnr
						+ ")");
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

	}

	public int getHighestidspel() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT MAX(idspel) AS nr FROM spel");

			while (rs.next()) {
				return rs.getInt("nr");
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return 0;
	}

	public void createGame(int gameNr, boolean isRandom) {
		try {

			int random = (isRandom) ? 1 : 0;

			Statement st = (Statement) conn.createStatement();
			st.execute("INSERT INTO spel VALUES (" + gameNr + ",null,null,null,0,null," + random + ")");

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		try {

			Statement st = (Statement) conn.createStatement();
			st.execute(
					"INSERT INTO speler VALUES (" + gameNr + ",'" + LoginFrame.username + "','Rood','uitdager',0,1)");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public boolean gameNotStarted() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT speelstatus FROM speler WHERE idspel = " + HomeFrame.gameNr
					+ " AND speelstatus = 'uitgedaagd'");

			while (rs.next()) {
				return true;
			}
			st.close();
			return false;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void getGame(int gameNr) {

	}

	public int getGameNr() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT MAX(idspel) AS spelid FROM speler WHERE username = '" + LoginFrame.username + "'");

			while (rs.next()) {
				return rs.getInt("spelid");
			}
			st.close();
			return 0;

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public String getWool() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelergrondstofkaart AS sk ON s.username = sk.username "
							+ "JOIN grondstofkaart AS gk ON gk.idgrondstofkaart = sk.idgrondstofkaart "
							+ "WHERE s.username LIKE '" + LoginFrame.username + "'"
							+ "AND gk.idgrondstofsoort LIKE 'W'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getWheat() {
		try {

			System.out.println("Connected!");

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelergrondstofkaart AS sk ON s.username = sk.username "
							+ "JOIN grondstofkaart AS gk ON gk.idgrondstofkaart = sk.idgrondstofkaart "
							+ "WHERE s.username LIKE '" + LoginFrame.username + "'"
							+ "AND gk.idgrondstofsoort LIKE 'G'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getOre() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelergrondstofkaart AS sk ON s.username = sk.username "
							+ "JOIN grondstofkaart AS gk ON gk.idgrondstofkaart = sk.idgrondstofkaart "
							+ "WHERE s.username LIKE '" + LoginFrame.username + "'"
							+ "AND gk.idgrondstofsoort LIKE 'E'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public int getStone() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelergrondstofkaart AS sk ON s.username = sk.username "
							+ "JOIN grondstofkaart AS gk ON gk.idgrondstofkaart = sk.idgrondstofkaart "
							+ "WHERE s.username LIKE '" + LoginFrame.username + "'"
							+ "AND gk.idgrondstofsoort LIKE 'S'");
			rs.next();
			return Integer.parseInt(rs.getString("aantal"));

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getWood() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelergrondstofkaart AS sk ON s.username = sk.username "
							+ "JOIN grondstofkaart AS gk ON gk.idgrondstofkaart = sk.idgrondstofkaart "
							+ "WHERE s.username LIKE '" + LoginFrame.username + "'"
							+ "AND gk.idgrondstofsoort LIKE 'H'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getKnigth() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'" + "AND ok.naam LIKE 'ridder'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getInvention() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'uitvinding'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getRoadConstruction() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'uitvinding'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getMonopoly() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'monopolie'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getCathedral() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'kathedraal'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getLiblary() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'bibliotheek'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getMarkt() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'" + "AND ok.naam LIKE 'markt'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);

		}
	}

	public String getUniversity() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'universiteit'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);

		}
	}

	public ResultSet getChat() {
		try {
			

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT bericht FROM chatregel");
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

	public void addChatRow(String message) {
		try {

			// correct the string if contains a apostrophe as to not create an
			// invalid query.
			ArrayList<Character> messageArray = new ArrayList<Character>();
			for (char c : message.toCharArray()) {
				messageArray.add(c);
			}
			int i = messageArray.size();
			for (int index = 0; index < i; index++) {
				if (messageArray.get(index) == "'".toCharArray()[0]) {

					messageArray.add(index, "'".toCharArray()[0]);

				} else if (messageArray.get(index) == "\\".toCharArray()[0]) {

					messageArray.add(index, "\\".toCharArray()[0]);
					index++;
				}
			}
			String output = "";
			for (Character character : messageArray) {
				output = output + character.toString();
			}

			

			Statement st = (Statement) conn.createStatement();

			st.execute(
					"INSERT INTO `yson_db`.`chatregel` (`idspel`, `username`, `tijdstip`, `bericht`) VALUES ('771', 'dummy', '"
							+ LocalDateTime.now() + "', '" + output + "')");
			if (getRowCount(st.executeQuery("SELECT * FROM chatregel")) > 28) {
				deleteFirstChatRow();
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

	}

	public void deleteFirstChatRow() {
		try {
			

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM chatregel ORDER BY tijdstip");
			rs.isBeforeFirst();
			rs.next();
			int idSpel = rs.getInt(1);

			String username = rs.getString(2);
			Timestamp tijdstip = rs.getTimestamp(3);

			st.execute("DELETE FROM `yson_db`.`chatregel` WHERE `idspel`='" + idSpel + "' and`username`='" + username
					+ "' and`tijdstip`='" + tijdstip + "';");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public String getCongress() {
		try {

			Statement st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT count(*) AS aantal FROM speler AS s JOIN spelerontwikkelingskaart sok ON sok.username = s.username "
							+ "JOIN ontwikkelingskaart ok ON ok.idontwikkelingskaart = sok.idontwikkelingskaart "
							+ "WHERE sok.username LIKE  '" + LoginFrame.username + "'"
							+ "AND ok.naam LIKE 'parlement'");
			rs.next();
			return rs.getString("aantal");

		} catch (SQLException e) {
			throw new IllegalStateException(e);

		}
	}

	// WIP!
	public void updateBoard(Location[][] locations) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, username, password);
			Statement st = (Statement) conn.createStatement();

			for (int x = 0; x < 12; x++) {
				for (int y = 0; y < 12; y++) {
					// PLACEHOLDER verander dit later!
					int idspel = 711;
					// EINDE PLACEHOLDER

					Location currentLocation = locations[x][y];
					String tileQuery = "INSERT INTO 'yson_db'.'tegel' ('idspel', 'idtegel', 'x', 'y', 'idgrondstofsoort', 'idgetalfische') VALUES ";
					// insert the tile at this location into the database
					if (currentLocation.getTile() != null) {
						Tile currentTile = currentLocation.getTile();
						int idtegel = Integer.parseInt(x + "" + y);

						char idgrondstofsoort = currentTile.getTypeChar();
						int idgetalfiche = currentTile.getNumber();
						tileQuery = tileQuery + "('" + idspel + "', '" + idtegel + "', '" + x + "', '" + y + "', '"
								+ idgrondstofsoort + "', '" + idgetalfiche + "'),";
					}

					int buildingIDCounter = 0;
					if (currentLocation.getBuilding() != null) {
						buildingIDCounter++;
						Village currentBuilding = currentLocation.getBuilding();
						String stuksoort;

						if (currentBuilding instanceof Village) {
							stuksoort = "dorp";
						} else {
							stuksoort = "stad";
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
