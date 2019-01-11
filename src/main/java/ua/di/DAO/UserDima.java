package ua.di.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.di.model.User;

@Component
public class UserDima {

	@Autowired
//	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public List<User> getAll() {
		return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
	}

	public void add(User user) {
		jdbcTemplate.update("insert into users values (?,?,?)", user.getName(), user.getUrl());

	}
}
