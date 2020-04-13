package com.springbootjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate template;

	Integer getCountOfUsers() {
		return template.queryForObject("select count(*) from user", Integer.class);
	}

	String getUserName(int id, String address) {
		/* Integer ID = new Integer(id); */
		return template.queryForObject("select name from user where id=? and address=?", new Object[] { id, address },
				String.class);
	}

	User getUserById(int id) {

		return template.queryForObject("select * from user where id=?", new Object[] { id }, new UserMapper());
	}

	String addUser(User user) {

		template.update("insert into user values(?,?,?)", user.getId(), user.getName(), user.getAddress());
		return "" + user.getName() + "  was added into table.";
	}

	String removeUser(int id) {
		template.update("delete from user where id=?", id);
		return "" + id + "  was removed from table.";
	}

	String updateUser(User user) {
		template.update("update user set name=?, address=? where id=?", user.getName(), user.getAddress(),
				user.getId());
		return "" + user.getName() + "  was successfully updatede.";
	}

	List<User> getAllUsers() {
		List<User> listUsers = template.query("select * from user"
					,new UserMapper() 
				);
		return listUsers;

	}

	class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();

//			Map the fields of User with the columns of table
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setAddress(rs.getString("address"));

			return user;
		}

	}

}
