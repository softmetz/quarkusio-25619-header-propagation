package com.acme;

import io.quarkus.security.Authenticated;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@RequestScoped
@Path("/")
public class RestResource {

  public static final String BEARER_FROM_SOURCE = "Bearer fromSource";
  @Inject
  @RestClient
  RESTClient restClient;

  @GET
  @Path("authorized-endpoint")
  @Consumes("application/json")
  @Authenticated
  public Response authorized() {

    restClient.sendAuthorizationHeader(BEARER_FROM_SOURCE);

    return Response.noContent().build();

  }

  @GET
  @Path("public-endpoint")
  @Consumes("application/json")
  @Authenticated
  public Response publicEndpoint() {

    restClient.sendAuthorizationHeader(BEARER_FROM_SOURCE);

    return Response.noContent().build();

  }

}
