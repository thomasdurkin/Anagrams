package Model;
import java.io.*;
import java.sql.*;

public class LoadData {
	public static void main(String[] argv) throws SQLException, IOException {
		String url = "jdbc:mysql://localhost/words?serverTimezone=UTC";
		String user = "root";
		String password = "";
		
		System.out.println("Connecting to the database");
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("Successfully connected to database words");
		Statement statement = connection.createStatement();
		
		System.out.println("Preparing to load in data");
		BufferedReader reader = new BufferedReader(new FileReader("../data/words.txt"));
		String l = null;
		while ((l = reader.readLine()) != null) {
			String[] line = l.split(" ");
			for (String s: line) {
				if (s.length() == 3) {
					String query = "INSERT INTO words VALUES(\""+s+"\", 100)";
					statement.executeUpdate(query);
				}
				if (s.length() == 4) {
					String query = "INSERT INTO words VALUES(\""+s+"\", 200)";
					statement.executeUpdate(query);
				}
				if (s.length() == 5) {
					String query = "INSERT INTO words VALUES(\""+s+"\", 500)";
					statement.executeUpdate(query);
				}
				if (s.length() == 6) {
					String query = "INSERT INTO words VALUES(\""+s+"\", 2000)";
					statement.executeUpdate(query);
				}
			}
		}
		reader.close();
		System.out.println("Successfully loaded in all data");
	}
}
