package ua.di.dima;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

import ua.di.model.User;

@Component
public class UserDima {

	private static Connection conn;

	static {
		String url = null;
		String password = null;
		String username = null;

		try (InputStream in = UserDima.class.getClassLoader().getResourceAsStream("persistence.properties")) {
			Properties properties = new Properties();
			properties.load(in);
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Class.forName("org.postgresgl.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement("select * from users");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setName(rs.getString(1));
			user.setUrl(rs.getString(2));
			users.add(user);
		}
		return users;
	}

	public void add(User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("insert into users values (?,?,?)");
		{
			ps.setString(1, user.getName());
			ps.setString(2, user.getUrl());
			ps.execute();
		}

	}
}
