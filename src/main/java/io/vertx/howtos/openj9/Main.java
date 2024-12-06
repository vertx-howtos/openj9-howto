package io.vertx.howtos.openj9;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Main extends VerticleBase {

  @Override
  public Future<?> start() {
    Router router = Router.router(vertx);
    router.post().handler(BodyHandler.create());
    router.post("/sum").handler(this::sum);

    return vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);
  }

  private void sum(RoutingContext context) {
    JsonObject input = context.body().asJsonObject();

    Integer a = input.getInteger("a", 0);
    Integer b = input.getInteger("b", 0);

    JsonObject response = new JsonObject().put("sum", a + b);

    context.response()
      .putHeader("Content-Type", "application/json")
      .end(response.encode());
  }

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Main()).await();
    long duration = MILLISECONDS.convert(System.nanoTime() - startTime, NANOSECONDS);
    System.out.println("Started in " + duration + "ms");
  }
}
