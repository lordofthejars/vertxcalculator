package org.superbiz;

import io.restassured.response.ValidatableResponse;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import java.io.IOException;
import java.net.ServerSocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

@RunWith(VertxUnitRunner.class)
public class CalculatorVerticleTest {

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    int port;

    @Before
    public void before(TestContext context) throws IOException {
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();

        DeploymentOptions options = new DeploymentOptions()
            .setConfig(new JsonObject().put("http.port", port));

        rule.vertx().deployVerticle(CalculatorVerticle.class.getName(), options, context.asyncAssertSuccess());
    }

    @After
    public void after(TestContext context) {
        rule.vertx().close(context.asyncAssertSuccess());
    }

    @Test
    public void should_add_two_numbers(TestContext context) {

        // when
        final ValidatableResponse response = get("/sum/{a}/{b}", 1, 3).then();

        // then
        response.assertThat().body(is("1 + 3 = 4"));
    }
}
