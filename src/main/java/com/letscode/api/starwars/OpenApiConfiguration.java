package com.letscode.api.starwars;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Value("${info.app.description}")
  private String description;

  @Value("${info.app.version}")
  private String version;

  @Value("${spring.application.name}")
  private String appName;

  @Bean
  public OpenAPI swagger() {
    return
        new OpenAPI()
            .components(openApiComponents())
            .info(swaggerInfo());
  }

  private Components openApiComponents() {
    return new Components()
        .addSecuritySchemes(
            "basicScheme", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
        );
  }

  private Info swaggerInfo() {
    return new Info()
        .title(appName)
        .description(description)
        .version(version);
  }
}
