package com.github.sharifyy.inpresstask;

import com.github.sharifyy.inpresstask.feign.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FeignConfiguration.class)
@EnableFeignClients
public class InpressTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(InpressTaskApplication.class, args);
	}

}
