package br.edu.ifpb.popjudge.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.dao.ProblemDAO;
import br.edu.ifpb.popjudge.model.Problem;

public class ProblemService {
	@Inject
	private ProblemDAO dao;

	public List<Problem> consultarTodos() {
		try {
			return dao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Problem consultaPorId(int id) {
		try {
			return dao.get(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
