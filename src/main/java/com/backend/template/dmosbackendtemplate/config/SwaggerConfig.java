package com.backend.template.dmosbackendtemplate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("${dmos.config.show-swagger-ui}")
    private boolean showSwaggerUi;
    @Value("${keycloak.auth-server-url}")
    private String authURL;
    @Value("${keycloak.realm}")
    private String realm;

    @Bean
    public Docket apiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(securityScheme()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(apiInfo())
                .enable(showSwaggerUi);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DMOS Test Application")
                .description("DMOS Template Application")
                .license("")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("")
                .version("0.0.1")
                .build();
    }
    private SecurityScheme securityScheme() {
        return new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(grantTypes())
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(new SecurityReference("spring_oauth", new AuthorizationScope[]{})))
                .forPaths(PathSelectors.regex("/api.*"))
                .build();
    }

    private List<GrantType> grantTypes() {
        GrantType grantType = new ClientCredentialsGrant(authURL + "realms/" + realm + "/protocol/openid-connect/token");
        return List.of(grantType);
    }


    @Bean
    public LinkDiscoverers discovers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}
