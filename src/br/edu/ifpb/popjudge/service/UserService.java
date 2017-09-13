package br.edu.ifpb.popjudge.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.dao.UserDAO;
import br.edu.ifpb.popjudge.model.User;


public class UserService {

	@Inject
	private UserDAO dao;
	
	public List<User> consultarTodos() {
		try {
			return dao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String create(User user) {
		try {
			return dao.insert(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR";
	}

	public User consultaPorId(int id) {
		try {
			return dao.get(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User update(User user) {
		try {
			return dao.update(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean delete(int id) {
		try {
			return dao.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
