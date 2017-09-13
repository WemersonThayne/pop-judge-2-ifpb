package br.edu.ifpb.popjudge.util;

import java.io.File;
import java.io.IOException;

public class UtilComand {
	
	private final static String BASE_DIR  = "C:\\submisoes\\";
	
	public static Boolean createDirUser(String nameDir) {
		
		try {
			
			 File diretorio = new File(BASE_DIR + nameDir);
	         diretorio.mkdir();
			return Boolean.TRUE;
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}
	
}
