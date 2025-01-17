package cn.zup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ImportResource("classpath:zup-*.xml")
@ComponentScan({"cn.zup.*"})
@ServletComponentScan
@EnableSwagger2
public class ZupApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ZupApplication.class, args);
	}
	
	
}
