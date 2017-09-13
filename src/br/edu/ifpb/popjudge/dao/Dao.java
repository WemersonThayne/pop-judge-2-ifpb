package br.edu.ifpb.popjudge.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T> {
	
	//TODO Colocar pra essa classe sempre retornar um inteiro (a chave gerada?)
	public String insert (T value) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public T get (int id) throws SQLException;

    public boolean delete (int id) throws SQLException;

    public T update(T value) throws SQLException;
    
    public void truncate() throws SQLException;
}
