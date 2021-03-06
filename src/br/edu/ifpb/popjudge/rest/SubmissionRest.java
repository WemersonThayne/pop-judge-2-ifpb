package br.edu.ifpb.popjudge.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.service.SubmissionService;
@Path("/submission")
public class SubmissionRest {
	
	@Inject
	private SubmissionService service;
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGetAll() {
		return Response.ok().entity(service.consultarTodos()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultaPorIdUsuario(@PathParam("id") int id) {
		return Response.ok().entity(service.consultarPorIdUser(id)).build();
	}
}
