package vn.iotstar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vn.iotstar.config.StorageProperties;
import vn.iotstar.service.IStorageService;

@SpringBootApplication(scanBasePackages = { "vn.iotstar" })
//@ComponentScan(basePackages = {"vn.iotstar.controller"})
@EnableConfigurationProperties(StorageProperties.class)
//@EnableSwagger2

public class LvdApplication {

	public static void main(String[] args) {
		SpringApplication.run(LvdApplication.class, args);

	}

	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}
//	@Bean
//	 public Docket SWAGGERApi() {
//	 return new Docket(DocumentationType.SWAGGER_2)
//	 //.select()
//
//	//.apis(RequestHandlerSelectors.basePackage("vn.iotstar")).build();
//	 .select()
//	 .apis(RequestHandlerSelectors.any())
//	 .paths(PathSelectors.any())
//	 .build();
//	 }

}
