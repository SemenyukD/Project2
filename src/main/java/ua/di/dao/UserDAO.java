package ua.di.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.di.model.User;

@Component
public class UserDAO {

	@Autowired
	DataSource dataSource;
//	private JdbcTemplate jdbcTemplate;

	public List<User> getAll() {
		return ((JdbcTemplate) dataSource).query("select * from users", new BeanPropertyRowMapper<>(User.class));
	}

	public void add(User user) {
		((JdbcTemplate) dataSource).update("insert into users values (?,?,?)", user.getName(), user.getUrl());

	}
}
