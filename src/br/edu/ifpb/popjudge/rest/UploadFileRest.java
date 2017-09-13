package br.edu.ifpb.popjudge.rest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import br.edu.ifpb.popjudge.util.UtilComand;

@Path("/upload")
public class UploadFileRest {
	
	private final String  PATH_SUBMISOES = "C:\\submisoes\\";
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("file") InputStream in,  @FormDataParam("file") FormDataContentDisposition fdcd) {
		try {

			UtilComand.createDirUser("adm");
			Files.copy(in, new File(PATH_SUBMISOES+"\\adm"+fdcd.getFileName()).toPath());
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).build();
		
	}
}
