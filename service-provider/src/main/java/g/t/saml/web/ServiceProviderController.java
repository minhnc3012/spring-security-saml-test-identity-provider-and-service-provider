package g.t.saml.web;

import g.t.saml.config.AppProperties;
import g.t.saml.config.ThymeleafTemplateEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.saml.provider.config.ExternalProviderConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ServiceProviderController {
	private static final Logger log = LoggerFactory.getLogger(ThymeleafTemplateEngine.class);
	@Autowired
    final AppProperties appProperties = null;

    @GetMapping("/idps")
    public String listIDPs(Model model) {

        List<String> collect = appProperties.getServiceProvider().getProviders().stream()
                .map(ExternalProviderConfiguration::getLinktext)
                .collect(toList());

        model.addAttribute("idps", collect);
        return "idp-list";
    }

    @RequestMapping(value = {"/", "/index", "/logged-in"})
    public String home() {
        log.info("Sample SP Application - You are logged in!");
        return "logged-in";
    }

}
