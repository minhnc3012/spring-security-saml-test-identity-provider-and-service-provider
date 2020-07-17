package g.t.saml;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import g.t.saml.config.AppProperties;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = JmxAutoConfiguration.class)
@EnableConfigurationProperties({AppProperties.class})
@Slf4j
public class IdentityProviderApplication {
	private static final Logger log = LoggerFactory.getLogger(IdentityProviderApplication.class);
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(IdentityProviderApplication.class);

        Environment env = app.run(args).getEnvironment();
        log.info("Identity Provider: Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\t\thttp://localhost:{}{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("server.port"), env.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress()
        );
    }
}

