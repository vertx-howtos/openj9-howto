package io.vertx.howtos.openj9;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Main extends AbstractVerticle {

  private static long startTime;

  @Override
  public void start(Future<Void> future) {
    Router router = Router.router(vertx);
    router.post().handler(BodyHandler.create());
    router.post("/sum").handler(this::sum);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080, ar -> {
        if (ar.succeeded()) {
          System.out.println("Started in " + (System.currentTimeMillis() - startTime) + "ms");
        } else {
          ar.cause().printStackTrace();
        }
      });
  }

  private void sum(RoutingContext context) {
    JsonObject input = context.getBodyAsJson();

    Integer a = input.getInteger("a", 0);
    Integer b = input.getInteger("b", 0);

    JsonObject response = new JsonObject().put("sum", a + b);

    context.response()
      .putHeader("Content-Type", "application/json")
      .end(response.encode());
  }

  public static void main(String[] args) {
    startTime = System.currentTimeMillis();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Main());
  }
}
