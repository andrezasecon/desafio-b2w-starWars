package br.com.andrezasecon.b2w.apiplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class ApiplanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiplanetApplication.class, args);
	}

}
