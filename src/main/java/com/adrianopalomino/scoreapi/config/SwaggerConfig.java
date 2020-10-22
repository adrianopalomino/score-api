package com.adrianopalomino.scoreapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.adrianopalomino.scoreapi")).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET()).apiInfo(metaData());

	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Análise de Perfil de Crédito API")
				.description("\"Spring Boot REST API para análise de crédito\"").version("1.0.0")
				.license("Apache License Version 2.0").licenseUrl("https://apache.org/licenses/LICENSE-2.0").build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private List<ResponseMessage> responseMessageForPOST() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(500).message("Erro interno").build());
				add(new ResponseMessageBuilder().code(403).message("Você não tem permissão para acessar este recurso")
						.build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(500).message("Erro interno").build());
				add(new ResponseMessageBuilder().code(404).message("Recurso não encontrado")
						.build());
				add(new ResponseMessageBuilder().code(403).message("Você não tem permissão para acessar este recurso")
						.build());
			}
		};
	}
}