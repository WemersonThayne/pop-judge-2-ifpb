package br.edu.ifpb.popjudge.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.dao.RankingDAO;
import br.edu.ifpb.popjudge.dao.SubmissionDAO;
import br.edu.ifpb.popjudge.model.Submission;
import br.edu.ifpb.popjudge.model.UserRank;

public class RankingService {
	@Inject
	private RankingDAO dao;

	public List<UserRank> consultarTodos() {
		try {
			return dao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
