package br.edu.ifpb.popjudge.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.dao.LanguageDAO;
import br.edu.ifpb.popjudge.language.Language;

public class LanguageService {

	@Inject
	private LanguageDAO dao;
	
	public List<Language> consultarTodos() {
		try {
			return dao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Language consultaPorId(int id) {
		try {
			return dao.get(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public Language update(Language language) {
//		try {
//			return dao.update(language);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public Boolean delete(int id) {
//		try {
//			return dao.delete(id);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
}
