package org.superbiz;

import io.restassured.response.ValidatableResponse;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

@RunWith(VertxUnitRunner.class)
public class CalculatorVerticleTest {

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    @Test
    public void shoudl_add_two_numbers(TestContext context) {

        // given
        Vertx vertx = rule.vertx();
        vertx.deployVerticle(CalculatorVerticle.class.getName());

        // when
        final ValidatableResponse response = get("/sum/{a}/{b}", 1, 3).then();

        // then
        response.assertThat().body(is("1 + 3 = 4"));
    }
}
