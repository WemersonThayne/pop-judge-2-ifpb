package br.edu.ifpb.popjudge.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.dao.SubmissionDAO;
import br.edu.ifpb.popjudge.model.Submission;

public class SubmissionService {

	@Inject
	private SubmissionDAO dao;

	public List<Submission> consultarTodos() {
		try {
			return dao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public List<Submission> consultarPorIdUser(int id) {
		try {
			return dao.getMySubmissions(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
