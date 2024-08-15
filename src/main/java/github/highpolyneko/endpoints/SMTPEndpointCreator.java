package github.highpolyneko.endpoints;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.component.mail.MailConfiguration;
import org.apache.camel.component.mail.MailEndpoint;

public class SMTPEndpointCreator {
  public static Endpoint create(CamelContext context, SMTPEndpointConfig config) throws Exception {
    MailConfiguration mailConfiguration = new MailConfiguration();
    mailConfiguration.configureProtocol("smtp");
    mailConfiguration.setHost(config.getHost());
    mailConfiguration.setPort(config.getPort());
    mailConfiguration.setUsername(config.getUserName());
    mailConfiguration.setPassword(config.getPassword());

    // enable TLS
    mailConfiguration.getAdditionalJavaMailProperties().put("mail.smtp.starttls.enable", "true");

    String endpointUri = String.format("smtp://%s:%d", mailConfiguration.getHost(), mailConfiguration.getPort());
    MailEndpoint endpoint = new MailEndpoint(endpointUri);
    endpoint.setConfiguration(mailConfiguration);
    context.addEndpoint(endpointUri, endpoint);
    return endpoint;
  }
}
