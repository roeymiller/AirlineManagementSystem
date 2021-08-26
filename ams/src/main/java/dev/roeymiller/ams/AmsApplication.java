package dev.roeymiller.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class AmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmsApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("dev.roeymiller"))
				.build()
				.apiInfo(apiDetails());

	}
	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Airlines Management System",
				"http://localhost:8094/swagger-ui/index.html#/",
				"1.0",
				"",
				new springfox.documentation.service.Contact("Roey Miller","","roeymillerp401@gmail.com"),
				"",
				"",
				Collections.emptyList()
		);
	}
}
