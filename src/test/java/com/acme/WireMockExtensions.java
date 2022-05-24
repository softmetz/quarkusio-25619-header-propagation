package com.acme;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockExtensions implements QuarkusTestResourceLifecycleManager {

  private WireMockServer wireMockServer;

  @Override
  public Map<String, String> start() {
    wireMockServer = new WireMockServer();
    wireMockServer.start();

    wireMockServer.stubFor(
        get(urlEqualTo("/hello")).withHeader("authorization", equalTo("Bearer fromSource"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

    return Collections.singletonMap(
        "quarkus.rest-client.\"authorization-header-rest-client\".url",
        wireMockServer.baseUrl());
  }

  @Override
  public void stop() {
    if (null != wireMockServer) {
      wireMockServer.stop();
    }
  }
}
