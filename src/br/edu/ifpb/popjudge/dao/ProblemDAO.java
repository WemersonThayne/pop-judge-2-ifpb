package br.edu.ifpb.popjudge.dao;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpb.popjudge.model.Problem;


public class ProblemDAO implements Dao<Problem> {
	private Connection connection;

	@Override
	@Deprecated
	public String insert(Problem value) throws SQLException {
		return "Criado com sucesso";
	}
	public int insertGet(Problem value) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "INSERT INTO PROBLEM(id_problem, title, score_points, "
				+ "time_limit, dir) VALUES(0, ?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			statement.setString(1, value.getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			statement.setInt(2, value.getScorePoints());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			statement.setLong(3, value.getTimeLimit());
		} catch (IOException e) {
			e.printStackTrace();
		}
		statement.setString(4, value.getDir().getAbsolutePath());
		statement.execute();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		resultSet.next();
		int generatedKey = resultSet.getInt(1);
		
		statement.close();
		connection.close();
		
		return generatedKey;
	}

	@Override
	public ArrayList<Problem> getAll() throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "SELECT * FROM PROBLEM";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<Problem> list = new ArrayList<Problem>();

		while (resultSet.next()) {
			list.add(new Problem(resultSet.getInt("id_problem"), resultSet
					.getInt("score_points"), resultSet.getString("title"),
					resultSet.getLong("time_limit"), new File(resultSet
							.getString("dir"))));
		}

		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	@Override
	public Problem get(int id) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = String.format(
				"SELECT * FROM PROBLEM WHERE id_problem = %d", id);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		Problem ret = null;

		if (resultSet.next()) {
			ret = new Problem(resultSet.getInt("id_problem"),
					resultSet.getInt("score_points"),
					resultSet.getString("title"),
					resultSet.getLong("time_limit"), new File(
							resultSet.getString("dir")));
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

		String sql = "DELETE FROM PROBLEM WHERE id_problem = ?";		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setInt(1, id);
		
		stmt.execute();
		
		connection.close();
		
		return true;
	}

	@Override
	public Problem update(Problem value) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "UPDATE PROBLEM SET title = ?, score_points = ?, "
				+ "time_limit = ?, dir = ? WHERE id_problem = ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		try {
			statement.setString(1, value.getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			statement.setInt(2, value.getScorePoints());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			statement.setLong(3, value.getTimeLimit());
		} catch (IOException e) {
			e.printStackTrace();
		}
		statement.setString(4, value.getDir().getAbsolutePath());
		statement.setInt(5, value.getIdProblem());

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

		String sql = "truncate table PROBLEM";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		statement.close();
		
		connection.close();		
	}
}
