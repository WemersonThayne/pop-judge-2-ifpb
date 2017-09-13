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

import br.edu.ifpb.popjudge.model.User;
import br.edu.ifpb.popjudge.service.UserService;


@Path("/user")
public class UserRest {
	
	@Inject
	private UserService service;
	
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creat(User user) {
		return Response.ok(service.create(user)).build();
	}


	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doPut(User user) {
		return Response.ok(service.update(user)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doDelete(@PathParam("id") int id) {
		return Response.ok(service.delete(id)).build();
	}
}
