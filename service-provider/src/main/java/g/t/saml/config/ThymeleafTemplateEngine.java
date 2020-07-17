package g.t.saml.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.SamlTemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import g.t.saml.ServiceProviderApplication;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class ThymeleafTemplateEngine implements SamlTemplateEngine {
	private static final Logger log = LoggerFactory.getLogger(ThymeleafTemplateEngine.class);
    final SpringTemplateEngine springTemplateEngine;

    public ThymeleafTemplateEngine(SpringTemplateEngine springTemplateEngine) {
		super();
		this.springTemplateEngine = springTemplateEngine;
	}

	@Override
    public void process(HttpServletRequest request, String templateId, Map<String, Object> model, Writer out) {

        Context ctx = new Context(Locale.ENGLISH, model);
        String content = springTemplateEngine.process(templateId, ctx);

        try {
            out.write(content);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
