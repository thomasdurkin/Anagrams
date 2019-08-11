import java.sql.*;

public class CheckWord {
	
	public int check(String word) throws SQLException {
		int points = 0;
		String url = "jdbc:mysql://localhost/words?serverTimezone=UTC";
		String user = "root";
		String password = "";
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		
		String query = "SELECT points FROM words WHERE word=?";
		PreparedStatement p = connection.prepareStatement(query);
		p.setString(1, word);
		ResultSet rs = p.executeQuery();
		if (!rs.isBeforeFirst()) {
			return points;
		}
		rs.next();
		points = rs.getInt(1);
		return points;
	}
}
