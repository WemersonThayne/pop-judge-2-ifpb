package br.edu.ifpb.popjudge.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.inject.Inject;

import br.edu.ifpb.popjudge.service.RankingService;
@Path("/rank/user")
public class RankingRest {
	@Inject
	private RankingService service;
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGetAll() {
		return Response.ok().entity(service.consultarTodos()).build();
	}
}
