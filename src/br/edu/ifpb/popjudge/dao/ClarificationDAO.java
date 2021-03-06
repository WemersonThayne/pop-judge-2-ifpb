package br.edu.ifpb.popjudge.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpb.popjudge.model.Clarification;


public class ClarificationDAO implements Dao<Clarification> {
	private Connection connection;
	
	@Override
	public String insert(Clarification value) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO CLARIFICATION(id_clarification, id_user, id_problem, "
				+ "issue, answer) VALUES(0, ?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, value.getUser().getIdUser());
		statement.setInt(2, value.getProblem().getIdProblem());
		statement.setString(3, value.getIssue());
		statement.setString(4, value.getAnswer());
		
		statement.execute();
		
		statement.close();
		connection.close();
		return "Criado com sucesso";
	}

	@Override
	public ArrayList<Clarification> getAll() throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM CLARIFICATION ORDER BY id_problem";
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		ArrayList<Clarification> list = new ArrayList<Clarification>();
		
		UserDAO uDAO = new UserDAO();
		ProblemDAO pDAO = new ProblemDAO();
		
		while(resultSet.next()){
			list.add(new Clarification(resultSet.getInt("id_clarification"),
									   uDAO.get(resultSet.getInt("id_user")),
									   pDAO.get(resultSet.getInt("id_problem")),
									   resultSet.getString("issue"),
									   resultSet.getString("answer"),
									   resultSet.getTimestamp("time_clarification")));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}
	
	public ArrayList<Clarification> getNotReplied() throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM CLARIFICATION WHERE answer IS NULL ORDER BY id_problem";
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		ArrayList<Clarification> list = new ArrayList<Clarification>();
		
		UserDAO uDAO = new UserDAO();
		ProblemDAO pDAO = new ProblemDAO();
		
		while(resultSet.next()){
			list.add(new Clarification(resultSet.getInt("id_clarification"),
									   uDAO.get(resultSet.getInt("id_user")),
									   pDAO.get(resultSet.getInt("id_problem")),
									   resultSet.getString("issue"),
									   resultSet.getString("answer"),
									   resultSet.getTimestamp("time_clarification")));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}
	
	@Override
	public Clarification get(int id) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = String.format("SELECT * FROM CLARIFICATION WHERE id_clarification = %d", id);
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		Clarification ret = null;
		UserDAO uDAO = new UserDAO();
		ProblemDAO pDAO = new ProblemDAO();
		
		if(resultSet.next()){
			ret = new Clarification(resultSet.getInt("id_clarification"),
				   				    uDAO.get(resultSet.getInt("id_user")),
									pDAO.get(resultSet.getInt("id_problem")),
									resultSet.getString("issue"),
									resultSet.getString("answer"),
									resultSet.getTimestamp("time_clarification"));
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
		
		String sql = String.format("DELETE FROM CLARIFICATION WHERE id_clarification = %d", id);
	
		Statement statement = connection.createStatement();
		
		boolean ret = statement.execute(sql);
	
		statement.close();
		connection.close();
		
		return ret;
	}

	@Override
	public Clarification update(Clarification value) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "UPDATE CLARIFICATION SET id_user = ?, id_problem = ?, "
				+ "issue = ?, answer = ?, time_clarification = ? WHERE id_clarification = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, value.getUser().getIdUser());
		statement.setInt(2, value.getProblem().getIdProblem());
		statement.setString(3, value.getIssue());
		statement.setString(4, value.getAnswer());
		statement.setString(5, value.getTimestamp().toString());
		statement.setInt(6, value.getIdClarification());
		
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

		String sql = "truncate table CLARIFICATION";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		statement.close();
		
		connection.close();
		
	}

	public void deleteByProblem(int idProblem) throws SQLException {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = String.format("DELETE FROM CLARIFICATION WHERE id_problem = %d", idProblem);

		Statement statement = connection.createStatement();

		statement.execute(sql);
		
		statement.close();
		connection.close();	
	}
}