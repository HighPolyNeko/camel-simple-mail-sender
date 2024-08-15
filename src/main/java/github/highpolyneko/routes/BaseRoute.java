package github.highpolyneko.routes;

import github.highpolyneko.endpoints.SMTPEndpointConfig;
import github.highpolyneko.endpoints.SMTPEndpointCreator;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BaseRoute extends RouteBuilder {
  private SMTPEndpointConfig config;

  @Override
  public void configure() throws Exception {
    from("timer:test?period=10000")
        .setHeader("Subject", simple("test message"))
        .setHeader("From", simple("hello <>"))
        .setHeader("To", simple(""))
        .setBody(simple("просто тестирую не блочь"))
        .to(SMTPEndpointCreator.create(getContext(), config))
        .log("все гуд");
  }
}