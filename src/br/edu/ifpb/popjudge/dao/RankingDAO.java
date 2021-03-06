package br.edu.ifpb.popjudge.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import br.edu.ifpb.popjudge.model.Score;
import br.edu.ifpb.popjudge.model.UserRank;


public class RankingDAO implements Dao<UserRank> {
	private ArrayList<UserRank> all = null;

	@Override
	public String insert(UserRank value) throws SQLException {
		if (this.get(value.getUsername()) != null) {
			Connection connection;
			try {
				connection = new ConnectionFactory().getConnection();
				String sql = "insert into RANKING(username, id_problem, tries, passed_time) values (?, ?, ?, ?)";
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setString(1, value.getUsername());

//				for (Map.Entry<Integer, Score> entry : value.getProblems()
//						.entrySet()) {
//					stmt.setInt(2, entry.getKey());
//					stmt.setInt(3, entry.getValue().getTries());
//					stmt.setLong(4, entry.getValue().getPassedTime());
//					stmt.execute();
//				}
				stmt.close();
				connection.close();
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}

		return "Criado com sucesso";
	}

	@Override
	public ArrayList<UserRank> getAll() throws SQLException {

		if (all == null) {
			Connection connection;
			try {
				connection = new ConnectionFactory().getConnection();
				this.all = new ArrayList<UserRank>();

				String sql = "select * from RANKING group by(username)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					all.add(this.get(rs.getString("username")));
				}

				rs.close();
				stmt.close();
				connection.close();
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

		return all;
	}

	public UserRank get(String username) throws SQLException {
		Connection connection;
		try {
			connection = new ConnectionFactory().getConnection();
			if (connection == null) {
				System.out.println("A PORRA DA CONEXÃƒO TÃ� NULA");
			}
			String sql = "select * from RANKING where username = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			Map<Integer, Score> problems = new TreeMap<Integer, Score>();

			while (rs.next()) {
				problems.put(rs.getInt("id_problem"), new Score(rs.getInt("tries"),
						rs.getLong("passed_time")));
			}

			UserRank ur = new UserRank(username, problems);

			rs.close();
			stmt.close();
			connection.close();

			return ur;
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	@Deprecated
	public UserRank get(int id) throws SQLException {
		System.out.println("DONT. USE. THIS.");
		return null;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		Connection connection;
		try {
			connection = new ConnectionFactory().getConnection();
			String sql = "delete from RANKING where id_problem = ?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public UserRank update(UserRank value) throws SQLException {
		Connection connection;
		try {
			connection = new ConnectionFactory().getConnection();
			String sql = "update RANKING set tries = ?, passed_time = ? where username = ? and id_problem = ? limit 1";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(3, value.getUsername());

			for (Map.Entry<Integer, Score> entry : value.getProblems().entrySet()) {
				stmt.setInt(1, entry.getValue().getTries());
				stmt.setLong(2, entry.getValue().getPassedTime());
				stmt.setInt(4, entry.getKey());
				stmt.execute();
			}

			value.setProblems(this.get(value.getUsername()).getProblems());
			stmt.close();
			connection.close();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}

	@Override
	public void truncate() throws SQLException {
		Connection connection;
		try {
			connection = new ConnectionFactory().getConnection();
			String sql = "truncate table RANKING";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			statement.close();

			connection.close();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
