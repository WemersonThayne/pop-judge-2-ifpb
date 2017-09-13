package br.edu.ifpb.popjudge.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.service.ProblemService;

@Path("/problem")
public class ProblemRest {

	@Inject
	private ProblemService service;
	
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

}
