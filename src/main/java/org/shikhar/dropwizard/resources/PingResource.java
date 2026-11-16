package org.shikhar.dropwizard.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("ping")
public class PingResource {

  @GET
  public Response ping() {
    return Response.ok("Hello World!").build();
  }
}
