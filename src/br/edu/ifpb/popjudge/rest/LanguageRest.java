package br.edu.ifpb.popjudge.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.language.Language;
import br.edu.ifpb.popjudge.service.LanguageService;

@Path("/language")
public class LanguageRest {
	
	@Inject
	private LanguageService service;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGet(@PathParam("id") int id) {
		return Response.ok().entity(service.consultaPorId(id)).build();
	}
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGetAll() {
		return Response.ok().entity(service.consultarTodos()).build();
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response creat(Language language) {
//		return Response.ok(service.create(language)).build();
//	}
//
//
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response doPut(Language language) {
//		return Response.ok(service.update(language)).build();
//	}

//	@DELETE
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response doDelete(@PathParam("id") int id) {
//		return Response.ok(service.delete(id)).build();
//	}

}
