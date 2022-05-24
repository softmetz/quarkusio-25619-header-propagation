package com.acme;

import static io.restassured.RestAssured.given;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(RestResource.class)
@QuarkusTestResource(WireMockExtensions.class)
class ReproducerTest {

  @Test
  void callAuthorized() {
    given().header("authorization", "Bearer fromRequest").get("authorized-endpoint").then()
        .statusCode(204);
  }

  @Test
  void callPublic() {
    given().get("public-endpoint").then().statusCode(204);
  }

}
