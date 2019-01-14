package ua.di.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.di.model.User;

@Component
public class UserDAO {

	@Autowired
	private DataSource ds;

	public List<User> getAll() throws SQLException {

		Connection conn = ds.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from users");

		List<User> users = new ArrayList<>();
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

		Connection conn = ds.getConnection();

		PreparedStatement ps = conn.prepareStatement("insert into users values (?,?)");
		{
			ps.setString(1, user.getName());
			ps.setString(2, user.getUrl());
			ps.execute();
		}

	}
}

//
//	@Autowired
////	
//	private JdbcTemplate jdbcTemplate;
//
//	public List<User> getAll() {
//		return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
//	}
//
//	public void add(User user) {
//		jdbcTemplate.update("insert into users values (?,?)", user.getName(), user.getUrl());
//
//	}
//}
