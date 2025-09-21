package vn.iotstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication(scanBasePackages = {"vn.iotstar"})
//@ComponentScan(basePackages = {"vn.iotstar.controller"})
public class LvdApplication {

	public static void main(String[] args) {
		SpringApplication.run(LvdApplication.class, args);

	}

}
