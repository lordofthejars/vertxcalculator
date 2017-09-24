package org.superbiz;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class CalculatorVerticle extends AbstractVerticle {

    private CalculatorDecorator calculatorDecorator = new CalculatorDecorator();

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(CalculatorVerticle.class.getName());
    }

    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/sum/:a/:b").handler(this::sum);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    public void sum(RoutingContext routingContext) {
        final HttpServerRequest request = routingContext.request();
        final HttpServerResponse response = routingContext.response();

        int a = Integer.parseInt(request.getParam("a"));
        int b = Integer.parseInt(request.getParam("b"));

        String result = calculatorDecorator.add(a, b);

        response.putHeader("content-type", "text/plain").end(result);

    }

}
