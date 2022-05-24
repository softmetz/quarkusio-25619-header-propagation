package com.acme;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "authorization-header-rest-client")
public interface RESTClient {

  @GET
  @Consumes("application/json")
  @Path("hello")
  Response sendAuthorizationHeader(@HeaderParam("Authorization") String authorizationHeader);
}
