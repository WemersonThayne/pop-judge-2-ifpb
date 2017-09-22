package br.edu.ifpb.popjudge.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpb.popjudge.model.User;



public class UserDAO implements Dao<User> {
	private Connection connection;
	
	@Override
	public String insert(User value) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO USER(id_user, username, password, email, "
				+ "full_name, city, college, dir)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, 0);
		statement.setString(2, value.getUsername());
		statement.setString(3, value.getPassword());
		statement.setString(4, value.getEmail());
		statement.setString(5, value.getFullName());
		statement.setString(6, value.getCity());
		statement.setString(7, value.getCollege());
		statement.setString(8, value.getDir());
		
		statement.execute();
		
		statement.close();
		connection.close();
		return "Criado com sucesso";
	}

	@Override
	public ArrayList<User> getAll() throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM USER";
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		ArrayList<User> list = new ArrayList<User>();
		
		while(resultSet.next()){
			list.add(new User(resultSet.getInt("id_user"),
							  resultSet.getString("username"),
							  resultSet.getString("password"),
							  resultSet.getString("email"),
							  resultSet.getString("full_name"),
							  resultSet.getString("city"),
							  resultSet.getString("college"),
							  resultSet.getString("dir")));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}

	@Override
	public User get(int id) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = String.format("SELECT * FROM USER WHERE id_user = %d", id);
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		User ret = null;
		
		if(resultSet.next()){
			ret = new User(resultSet.getInt("id_user"),
						   resultSet.getString("username"),
						   resultSet.getString("password"),
						   resultSet.getString("email"),
						   resultSet.getString("full_name"),
						   resultSet.getString("city"),
						   resultSet.getString("college"),
						   resultSet.getString("dir"));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return ret;
	}
	
	public User get(String username) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = String.format("SELECT * FROM USER WHERE username = '%s'", username);
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		User ret = null;
		
		if(resultSet.next()){
			ret = new User(resultSet.getInt("id_user"),
						   resultSet.getString("username"),
						   resultSet.getString("password"),
						   resultSet.getString("email"),
						   resultSet.getString("full_name"),
						   resultSet.getString("city"),
						   resultSet.getString("college"),
						   resultSet.getString("dir"));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return ret;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = String.format("DELETE FROM USER WHERE id_user = %d", id);
		
		Statement statement = connection.createStatement();
		
		return statement.execute(sql);
	}

	@Override
	public User update(User value) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "UPDATE USER SET password = ?, email = ?,"
				+ "full_name = ?, city = ?, college = ?, dir = ? WHERE id_user = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, value.getPassword());
		statement.setString(2, value.getEmail());
		statement.setString(3, value.getFullName());
		statement.setString(4, value.getCity());
		statement.setString(5, value.getCollege());
		statement.setString(6, value.getDir());
		statement.setInt(7, value.getIdUser());
		
		statement.execute();
		
		statement.close();
		connection.close();
		return value;
	}

	@Override
	public void truncate() throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "delete from USER where username <> 'Admin'";
			
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		statement.close();
		
		connection.close();
	}

	
}
